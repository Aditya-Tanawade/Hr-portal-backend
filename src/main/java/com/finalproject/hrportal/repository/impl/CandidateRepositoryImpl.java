package com.finalproject.hrportal.repository.impl;

import com.finalproject.hrportal.domain.Candidate;
import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import com.finalproject.hrportal.repository.CandidateRepository;
import com.finalproject.hrportal.rowmapper.AppliedCandidateRowMapper;
import com.finalproject.hrportal.rowmapper.CandidateRowMapper;
import com.finalproject.hrportal.strategy.CandidateFilterStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class CandidateRepositoryImpl implements CandidateRepository {

    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;
    private final List<CandidateFilterStrategy> strategies;



    @Override
    public List<AppliedCandidatesDTO> filterCandidates(String hrId, CandidateFilterRequestDTO filter) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        List<String> whereParts = new ArrayList<>();

        // Base condition: only candidates under this HR
        whereParts.add("j.hr_id = :hrId AND j.status='POSTED' AND ca.status='APPLIED'");
        params.addValue("hrId", hrId);

        // Apply all registered strategies
        for (CandidateFilterStrategy s : strategies) {
            s.createCondition(filter, params).ifPresent(whereParts::add);
        }

        String where = whereParts.isEmpty() ? "" : " WHERE " + String.join(" AND ", whereParts);

        int page = Optional.ofNullable(filter.getPage()).orElse(0);
        int size = Optional.ofNullable(filter.getSize()).orElse(20);
        int offset = page * size;

        params.addValue("offset", offset);
        params.addValue("limit", size);

        String sql = """
            SELECT c.candidate_id, c.email, c.full_name, c.gender, 
                   c.expected_ctc, c.resume_path, c.total_experience, 
                   c.skills, c.profile_role, c.notice_period,
                   ca.application_id, ca.applied_at,
                   j.job_request_id, j.title
            FROM candidates c
            JOIN candidate_applications ca ON c.candidate_id = ca.candidate_id
            JOIN job_requests j ON ca.job_request_id = j.job_request_id
            """ + where + """
             ORDER BY c.created_at DESC
            OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY
            """;

        log.info("Final SQL: {}", sql);
        log.info("Params: {}", params.getValues());

        return jdbc.query(sql, params, new AppliedCandidateRowMapper());
    }


}
