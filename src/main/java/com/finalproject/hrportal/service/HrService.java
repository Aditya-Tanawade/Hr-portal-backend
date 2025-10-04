package com.finalproject.hrportal.service;

import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import com.finalproject.hrportal.dto.CandidateResponseDTO;
import com.finalproject.hrportal.dto.PmJobRequestResponseDTO;

import java.util.List;

public interface HrService {

    List<PmJobRequestResponseDTO> getAllJobRequestByHrId(String hrId);

    boolean postJobOnPortal(int jobRequestId);

    List<PmJobRequestResponseDTO> getJobRequestByHrIdAndPriority(String hrId, String priority);

    PmJobRequestResponseDTO getJobRequestById(int jobRequestId);

    List<CandidateResponseDTO> searchCandidates(int jobRequestId,CandidateFilterRequestDTO req);

    CandidateResponseDTO getCandidateByCandidateId(int candidateId);

    List<CandidateResponseDTO> getCandidatesByJobRequestId(int jobRequestId);

    boolean changeStatusOfCandidateToShortListed(int candidateId, int jobRequestId, String status);

    Integer getCountOfJobRequests(String hrId);

    Integer getCountOfPostedJobs(String hrId);

    Integer getCountOfAppliedCandidates(String hrId);

    Integer getCountOfShortlistedCandidates(String hrId);

    Integer getCountOfPendingJobRequests(String hrId);
}
