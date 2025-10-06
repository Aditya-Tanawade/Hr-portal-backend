package com.finalproject.hrportal.strategy;


import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NoticePeriodFilterStrategy implements CandidateFilterStrategy {
    @Override
    public Optional<String> createCondition(CandidateFilterRequestDTO req, MapSqlParameterSource params) {
        if (req.getNoticePeriod() != null) {
            params.addValue("noticePeriod", req.getNoticePeriod());
            return Optional.of("c.notice_period <= :noticePeriod");
        }
        return Optional.empty();
    }
}


