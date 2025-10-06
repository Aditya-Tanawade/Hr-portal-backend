package com.finalproject.hrportal.strategy;

import com.finalproject.hrportal.dto.CandidateFilterRequestDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SkillFilterStrategy implements CandidateFilterStrategy {
    @Override
    public Optional<String> createCondition(CandidateFilterRequestDTO req, MapSqlParameterSource params) {
        if (req.getSkills() == null || req.getSkills().isEmpty()) return Optional.empty();

        List<String> skills = req.getSkills().stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        if (skills.isEmpty()) return Optional.empty();

        StringBuilder cond = new StringBuilder("(");
        for (int i = 0; i < skills.size(); i++) {
            String param = "skill" + i;
            cond.append("LOWER(c.skills) LIKE :" + param);
            params.addValue(param, "%" + skills.get(i).toLowerCase() + "%");
            if (i < skills.size() - 1) cond.append(" OR ");
        }
        cond.append(")");
        return Optional.of(cond.toString());
    }
}


