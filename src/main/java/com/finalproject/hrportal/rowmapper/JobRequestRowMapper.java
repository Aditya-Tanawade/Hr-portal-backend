package com.finalproject.hrportal.rowmapper;

import com.finalproject.hrportal.domain.JobRequest;
import com.finalproject.hrportal.domain.enums.JobRequestPriority;
import com.finalproject.hrportal.domain.enums.JobRequestStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class JobRequestRowMapper implements RowMapper<JobRequest> {
    @Override
    public JobRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        int jobRequestId=rs.getInt("JOB_REQUEST_ID");
        String teamLeaderId=rs.getString("TEAM_LEADER_ID");
        String projectManagerId=rs.getString("PROJECT_MANAGER_ID");
        String hrId=rs.getString("HR_ID");
        int projectId=rs.getInt("PROJECT_ID");
        String title=rs.getString("TITLE");
        String description=rs.getString("DESCRIPTION");
        JobRequestStatus status=JobRequestStatus.valueOf(rs.getString("STATUS"));
        JobRequestPriority priority=JobRequestPriority.valueOf(rs.getString("PRIORITY"));
        double minCtc=rs.getDouble("MIN_CTC");
        double maxCtc=rs.getDouble("MAX_CTC");
        String skills=rs.getString("SKILLS");
        LocalDate createdAt=rs.getDate("CREATED_AT").toLocalDate();
        LocalDate updatedAt=rs.getDate("UPDATED_AT").toLocalDate();
        int headCount=rs.getInt("HEAD_COUNT");
        double minExperience=rs.getDouble("MIN_EXPERIENCE");
        double maxExperience=rs.getDouble("MAX_EXPERIENCE");
        String jobType=rs.getString("Job_Type");

        return new JobRequest(jobRequestId,teamLeaderId,projectManagerId,hrId,projectId,title,description,status,priority,minCtc,maxCtc,skills,createdAt,updatedAt,headCount,minExperience,maxExperience,jobType);
    }
}
