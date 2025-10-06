package com.finalproject.hrportal.service;

import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import com.finalproject.hrportal.dto.CandidateResponseDTO;
import com.finalproject.hrportal.dto.PmJobRequestResponseDTO;

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

    List<AppliedCandidatesDTO> getShortlistedCandidates(String hrId);

    String addInterview(AppliedCandidatesDTO appliedCandidatesDTO);
}
