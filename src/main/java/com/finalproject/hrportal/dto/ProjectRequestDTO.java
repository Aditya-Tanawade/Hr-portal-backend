package com.finalproject.hrportal.dto;

import com.finalproject.hrportal.domain.enums.ProjectStatus;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectRequestDTO {
    private int projectId;

    @NotBlank(message = "Project Name is required")
    private String name;

    @NotNull(message = "Project Status is required")
    private ProjectStatus status;

    @DecimalMin(value = "20.00", message = "Budget Min should be at least 20 Lakhs")
    @Digits(integer = 3, fraction = 2, message = "Budget Min should have max 3 digits and 2 decimals")
    private double budgetBandMin;

    @DecimalMax(value = "100.99", message = "Budget Max should be less than 100 Lakhs")
    @Digits(integer = 3, fraction = 2, message = "Budget Max should have max 3 digits and 2 decimals")
    private double budgetBandMax;

    @FutureOrPresent(message = "Project Start Date should be today or future date")
    private LocalDate startDate;

    @Future(message = "Project End Date should be in the future")
    private LocalDate endDate;

    @NotBlank(message = "Team Lead ID is required")
    private String teamLeadId;

}
