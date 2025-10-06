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
    public String addInterview(AppliedCandidatesDTO appliedCandidatesDTO) {
        String sql="INSERT INTO INTERVIEWS (interview_id,application_id) VALUES (interview_id_seq.NEXTVAL,?)";
        return "";
    }
}
