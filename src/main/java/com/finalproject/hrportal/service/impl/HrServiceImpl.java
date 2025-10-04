package com.finalproject.hrportal.service.impl;

import com.finalproject.hrportal.domain.Candidate;
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


    @Override
    public List<PmJobRequestResponseDTO> getAllJobRequestByHrId(String hrId) {

        return jobRequestRepository.getAllJobRequestByHrId(hrId)
                .stream()
                .map(jobRequest -> modelMapper.map(jobRequest, PmJobRequestResponseDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public PmJobRequestResponseDTO getJobRequestById(int jobRequestId) {
        return modelMapper.map(jobRequestRepository.getJobRequestById(jobRequestId),PmJobRequestResponseDTO.class);
    }

    @Override
    public List<CandidateResponseDTO> searchCandidates(int jobRequestId,CandidateFilterRequestDTO req) {

        return candidateRepository.searchCandidates(jobRequestId, req)
                .stream()
                .map(candidate -> modelMapper.map(candidate, CandidateResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponseDTO getCandidateByCandidateId(int candidateId) {
        return modelMapper.map(candidateRepository.getCandidateById(candidateId),CandidateResponseDTO.class);
    }



    @Override
    public boolean postJobOnPortal(int jobRequestId) {
        return jobRequestRepository.postJobOnPortal(jobRequestId);
    }

    @Override
    public List<PmJobRequestResponseDTO> getJobRequestByHrIdAndPriority(String hrId, String priority) {
        return jobRequestRepository.getJobRequestByHrIdAndPriority(hrId,priority)
                .stream()
                .map(jobRequest -> modelMapper.map(jobRequest, PmJobRequestResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateResponseDTO> getCandidatesByJobRequestId(int jobRequestId) {
        List<Candidate>candidates=new ArrayList<>();
        List<Integer>candidatesId=candidateApplicationRepository.getCandidateIdsBasedOnJobRequestId(jobRequestId);
        for(int i:candidatesId){
            candidates.add(candidateRepository.getCandidateById(i));
        }
        return candidates.stream().map(c->modelMapper.map(c,CandidateResponseDTO.class)).collect(Collectors.toList());
    }


    public boolean changeStatusOfCandidateToShortListed(int jobRequestId, int candidateId,String status){
        System.out.println("In Service"+ candidateApplicationRepository.changeStatusOfCandidateToShortListed(jobRequestId,candidateId,status));
        return candidateApplicationRepository.changeStatusOfCandidateToShortListed(jobRequestId,candidateId,status);
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


}
