package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.dto.AppliedCandidatesDTO;

public interface IntervieweRepository {

    Integer getCountOfAssignedInterview(String hrId);

    Integer getCountOfSelectedCandidate(String hrId);

    Integer getCountOfRejectedCandidates(String hrId);
}
