package com.finalproject.hrportal.rowmapper;

import com.finalproject.hrportal.domain.enums.ProjectStatus;
import com.finalproject.hrportal.dto.ProjectResponseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectWithEmployeeRowMapper implements RowMapper<ProjectResponseDTO> {

    @Override
    public ProjectResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setProjectId(rs.getInt("project_id"));
        dto.setName(rs.getString("name"));
        dto.setStatus(ProjectStatus.valueOf(rs.getString("status")));
        dto.setBudgetBandMin(rs.getDouble("budget_band_min"));
        dto.setBudgetBandMax(rs.getDouble("budget_band_max"));
        dto.setStartDate(rs.getDate("start_date").toLocalDate());
        dto.setEndDate(rs.getDate("end_date").toLocalDate());
        dto.setTeamLeadId(rs.getString("employee_id"));
        dto.setTeamLeadName(rs.getString("full_name"));
        return dto;
    }
}
