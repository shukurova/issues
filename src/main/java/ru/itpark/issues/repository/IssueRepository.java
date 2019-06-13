package ru.itpark.issues.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.issues.domain.Issue;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class IssueRepository {
    private final NamedParameterJdbcTemplate template;

    public List<Issue> getAll() {
        return template.query("SELECT id, repo_id, name, description, date, rate, tags, owner_id, assignment_id FROM issues", (rs, i) -> new Issue(
                rs.getLong("id"),
                rs.getLong("repo_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDate("date"),
                rs.getInt("rate"),
                Arrays.asList((String[]) rs.getArray("tags").getArray()),
                rs.getLong("owner_id"),
                rs.getLong("assignment_id")
        ));
    }

    public void save(Issue issue) {
        if (issue.getId() == 0) {
            Array tags = null;
            try {
                tags = template.getJdbcTemplate().getDataSource()
                        .getConnection()
                        .createArrayOf("TEXT", issue.getTags().toArray());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            template.update("INSERT INTO issues (repo_id, name, description, tags, owner_id, assignment_id) VALUES (:repo_id, :name, :description, :tags, :owner_id, :assignment_id)",
                    Map.of("repo_id", issue.getRepoId(),
                            "name", issue.getName(),
                            "description", issue.getDescription(),
                            "tags", tags,
                            "owner_id", issue.getOwnerId(),
                            "assignment_id", issue.getAssignmentId()));
            return;
        }

        Array tags = null;
        try {
            tags = template.getJdbcTemplate().getDataSource()
                    .getConnection()
                    .createArrayOf("TEXT", issue.getTags().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        template.update("UPDATE issues SET repo_id = :repo_id, name = :name, description = :description, tags = :tags, owner_id = :owner_id, assignment_id = :assignment_id WHERE id = :id",
                Map.of("id", issue.getId(),
                        "repo_id", issue.getRepoId(),
                        "name", issue.getName(),
                        "description", issue.getDescription(),
                        "tags", tags,
                        "owner_id", issue.getOwnerId(),
                        "assignment_id", issue.getAssignmentId()));
    }

    public void delete(long id) {
        template.update("DELETE FROM issues WHERE id = :id",
                Map.of("id", id));
    }

    public List<Issue> findByParam(String param) {
        return template.query("SELECT id, repo_id, name, description, date, rate, tags, owner_id, assignment_id FROM issues WHERE name LIKE ( '%' || :param || '%') OR description LIKE ( '%' || :param || '%') OR (:param) = ANY(tags);",
                Map.of("param", param),
                (rs, i) -> new Issue(
                        rs.getLong("id"),
                        rs.getLong("repo_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("date"),
                        rs.getInt("rate"),
                        Arrays.asList((String[]) rs.getArray("tags").getArray()),
                        rs.getLong("owner_id"),
                        rs.getLong("assignment_id")
                ));
    }
}
