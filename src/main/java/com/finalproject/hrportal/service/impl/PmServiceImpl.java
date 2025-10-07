package com.finalproject.hrportal.service.impl;


import com.finalproject.hrportal.dto.*;
import com.finalproject.hrportal.repository.EmployeeRepository;
import com.finalproject.hrportal.repository.JobRequestRepository;
import com.finalproject.hrportal.repository.ProjectRepository;
import com.finalproject.hrportal.service.PmService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PmServiceImpl implements PmService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final JobRequestRepository jobRequestRepository;

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO,String loginPmId) {
        return projectRepository.createProject(projectRequestDTO,loginPmId);
    }

    @Override
    public ProjectResponseDTO getAssignedProject(String pmId) {
        return projectRepository.getAssignedProject(pmId);
    }


    @Override
    public List<String> getEmployeeByRole(String role) {
        return employeeRepository.getEmployeeByRole(role.toUpperCase());
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesOnBench() {
        return employeeRepository.getEmployeesOnBench()
                .stream().map(employee -> modelMapper.map(employee,EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean setProjectIdToEmployee(int jobRequestId,int projectId,String employeeId) {
        int count =jobRequestRepository.updateHeadCount(jobRequestId);
        if(count >0) return projectRepository.setProjectIdToEmployee(projectId,employeeId);
        return false;
    }

    @Override
    public List<PmJobRequestResponseDTO> getAllJobRequestByPmId(String pmId) {
        return jobRequestRepository.getAllJobRequestByPmId(pmId)
                .stream()
                .map(requests->modelMapper.map(requests,PmJobRequestResponseDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public boolean forwardToHr(HrForwardDTO hrForwardDTO, int jobRequestId) {
        return jobRequestRepository.forwardToHr(hrForwardDTO,jobRequestId);
    }

    @Override
    public ProjectResponseDTO getProjectByIdWithTeamLeaderName(int projectId) {
        return  modelMapper.map(projectRepository.getProjectByIdWithTeamLeaderName(projectId),ProjectResponseDTO.class);
    }

    @Override
    public Integer getCountOfActiveProjects(String pmId) {
        return employeeRepository.getCountOfActiveProjects(pmId);
    }

    @Override
    public Integer getCountOfPendingRequest(String pmID) {
        return jobRequestRepository.getCountOfPendingRequest(pmID);
    }

    @Override
    public Integer getCountOfBenchEmployee() {
        return employeeRepository.getCountOfBenchEmployee();
    }

    @Override
    public Integer getTotalCountOfAllJobRequests(String loginPmId) {
        return jobRequestRepository.getTotalCountOfAllJobRequests(loginPmId);
    }

    @Override
    public Integer getCountOfApprovedJobRequests(String loginPmId) {
        return jobRequestRepository.getCountOfApprovedJobRequests(loginPmId);
    }

    @Override
    public Integer getCountOfClosedJobRequests(String loginPmId) {
        return jobRequestRepository.getCountOfClosedJobRequests(loginPmId);
    }

    @Override
    public List<EmployeeResponseDTO> getAllTeamMembers(int projectId) {
        return employeeRepository.getAllTeamMembers(projectId)
                .stream()
                .map(employee -> modelMapper.map(employee,EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean declinedTheJobRequest(int jobRequestId) {
        return jobRequestRepository.declinedTheJobRequest(jobRequestId);
    }

    @Override
    public Integer getCountOfDeclinedJobRequests(String loginPmId) {
        return jobRequestRepository.getCountOfDeclinedJobRequests(loginPmId);
    }
}
