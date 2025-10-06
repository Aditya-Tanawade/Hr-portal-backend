package com.finalproject.hrportal.rowmapper;

import com.finalproject.hrportal.domain.Interview;
import com.finalproject.hrportal.domain.enums.InterviewStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class InterviewRowMapper implements RowMapper<Interview> {
    @Override
    public Interview mapRow(ResultSet rs, int rowNum) throws SQLException {
        int interviewId=rs.getInt("INTERVIEW_ID");
        String interviewerId=rs.getString("Interviewer_ID");
        int applicationId=rs.getInt("APPLICATION_ID");
        int roundNumber=rs.getInt("ROUND_NUMBER");
        InterviewStatus status=InterviewStatus.valueOf(rs.getString("STATUS"));
        String remarks=rs.getString("REMARKS");
        double score=rs.getDouble("SCORE");
        LocalDate createdAt=rs.getDate("CREATED_AT").toLocalDate();
        LocalDate updatedAt=rs.getDate("UPDATED_AT").toLocalDate();
        String interviewLink=rs.getString("INTERVIEW_LINK");
        return new Interview(interviewId,interviewerId,applicationId,roundNumber,status,remarks,score,createdAt,updatedAt,interviewLink);
    }
}
