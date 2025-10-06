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
        String teamLeaderId=rs.getString("TEAM_LEADER_ID");
        String projectManagerId=rs.getString("PROJECT_MANAGER_ID");
        String hrId=rs.getString("HR_ID");
        int applicationId=rs.getInt("APPLICATION_ID");
        int candidateId=rs.getInt("CANDIDATE_ID");
        int jobRequestId=rs.getInt("JOB_REQUEST_ID");
        int roundNumber=rs.getInt("ROUND_NUMBER");
        InterviewStatus status=InterviewStatus.valueOf(rs.getString("STATUS"));
        String remarks=rs.getString("REMARKS");
        double score=rs.getDouble("SCORE");
        String googleMeetLink=rs.getString("GOOGLE_MEET_LINK");
        LocalDate interviewDate=rs.getDate("INTERVIEW_DATE").toLocalDate();
        String startTime=rs.getString("START_TIME");
        String endTime=rs.getString("END_TIME");

        LocalDate createdAt=rs.getDate("CREATED_AT").toLocalDate();
        LocalDate updatedAt=rs.getDate("UPDATED_AT").toLocalDate();
        String interviewLink=rs.getString("INTERVIEW_LINK");
        return new Interview(interviewId,teamLeaderId,projectManagerId,hrId,applicationId,candidateId,jobRequestId,roundNumber,status,remarks,score,googleMeetLink,interviewDate,startTime,endTime,createdAt,updatedAt);
    }
}
