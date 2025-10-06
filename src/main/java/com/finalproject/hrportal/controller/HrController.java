package com.finalproject.hrportal.controller;

import com.finalproject.hrportal.dto.*;
import com.finalproject.hrportal.service.HrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/hr")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HrController {

    private final HrService hrService;

    @GetMapping("/get-all/job-requests/{hrId}")
    public ResponseEntity<List<PmJobRequestResponseDTO>> getAllJobRequestByHrId(@PathVariable ("hrId")String hrId){
        return ResponseEntity.ok(hrService.getAllJobRequestByHrId(hrId));
    }


    @PatchMapping("/post/{jobRequestId}")
    public ResponseEntity<String>postJob(@PathVariable ("jobRequestId") int jobRequestId){
        if(hrService.postJobOnPortal(jobRequestId)){
            return ResponseEntity.status(HttpStatus.OK).body("Job Posted ON Portal");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed To Post JOb ON Portal");
    }


    @GetMapping("/filter-candidates/search/{hrId}")
    public ResponseEntity<List<AppliedCandidatesDTO>>getCandidatesByHrId(@PathVariable("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCandidatesByHrId(hrId));
    }

    @PostMapping("/filter-candidates/search/{hrId}")
    public ResponseEntity<List<AppliedCandidatesDTO>> filterCandidates(@PathVariable("hrId") String hrId, @RequestBody CandidateFilterRequestDTO request) {
        return ResponseEntity.ok(hrService.filterCandidates(hrId, request));
    }


    @PatchMapping("/candidates/change-status/{applicationId}")
    public ResponseEntity<String>changeStatusOfCandidateToShortListed(@PathVariable("applicationId") int applicationId,@RequestParam String status){
        if(hrService.changeStatusOfCandidateToShortListed(applicationId,status)){
            return ResponseEntity.status(HttpStatus.OK).body("Candidate " + status );
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error While ShortListing Candidate");
    }


    @GetMapping("/get-shortlisted-candidates")
    public ResponseEntity<List<AppliedCandidatesDTO>>getAppliedCandidates(@PathVariable("hrId") String hrId){
        return ResponseEntity.ok(hrService.getAppliedCandidates(hrId));
    }
//
//    @PostMapping("/add-interview")
//    public ResponseEntity<String>addInterview(@RequestBody AppliedCandidatesDTO appliedCandidatesDTO){
//        return ResponseEntity.ok(hrService.addInterview(appliedCandidatesDTO));
//    }

    //dashboard count
    @GetMapping("/dashboard/job-requests/{hrId}")
    public ResponseEntity<Integer>getCountOfJobRequests(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfJobRequests(hrId));
    }

    @GetMapping("/dashboard/posted-jobs/{hrId}")
    public ResponseEntity<Integer>getCountOfPostedJobs(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfPostedJobs(hrId));
    }



    @GetMapping("/dashboard/applied-candidates/{hrId}")
    public ResponseEntity<Integer>getCountOfAppliedCandidates(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfAppliedCandidates(hrId));
    }

    @GetMapping("/dashboard/shortlisted-candidates/{hrId}")
    public ResponseEntity<Integer>getCountOfShortlistedCandidates(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfShortlistedCandidates(hrId));
    }

    @GetMapping("/job-request-ui/pending-job-requests/{hrId}")
    public ResponseEntity<Integer>getCountOfPendingJobRequests(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfPendingJobRequests(hrId));
    }


    //shortlisted-candidates


    @GetMapping("/count/assigned-interview/{hrId}")
    public ResponseEntity<Integer>getCountOfAssignedInterview(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfAssignedInterview(hrId));
    }

    @GetMapping("/count/selected/{hrId}")
    public ResponseEntity<Integer>getCountOfSelectedCandidates(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfSelectedCandidate(hrId));
    }

    @GetMapping("/count/rejected/{hrId}")
    public ResponseEntity<Integer>getCountOfRejectedCandidates(@PathVariable ("hrId") String hrId){
        return ResponseEntity.ok(hrService.getCountOfRejectedCandidates(hrId));
    }


    @GetMapping("/getshortlisted/{hrId}")
    public ResponseEntity<List<ShortlistedCandidatesDTO>>getShortlistedCandidates(@PathVariable("hrId") String hrId){
        return ResponseEntity.ok(hrService.getShortlistedCandidates(hrId));
    }

}
