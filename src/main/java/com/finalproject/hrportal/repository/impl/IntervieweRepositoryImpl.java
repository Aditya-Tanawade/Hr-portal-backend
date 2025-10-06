package com.finalproject.hrportal.repository.impl;

import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.repository.IntervieweRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
@RequiredArgsConstructor
public class IntervieweRepositoryImpl implements IntervieweRepository {

    private final JdbcTemplate jdbcTemplate;



    @Override
    public Integer getCountOfAssignedInterview(String hrId) {
        String sql="SELECT COUNT(*) FROM interviews WHERE hr_id=? AND status NOT IN('PENDING') ";
        return jdbcTemplate.queryForObject(sql,Integer.class,hrId);

    }

    @Override
    public Integer getCountOfSelectedCandidate(String hrId) {
        String sql="SELECT COUNT(*) FROM interviews WHERE hr_id=? AND status NOT IN('PENDING','INTERVIEW1_REJECTED','INTERVIEW2_REJECTED','HR_REJECTED')";
        return jdbcTemplate.queryForObject(sql,Integer.class,hrId);
    }

    @Override
    public Integer getCountOfRejectedCandidates(String hrId) {
        String sql="SELECT COUNT(*) FROM interviews WHERE hr_id=? AND status IN('INTERVIEW1_REJECTED','INTERVIEW2_REJECTED','HR_REJECTED')";
        return jdbcTemplate.queryForObject(sql,Integer.class,hrId);
    }
}
