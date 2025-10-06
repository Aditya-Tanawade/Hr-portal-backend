package com.finalproject.hrportal.strategy;

import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CtcFilterStrategy implements CandidateFilterStrategy {
    @Override
    public Optional<String> createCondition(CandidateFilterRequestDTO req, MapSqlParameterSource params) {
        List<String> parts = new ArrayList<>();

        if (req.getMinExpectedCtc() != null) {
            parts.add("c.expected_ctc >= :minCtc");
            params.addValue("minCtc", req.getMinExpectedCtc());
        }
        if (req.getMaxExpectedCtc() != null) {
            parts.add("c.expected_ctc <= :maxCtc");
            params.addValue("maxCtc", req.getMaxExpectedCtc());
        }

        return parts.isEmpty() ? Optional.empty() : Optional.of(String.join(" AND ", parts));
    }
}


