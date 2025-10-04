package com.finalproject.hrportal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppliedCandidatesDTO {

    private int candidateId;

//    select c.candidate_Id,c.email,c.full_name,c.gender,c.expected_ctc,c.resume_path,
//    c.total_experience,c.skills,c.profile_role,c.notice_period,ca.application_id,ca.applied_at,j.title
//    From Candidates c
//    JOIN candidate_applications ca
//    ON c.candidate_id=ca.candidate_id
//    JOIN Job_requests j
//    ON ca.job_request_id=j.job_request_id
//    ;

}
