package com.finalproject.hrportal.strategy;

import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExperienceFilterStrategy implements CandidateFilterStrategy {
    @Override
    public Optional<String> createCondition(CandidateFilterRequestDTO req, MapSqlParameterSource params) {
        List<String> parts = new ArrayList<>();

        if (req.getMinExperience() != null) {
            parts.add("c.total_experience >= :minExp");
            params.addValue("minExp", req.getMinExperience());
        }
        if (req.getMaxExperience() != null) {
            parts.add("c.total_experience <= :maxExp");
            params.addValue("maxExp", req.getMaxExperience());
        }

        return parts.isEmpty() ? Optional.empty() : Optional.of(String.join(" AND ", parts));
    }
}
