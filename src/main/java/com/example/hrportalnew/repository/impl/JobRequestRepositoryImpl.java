package com.finalproject.hrportal.repository.impl;

import com.finalproject.hrportal.domain.JobRequest;
import com.finalproject.hrportal.domain.enums.JobRequestStatus;
import com.finalproject.hrportal.dto.HrForwardDTO;
import com.finalproject.hrportal.dto.PmJobRequestResponseDTO;
import com.finalproject.hrportal.exceptions.ResourceNotFoundException;
import com.finalproject.hrportal.repository.JobRequestRepository;
import com.finalproject.hrportal.rowmapper.JobRequestRowMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobRequestRepositoryImpl implements JobRequestRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;

    @Override
    public List<JobRequest> getAllJobRequestByPmId(String pmId) {
        String sql="SELECT * FROM job_requests WHERE PROJECT_MANAGER_ID=?";
        return jdbcTemplate.query(sql, new JobRequestRowMapper(),pmId);
    }

    @Override
    public JobRequest getJobRequestById(int jobRequestId) {
        String sql ="SELECT * FROM job_requests WHERE job_request_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new JobRequestRowMapper(), jobRequestId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Project with ID " + jobRequestId + " not found");
        }
    }



    @Override
    public boolean forwardToHr(HrForwardDTO hrForwardDTO, int jobRequestId) {
        String sql = "UPDATE job_requests SET hr_id=?, status=?, priority=?, min_ctc=?, max_ctc=? WHERE job_request_id=?";

        int count = jdbcTemplate.update(
                sql,
                hrForwardDTO.getHrId(),
                JobRequestStatus.FORWARDED_TO_HR.toString(),
                hrForwardDTO.getPriority().toString(),
                hrForwardDTO.getMinCtc(),
                hrForwardDTO.getMaxCtc(),
                jobRequestId  // This is the missing parameter
        );

        return count > 0;
    }


    public int updateHeadCount(int jobRequestId){
        String sql="update job_requests set head_count=? where job_request_id=?";
        int headCount=getHeadCountBasedOnJobRequestId(jobRequestId);
        if(headCount>=1){
            headCount=headCount-1;
        }
      return jdbcTemplate.update(sql,headCount,jobRequestId);
    }



    public int getHeadCountBasedOnJobRequestId(int jobRequestId){
        String sql="Select head_count FROM job_requests WHERE job_request_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,jobRequestId);
    }

    public int getProjectIdByJobRequestId(int jobRequestId){
        String sql="Select project_id FROM job_requests WHERE job_request_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,jobRequestId);
    }




    // HR Portion

    @Override
    public List<JobRequest> getAllJobRequestByHrId(String hrId) {
        String sql="SELECT * FROM job_requests WHERE HR_ID=?";
        return jdbcTemplate.query(sql, new JobRequestRowMapper(),hrId);
    }

    @Override
    public boolean postJobOnPortal(int jobRequestId) {
        String sql="UPDATE job_requests SET status=? WHERE job_request_id=?";
        return jdbcTemplate.update(sql,JobRequestStatus.POSTED.toString(),jobRequestId)>0;
    }

    @Override
    public List<JobRequest> getJobRequestByHrIdAndPriority(String hrId, String priority) {
        String sql="SELECT * FROM job_requests WHERE hr_id=? AND priority=?";
        return jdbcTemplate.query(sql,new JobRequestRowMapper(),hrId,priority);
    }


}
