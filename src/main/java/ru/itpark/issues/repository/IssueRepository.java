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
        return template.query("SELECT id, repo_id, name, description, date, rate, tags[], owner_id, assignment_id FROM issues",
                new BeanPropertyRowMapper<>(Issue.class));
    }

    public void save(Issue issue) {
        if (issue.getId() == 0) {
            template.update("INSERT INTO issues (repo_id, name, description, tags, owner_id, assignment_id) VALUES (:name, :description, :tags)",
                    Map.of("repo_id", issue.getRepoId(),
                            "name", issue.getName(),
                            "description", issue.getDescription(),
                            "tags", issue.getTags(),
                            "owner_id", issue.getOwnerId(),
                            "assignment_id", issue.getAssignmentId()));
            return;
        }
        template.update("UPDATE issues SET repo_id = :repo_id, name = :name, description = :description, tags = :tags, owner_id = :owner_id, assignment_id = :assignment_id",
                Map.of("repo_id", issue.getRepoId(),
                        "name", issue.getName(),
                        "description", issue.getDescription(),
                        "tags", issue.getTags(),
                        "owner_id", issue.getOwnerId(),
                        "assignment_id", issue.getAssignmentId()));
    }

    public void delete(long id) {
        template.update("DELETE FROM issues WHERE id = :id",
                Map.of("id", id));
    }

    public List<Issue> findByParam(String param) {
        return template.query("SELECT id, name, description, date, rate, tags, owner_id, assignment_id FROM issues WHERE name LIKE ('%' || :param || '%') OR description LIKE ('%' || :param || '%') OR tags LIKE ('%' || :param || '%')",
                Map.of("param", param),
                new BeanPropertyRowMapper<>(Issue.class));
    }
}
