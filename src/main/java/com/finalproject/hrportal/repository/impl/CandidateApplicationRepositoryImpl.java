package com.finalproject.hrportal.repository.impl;

import com.finalproject.hrportal.domain.enums.CandidateApplicationStatus;
import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.dto.ShortlistedCandidatesDTO;
import com.finalproject.hrportal.exceptions.ResourceNotFoundException;
import com.finalproject.hrportal.repository.CandidateApplicationRepository;
import com.finalproject.hrportal.rowmapper.AppliedCandidateRowMapper;
import com.finalproject.hrportal.rowmapper.ShortlistedCandidatesRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CandidateApplicationRepositoryImpl implements CandidateApplicationRepository {

    private final JdbcTemplate jdbcTemplate;



    @Override
    public boolean changeStatusOfCandidateToShortListed(int applicationId,String status) {
        String sql="UPDATE candidate_applications SET status=? WHERE application_id=?";
        return jdbcTemplate.update(sql, CandidateApplicationStatus.valueOf(status).toString(),applicationId)>0;
    }

    @Override
    public Integer getCountOfAppliedCandidates(String hrId) {
        String sql="SELECT COUNT(*) FROM candidate_applications where job_request_id IN  (select job_request_id from job_requests where hr_id=? AND status='POSTED')";
        return jdbcTemplate.queryForObject(sql,Integer.class,hrId);
    }

    @Override
    public Integer getCountOfShortlistedCandidates(String hrId) {
        String sql="SELECT COUNT(*) FROM candidate_applications where job_request_id IN  (select job_request_id from job_requests where hr_id=? AND status='POSTED')AND status='SHORTLISTED'";
        return jdbcTemplate.queryForObject(sql,Integer.class,hrId);
    }

    @Override
    public List<AppliedCandidatesDTO> getCandidatesByHrId(String hrId) {
        String sql="select c.candidate_Id,c.email,c.full_name,c.gender,c.expected_ctc,c.resume_path,\n" +
                "c.total_experience,c.skills,c.profile_role,c.notice_period,ca.application_id,ca.applied_at,j.job_request_id,j.title\n" +
                "From Candidates c\n" +
                "JOIN candidate_applications ca\n" +
                "ON c.candidate_id=ca.candidate_id\n" +
                "JOIN Job_requests j\n" +
                "ON ca.job_request_id=j.job_request_id\n" +
                "WHERE j.job_request_id IN(SELECT j.job_request_id from job_requests j where j.HR_id=? AND j.status='POSTED')\n" +
                "AND ca.status='APPLIED'";


        return jdbcTemplate.query(sql,new AppliedCandidateRowMapper(),hrId);

    }

    @Override
    public List<AppliedCandidatesDTO> getAppliedCandidates(String hrId) {
        String sql="select c.candidate_Id,c.email,c.full_name,c.gender,c.expected_ctc,c.resume_path,\n" +
                "c.total_experience,c.skills,c.profile_role,c.notice_period,ca.application_id,ca.applied_at,j.job_request_id,j.title\n" +
                "From Candidates c\n" +
                "JOIN candidate_applications ca\n" +
                "ON c.candidate_id=ca.candidate_id\n" +
                "JOIN Job_requests j\n" +
                "ON ca.job_request_id=j.job_request_id\n" +
                "WHERE j.job_request_id IN(SELECT j.job_request_id from job_requests j where j.HR_id=? AND j.status='POSTED')\n" +
                "AND ca.status='SHORTLISTED'";
        return jdbcTemplate.query(sql,new AppliedCandidateRowMapper(),hrId);
    }

    @Override
    public List<ShortlistedCandidatesDTO> getShortlistedCandidates(String hrId) {
        String sql="select c.candidate_Id,c.email,c.full_name,c.gender,c.expected_ctc,c.resume_path,\n" +
                "c.total_experience,c.skills,c.profile_role,c.notice_period,ca.application_id,ca.applied_at,j.job_request_id,j.title,i.status,i.remarks,i.score\n" +
                "From Candidates c\n" +
                "JOIN candidate_applications ca\n" +
                "ON c.candidate_id=ca.candidate_id\n" +
                "JOIN Job_requests j\n" +
                "ON ca.job_request_id=j.job_request_id\n" +
                "JOIN interviews i\n"+
                "ON ca.application_id=i.application_id"+
                "WHERE j.job_request_id IN(SELECT j.job_request_id from job_requests j where j.HR_id=? AND j.status='POSTED')\n" +
                "AND ca.status='SHORTLISTED'";
        return jdbcTemplate.query(sql,new ShortlistedCandidatesRowMapper(),hrId);
    }


}
