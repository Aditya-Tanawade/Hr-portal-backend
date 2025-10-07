package com.finalproject.hrportal.controller;


import com.finalproject.hrportal.dto.*;
import com.finalproject.hrportal.service.PmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/pm/view")
@RestController
@CrossOrigin("*")
public class PmController {

    private final PmService pmService;

    //To Create Project
    @PostMapping("/add")
    public ResponseEntity<ProjectResponseDTO> createProjectDTO(@RequestBody @Valid ProjectRequestDTO projectRequestDTO, @RequestParam String loginPmId){
        return ResponseEntity.status(HttpStatus.CREATED).body(pmService.createProject(projectRequestDTO,loginPmId));
    }

    @GetMapping("/assigned-project")
    public ResponseEntity<ProjectResponseDTO>getAssignedProject(    @RequestParam String loginPmId){
        return ResponseEntity.ok(pmService.getAssignedProject(loginPmId));
    }


    //To Get TEAMLEAD TO ASSIGN PROJECT
    @GetMapping("/get/{role}")
    public ResponseEntity<List<String>> getEmployeeByRole(@PathVariable("role") String role){
        return ResponseEntity.ok( pmService.getEmployeeByRole(role));
    }

    //TO GET EMPLOYEES ON BENCH
    @GetMapping("/bench")
    public ResponseEntity<List<EmployeeResponseDTO>>getAllEmployeesOnBench(){
        return ResponseEntity.ok(pmService.getEmployeesOnBench());
    }

    //TO Get ALL JobRequestByPmID
    @GetMapping("/job-requests/{pmId}")
    public ResponseEntity<List<PmJobRequestResponseDTO>>getAllJobRequestByPmId(@PathVariable ("pmId")String pmId){
        return ResponseEntity.ok(pmService.getAllJobRequestByPmId(pmId));
    }

    //TO ASSIGN PROJECT TO RESPECTIVE EMPLOYEE ON BENCH reduce headCount
    @PatchMapping("/{jobRequestId}/assign-project")
    public ResponseEntity<String>assignProject(@PathVariable("jobRequestId") int jobRequestId,@RequestParam int projectId,@RequestParam String employeeId){
        System.out.println(jobRequestId);
        if(pmService.setProjectIdToEmployee(jobRequestId,projectId,employeeId)){
            return ResponseEntity.ok("Project Assigned TO Employee Having Id " + employeeId);
        }
        return ResponseEntity.badRequest().body("Project Not Assigned To Employee");
    }

    @PatchMapping("/forward-hr/{jobRequestId}")
    public ResponseEntity<String> ForwardTohr(@RequestBody HrForwardDTO hrForwardDTO, @PathVariable("jobRequestId")int jobRequestId){
        if(pmService.forwardToHr(hrForwardDTO,jobRequestId)){
            return ResponseEntity.ok("Forwarded To Hr");
        }
        return ResponseEntity.ok("Failed To Forward ");
    }

    @PatchMapping("decline/job-request/{jobRequestId}")
    public ResponseEntity<String>declinedTheJobRequest(@PathVariable("jobRequestId")int jobRequestId){
        if(pmService.declinedTheJobRequest(jobRequestId)){
            return ResponseEntity.ok("Job request with id   Successfully  ");
        }
        return ResponseEntity.ok("Failed To Decline The Request");
    }






//    //To Display Project Details Based ON Id
//    @GetMapping("/getProject/{projectId}")
//    public ResponseEntity<ProjectResponseDTO> getProjectByIdWithTeamLeaderName(@PathVariable("projectId") int projectId){
//        return ResponseEntity.ok(pmService.getProjectByIdWithTeamLeaderName(projectId));
//    }


    @GetMapping("/count/active-projects/{pmId}")
    public ResponseEntity<Integer> getCountOfActiveProjects(@PathVariable ("pmId") String pmId){
        return ResponseEntity.ok(pmService.getCountOfActiveProjects(pmId));
    }



    @GetMapping("/count/pending-request/{pmId}")
    public  ResponseEntity<Integer>  getCountOfPendingRequest(@PathVariable ("pmId") String pmID){
        return ResponseEntity.ok(pmService.getCountOfPendingRequest(pmID));
    }


    @GetMapping("/count/bench-employee")
    public ResponseEntity<Integer> getCountOfBenchEmployee(){
        return ResponseEntity.ok(pmService.getCountOfBenchEmployee());
    }


//    @GetMapping("/count/upcoming-interview")
//    public ResponseEntity<Integer>getCountOfUpcomingInterview(@PathVariable ("pmId") String pmID){
//
//    }


    @GetMapping("/count/job-request/all/{loginPmId}")
    public ResponseEntity<Integer>getTotalCountOfAllJobRequests(@PathVariable ("loginPmId") String loginPmId){
        return ResponseEntity.ok(pmService.getTotalCountOfAllJobRequests(loginPmId));
    }


    @GetMapping("/count/job-request/approved/{loginPmId}")
    public ResponseEntity<Integer>getCountOfApprovedJobRequests(@PathVariable ("loginPmId") String loginPmId){
        return ResponseEntity.ok(pmService.getCountOfApprovedJobRequests(loginPmId));
    }

    @GetMapping("/count/job-request/closed/{loginPmId}")
    public ResponseEntity<Integer>getCountOfClosedJobRequests(@PathVariable ("loginPmId") String loginPmId){
        return ResponseEntity.ok(pmService.getCountOfClosedJobRequests(loginPmId));
    }

    @GetMapping("/count/job-request/declined/{loginPmId}")
    public ResponseEntity<Integer>getCountOfDeclinedJobRequests(@PathVariable ("loginPmId") String loginPmId){
        return ResponseEntity.ok(pmService.getCountOfDeclinedJobRequests(loginPmId));
    }




        @GetMapping("/get-all-team-members/{projectId}")
    public ResponseEntity<List<EmployeeResponseDTO>>getAllTeamMembers(@PathVariable("projectId")int projectId){
        return ResponseEntity.ok(pmService.getAllTeamMembers(projectId));
    }
}
