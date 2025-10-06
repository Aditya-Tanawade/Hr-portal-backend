package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.dto.AppliedCandidatesDTO;

import java.util.List;

public interface CandidateApplicationRepository {
    boolean changeStatusOfCandidateToShortListed(int applicationId,String status);

    Integer getCountOfAppliedCandidates(String hrId);

    Integer getCountOfShortlistedCandidates(String hrId);

    List<AppliedCandidatesDTO> getCandidatesByHrId(String hrId);

    List<AppliedCandidatesDTO> getShortlistedCandidates(String hrId);
}
