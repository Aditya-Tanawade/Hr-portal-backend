package com.finalproject.hrportal.rowmapper;


import com.finalproject.hrportal.domain.Candidate;
import com.finalproject.hrportal.domain.enums.CandidateGender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class CandidateRowMapper implements RowMapper<Candidate> {

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
         int candidateId=rs.getInt("CANDIDATE_ID");
         String email=rs.getString("EMAIL");
         String passwordHash=rs.getString("PASSWORD_HASH");
         String fullName=rs.getString("FULL_NAME");
         CandidateGender gender=CandidateGender.valueOf(rs.getString("GENDER"));
         double expectedCtc=rs.getDouble("EXPECTED_CTC");
         String resumePath=rs.getString("RESUME_PATH");
         double totalExperience=rs.getDouble("TOTAL_EXPERIENCE");
         int noticePeriod=rs.getInt("NOTICE_PERIOD");
         String currentCompany=rs.getString("CURRENT_COMPANY");
         String skills=rs.getString("SKILLS");
         String status=rs.getString("STATUS");
         LocalDate createdAt=rs.getDate("CREATED_AT").toLocalDate();
         LocalDate updatedAt=rs.getDate("UPDATED_AT").toLocalDate();
         String profileRole=rs.getString("PROFILE_ROLE");

         return new Candidate(candidateId,email,passwordHash,fullName,gender,expectedCtc,resumePath,totalExperience,noticePeriod,currentCompany,skills,status,createdAt,updatedAt,profileRole);
    }
}
