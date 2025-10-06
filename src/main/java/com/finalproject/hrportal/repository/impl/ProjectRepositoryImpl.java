package com.finalproject.hrportal.repository.impl;

import com.finalproject.hrportal.domain.Project;
import com.finalproject.hrportal.dto.ProjectRequestDTO;
import com.finalproject.hrportal.dto.ProjectResponseDTO;
import com.finalproject.hrportal.exceptions.ResourceNotFoundException;
import com.finalproject.hrportal.repository.ProjectRepository;
import com.finalproject.hrportal.rowmapper.ProjectRowMapper;
import com.finalproject.hrportal.rowmapper.ProjectWithEmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_NEXT_PROJECT_ID="Select project_id_seq.NEXTVAL from dual";
    private static final String GET_ALL_PROJECT="SELECT * FROM projects";
    private static final String GET_PROJECT_BY_ID="SELECT * FROM projects WHERE project_id=?";
    private static final String CREATE_PROJECT="INSERT INTO projects(project_id,name, status, budget_band_min, budget_band_max, start_date, end_date) VALUES(?,?,?,?,?,?,?)";
    private static final String SET_PROJECT_ID="UPDATE employee_details SET project_id = ? WHERE employee_id=?";
    private static final String GET_PROJECT_WITH_EMPLOYEE =
            "SELECT p.project_id, p.name, p.status, p.budget_band_min, p.budget_band_max, " +
                    "p.start_date, p.end_date, p.created_at, p.updated_at, " +
                    "e.employee_id, e.full_name " +
                    "FROM projects p " +
                    "JOIN employee_details e ON e.project_id = p.project_id " +
                    "WHERE p.project_id=? AND e.employee_id=?";
    private static final String SET_PROJECT_ID_TO_MANAGER="UPDATE employee_details SET project_id = ? WHERE employee_id=?";


    @Override
    public int getNextProjectId() {
        return jdbcTemplate.queryForObject(GET_NEXT_PROJECT_ID,Integer.class);
    }


    @Override
    public ProjectResponseDTO getProjectByIdWithTeamLeaderName(int projectId) {
        String sql="SELECT e.employee_id, e.full_name, p.*\n" +
                "FROM employee_details e\n" +
                "INNER JOIN projects p ON p.project_id = e.project_id\n" +
                "WHERE p.project_id = ? AND e.role_key='TEAMLEAD'";
        try {
            return jdbcTemplate.queryForObject(sql, new ProjectWithEmployeeRowMapper(), projectId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Project with ID " + projectId + " not found");
        }
    }

    @Override
    public ProjectResponseDTO getAssignedProject(String pmId) {
        String sql="SELECT e.employee_id, e.full_name, p.*\n" +
                "FROM employee_details e\n" +
                "INNER JOIN projects p ON p.project_id = e.project_id\n" +
                "WHERE p.project_id = ? AND e.role_key='TEAMLEAD'";
        int projectId=getProjectIdOfPM(pmId);
        try {
            return jdbcTemplate.queryForObject(sql, new ProjectWithEmployeeRowMapper(), projectId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("NOt Assigned the Project To Team Lead");
        }

    }

    private int getProjectIdOfPM(String pmId){
        String sql="Select project_id from employee_details where employee_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,pmId);
    }



    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO,String loginPmId) {
        int projectId = getNextProjectId();
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();

        int count = jdbcTemplate.update(CREATE_PROJECT,
                projectId,
                projectRequestDTO.getName(),
                projectRequestDTO.getStatus().toString().toUpperCase(),
                projectRequestDTO.getBudgetBandMin(),
                projectRequestDTO.getBudgetBandMax(),
                projectRequestDTO.getStartDate(),
                projectRequestDTO.getEndDate());

        if (count > 0) {
            setProjectIdToManager(loginPmId, projectId);
            setProjectIdToEmployee(projectId,projectRequestDTO.getTeamLeadId());
            return jdbcTemplate.queryForObject(GET_PROJECT_WITH_EMPLOYEE, new ProjectWithEmployeeRowMapper(), projectId,projectRequestDTO.getTeamLeadId());

        }
        return null;
    }




    @Override
     public boolean setProjectIdToEmployee(int projectId,String employeeId){
        return jdbcTemplate.update(SET_PROJECT_ID, projectId, employeeId) > 0;
    }




    private boolean setProjectIdToManager(String employeeId, int projectId){
        return jdbcTemplate.update(SET_PROJECT_ID_TO_MANAGER, projectId, employeeId) > 0;
    }

}
