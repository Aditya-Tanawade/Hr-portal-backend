package com.finalproject.hrportal.domain;


import com.finalproject.hrportal.domain.enums.JobRequestPriority;
import com.finalproject.hrportal.domain.enums.JobRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {

    private int jobRequestId;
    private String teamLeaderId;
    private String projectManagerId;
    private String hrId;
    private int projectId;
    private String title;
    private String description;
    private JobRequestStatus status;
    private JobRequestPriority priority;
    private double minCtc;
    private double maxCtc;
    private String skills;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int headCount;
    private double minExperience;
    private double maxExperience;
    private String jobType;


}
