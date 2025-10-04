package com.finalproject.hrportal.service;


import com.finalproject.hrportal.dto.*;

import java.util.List;

public interface PmService {
    ProjectResponseDTO createProject( ProjectRequestDTO projectRequestDTO,String loginPmId);
    ProjectResponseDTO getAssignedProject(String pmId);

    List<String> getEmployeeByRole(String role);
    List<EmployeeResponseDTO> getEmployeesOnBench();
    boolean setProjectIdToEmployee(int jobRequestId,int projectId,String employeeId);
    List<PmJobRequestResponseDTO> getAllJobRequestByPmId(String pmId);
    boolean forwardToHr(HrForwardDTO hrForwardDTO, int jobRequestId);
    ProjectResponseDTO getProjectByIdWithTeamLeaderName(int projectId);

    Integer getCountOfActiveProjects(String pmId);
    Integer getCountOfPendingRequest(String pmID);
    Integer getCountOfBenchEmployee();

    Integer getTotalCountOfAllJobRequests(String loginPmId);

    Integer getCountOfApprovedJobRequests(String loginPmId);

    Integer getCountOfClosedJobRequests(String loginPmId);

    List<EmployeeResponseDTO> getAllTeamMembers(int projectId);

    boolean declinedTheJobRequest(int jobRequestId);
}
