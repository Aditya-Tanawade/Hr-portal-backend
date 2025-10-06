package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.domain.Candidate;
import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;

import java.util.Arrays;
import java.util.List;

public interface CandidateRepository {
    List<AppliedCandidatesDTO> filterCandidates(String hrId, CandidateFilterRequestDTO request);
}
