package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.domain.Project;
import com.finalproject.hrportal.dto.ProjectRequestDTO;
import com.finalproject.hrportal.dto.ProjectResponseDTO;

import java.util.List;

public interface ProjectRepository {
    int getNextProjectId();
    List<Project> getAllProjects();
    ProjectResponseDTO getProjectByIdWithTeamLeaderName(int projectId);
    ProjectResponseDTO getAssignedProject(String pmId);
    Project getCurrentProjectById();
    boolean setProjectIdToEmployee(int projectId,String employeeId);
    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO,String loginPmId);

}
