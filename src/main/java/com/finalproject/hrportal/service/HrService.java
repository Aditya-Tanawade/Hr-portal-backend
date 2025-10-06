package com.finalproject.hrportal.service;

import com.finalproject.hrportal.dto.*;

import java.util.List;

public interface HrService {

    List<PmJobRequestResponseDTO> getAllJobRequestByHrId(String hrId);

    boolean postJobOnPortal(int jobRequestId);


    boolean changeStatusOfCandidateToShortListed(int applicationId, String status);

    Integer getCountOfJobRequests(String hrId);

    Integer getCountOfPostedJobs(String hrId);

    Integer getCountOfAppliedCandidates(String hrId);

    Integer getCountOfShortlistedCandidates(String hrId);

    Integer getCountOfPendingJobRequests(String hrId);

    List<AppliedCandidatesDTO> getCandidatesByHrId(String hrId);

    List<AppliedCandidatesDTO> filterCandidates(String hrId, CandidateFilterRequestDTO request);

    List<AppliedCandidatesDTO> getAppliedCandidates(String hrId);

    Integer getCountOfAssignedInterview(String hrId);

    Integer getCountOfSelectedCandidate(String hrId);

    Integer getCountOfRejectedCandidates(String hrId);

    List<ShortlistedCandidatesDTO> getShortlistedCandidates(String hrId);
}
