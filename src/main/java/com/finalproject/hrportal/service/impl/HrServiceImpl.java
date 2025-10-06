package com.finalproject.hrportal.service.impl;

import com.finalproject.hrportal.domain.Candidate;
import com.finalproject.hrportal.dto.AppliedCandidatesDTO;
import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import com.finalproject.hrportal.dto.CandidateResponseDTO;
import com.finalproject.hrportal.dto.PmJobRequestResponseDTO;
import com.finalproject.hrportal.repository.*;
import com.finalproject.hrportal.service.HrService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HrServiceImpl implements HrService {


    private final ModelMapper modelMapper;
    private final JobRequestRepository jobRequestRepository;
    private final CandidateRepository candidateRepository;
    private final CandidateApplicationRepository candidateApplicationRepository;
    private final IntervieweRepository intervieweRepository;


    @Override
    public List<PmJobRequestResponseDTO> getAllJobRequestByHrId(String hrId) {

        return jobRequestRepository.getAllJobRequestByHrId(hrId)
                .stream()
                .map(jobRequest -> modelMapper.map(jobRequest, PmJobRequestResponseDTO.class))
                .collect(Collectors.toList());
    }







    @Override
    public boolean postJobOnPortal(int jobRequestId) {
        return jobRequestRepository.postJobOnPortal(jobRequestId);
    }



    public boolean changeStatusOfCandidateToShortListed( int applicationId,String status){
        return candidateApplicationRepository.changeStatusOfCandidateToShortListed(applicationId,status);
    }

    @Override
    public Integer getCountOfJobRequests(String hrId) {
        return jobRequestRepository.getCountOfJobRequests(hrId);
    }

    @Override
    public Integer getCountOfPostedJobs(String hrId) {
        return jobRequestRepository.getCountOfPostedJobs( hrId);
    }

    @Override
    public Integer getCountOfAppliedCandidates(String hrId) {
        return candidateApplicationRepository.getCountOfAppliedCandidates(hrId);
    }

    @Override
    public Integer getCountOfShortlistedCandidates(String hrId) {
        return candidateApplicationRepository.getCountOfShortlistedCandidates(hrId);
    }

    @Override
    public Integer getCountOfPendingJobRequests(String hrId) {
        return jobRequestRepository.getCountOfPendingJobRequests(hrId);
    }

    @Override
    public List<AppliedCandidatesDTO> getCandidatesByHrId(String hrId) {
        return candidateApplicationRepository.getCandidatesByHrId(hrId);
    }

    @Override
    public List<AppliedCandidatesDTO> filterCandidates(String hrId, CandidateFilterRequestDTO request) {
        return candidateRepository.filterCandidates(hrId, request);
    }

    @Override
    public List<AppliedCandidatesDTO> getShortlistedCandidates(String hrId) {
        return candidateApplicationRepository.getShortlistedCandidates(hrId);
    }

    @Override
    public String addInterview(AppliedCandidatesDTO appliedCandidatesDTO) {
        return intervieweRepository.addInterview(appliedCandidatesDTO);
    }


}
