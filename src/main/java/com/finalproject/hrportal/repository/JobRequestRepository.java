package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.domain.JobRequest;
import com.finalproject.hrportal.dto.HrForwardDTO;

import java.util.List;

public interface JobRequestRepository {

   List<JobRequest> getAllJobRequestByPmId(String pmId);
   JobRequest getJobRequestById(int jobRequestId);
   boolean forwardToHr(HrForwardDTO hrForwardDTO, int jobRequestId);
   int getHeadCountBasedOnJobRequestId(int jobRequestId);
   int getProjectIdByJobRequestId(int jobRequestId);
   int updateHeadCount(int jobRequestId);
   List<JobRequest> getAllJobRequestByHrId(String hrId);
   boolean postJobOnPortal(int jobRequestId);
   List<JobRequest> getJobRequestByHrIdAndPriority(String hrId, String priority);

    Integer getCountOfPendingRequest(String pmID);

   Integer getTotalCountOfAllJobRequests(String loginPmId);

   Integer getCountOfApprovedJobRequests(String loginPmId);

   Integer getCountOfClosedJobRequests(String loginPmId);
}
