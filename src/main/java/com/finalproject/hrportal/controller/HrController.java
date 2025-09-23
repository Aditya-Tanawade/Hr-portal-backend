package com.finalproject.hrportal.controller;

import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import com.finalproject.hrportal.dto.CandidateResponseDTO;
import com.finalproject.hrportal.dto.PmJobRequestResponseDTO;
import com.finalproject.hrportal.service.HrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/hr/")
@RequiredArgsConstructor
public class HrController {

    private final HrService hrService;

    @GetMapping("/job-requests")
    public ResponseEntity<List<PmJobRequestResponseDTO>> getAllJobRequestByHrId(@RequestParam String hrId){
        return ResponseEntity.ok(hrService.getAllJobRequestByHrId(hrId));
    }

    @GetMapping("/job-request/{jobRequestId}")
    public ResponseEntity<PmJobRequestResponseDTO>getJobRequestById(@PathVariable ("jobRequestId") int jobRequestId){
        return ResponseEntity.ok(hrService.getJobRequestById(jobRequestId));
    }

    @PatchMapping("/post/{jobRequestId}")
    public ResponseEntity<String>postJob(@PathVariable ("jobRequestId") int jobRequestId){
        if(hrService.postJobOnPortal(jobRequestId)){
            return ResponseEntity.ok("JOB Posted ON Portal");
        }
        return ResponseEntity.ok("Failed To Post JOb ON Portal");
    }

    @GetMapping("/job-requests/{priority}")
    public ResponseEntity<List<PmJobRequestResponseDTO>> getAllJobRequestByHrIdAndPriority(@RequestParam String hrId,@PathVariable("priority")String priority){
        return ResponseEntity.ok(hrService.getJobRequestByHrIdAndPriority(hrId,priority));
    }




    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<CandidateResponseDTO>fetchCandidateByCandidateId(@PathVariable ("candidateId") int candidateId){
        return ResponseEntity.ok(hrService.getCandidateByCandidateId(candidateId));
    }

    @GetMapping("/get-candidates/search/{jobRequestId}")
    public ResponseEntity<List<CandidateResponseDTO>>getCandidatesBasedOnJobRequest(@PathVariable("jobRequestId") int jobRequestId){
        return ResponseEntity.ok(hrService.getCandidatesByJobRequestId(jobRequestId));
    }

    @PostMapping("/get-candidates/search/{jobRequestId}")
    public ResponseEntity<List<CandidateResponseDTO>> searchCandidatesForJob(@PathVariable("jobRequestId") int jobRequestId, @RequestBody CandidateFilterRequestDTO request) {
        return ResponseEntity.ok(hrService.searchCandidates(jobRequestId, request));
    }


    @PatchMapping("/get-candidates/search/{jobRequestId}/{candidateId}")
    public ResponseEntity<String>changeStatusOfCandidateToShortListed(@PathVariable("jobRequestId") int jobRequestId,@PathVariable("candidateId")int candidateId,@RequestParam String status){
        if(hrService.changeStatusOfCandidateToShortListed(jobRequestId,candidateId,status)){
            return ResponseEntity.ok("Candidate " +  status);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error While ShortListing Candidate");
    }








}
