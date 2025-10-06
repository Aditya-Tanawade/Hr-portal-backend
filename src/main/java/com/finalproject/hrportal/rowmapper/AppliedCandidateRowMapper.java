package com.finalproject.hrportal.rowmapper;

import com.finalproject.hrportal.domain.CandidateApplication;
import com.finalproject.hrportal.domain.enums.CandidateGender;
import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AppliedCandidateRowMapper implements RowMapper<AppliedCandidatesDTO> {

    @Override
    public AppliedCandidatesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        int candidateId=rs.getInt("CANDIDATE_ID");
        String email=rs.getString("EMAIL");
        String fullName=rs.getString("FULL_NAME");
        String gender=rs.getString("GENDER");
        double expectedCtc=rs.getDouble("EXPECTED_CTC");
        String resumePath=rs.getString("RESUME_PATH");
        double totalExperience=rs.getDouble("TOTAL_EXPERIENCE");
        int noticePeriod=rs.getInt("NOTICE_PERIOD");
        String skills=rs.getString("SKILLS");
        String profileRole=rs.getString("PROFILE_ROLE");
        int applicationId=rs.getInt("APPLICATION_ID");
        LocalDate appliedAt=rs.getDate("APPLIED_AT").toLocalDate();
        int jobRequestId=rs.getInt("JOB_REQUEST_ID");
        String title=rs.getString("TITLE");

        return new AppliedCandidatesDTO(candidateId,email,fullName,gender,expectedCtc,resumePath,totalExperience,noticePeriod,skills,profileRole,applicationId,appliedAt,jobRequestId,title);
    }
}
