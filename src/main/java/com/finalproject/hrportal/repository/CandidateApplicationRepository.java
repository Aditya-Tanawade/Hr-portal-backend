package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.dto.ShortlistedCandidatesDTO;

import java.util.List;

public interface CandidateApplicationRepository {
    boolean changeStatusOfCandidateToShortListed(int applicationId,String status);

    Integer getCountOfAppliedCandidates(String hrId);

    Integer getCountOfShortlistedCandidates(String hrId);

    List<AppliedCandidatesDTO> getCandidatesByHrId(String hrId);

    List<AppliedCandidatesDTO> getAppliedCandidates(String hrId);

    List<ShortlistedCandidatesDTO> getShortlistedCandidates(String hrId);
}
