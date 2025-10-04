package com.finalproject.hrportal.dto;

import com.finalproject.hrportal.domain.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class    ProjectResponseDTO {

    private int projectId;
    private String name;
    private ProjectStatus status;
    private double budgetBandMin;
    private double budgetBandMax;
    private LocalDate startDate;
    private LocalDate endDate;
    private String teamLeadId;
    private String teamLeadName;
}
