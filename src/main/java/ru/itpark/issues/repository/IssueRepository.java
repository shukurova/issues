package ru.itpark.issues.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.issues.domain.Issue;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class IssueRepository {
    private final NamedParameterJdbcTemplate template;

    public List<Issue> getAll() {
        return template.query("SELECT id, name, description, date, rate, tags FROM issues",
                new BeanPropertyRowMapper<>(Issue.class));
    }

    public void save(Issue issue) {
        if (issue.getId() == 0) {
            template.update("INSERT INTO issues (name, description, date, rate, tags) VALUES (:name, :description, :date, :rate, :tags)",
                    Map.of("name", issue.getName(),
                            "description", issue.getDescription(),
                            "date", issue.getDate(),
                            "rate", issue.getRate(),
                            "tags", issue.getTags()));
            return;
        }
        template.update("UPDATE issues SET name = :name, description = :description, date = :date, rate = :rate, tags = :tags",
                Map.of("name", issue.getName(),
                        "description", issue.getDescription(),
                        "date", issue.getDate(),
                        "rate", issue.getRate(),
                        "tags", issue.getTags()));
    }

    public void delete(long id) {
        template.update("DELETE FROM issues WHERE id = :id",
                Map.of("id", id));
    }

    public Issue getById(long id) {
        return (Issue) template.query("SELECT id, name, description, date, rate, tags FROM issues WHERE id = :id",
                Map.of("id", id),
                new BeanPropertyRowMapper<>(Issue.class));
    }

    public List<Issue> findByParam(String param) {
        return template.query("SELECT id, name, description, date, rate, tags FROM issues WHERE name LIKE ('%' || :param || '%') OR description LIKE ('%' || :param || '%') OR tags LIKE ('%' || :param || '%')",
                Map.of("param", param),
                new BeanPropertyRowMapper<>(Issue.class));
    }
}
