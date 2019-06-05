package ru.itpark.issues.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.issues.domain.Repo;
import ru.itpark.issues.domain.Team;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TeamRepository {
    private final NamedParameterJdbcTemplate template;

    public List<Team> getAll() {
        return template.query("SELECT id, user_id, repo_id FROM teams",
                new BeanPropertyRowMapper<>(Team.class));
    }

    public void save(Team team) {
        if (team.getId() == 0) {
            template.update("INSERT INTO teams (user_id, repo_id) VALUES (:user_id, :repo_id)",
                    Map.of("user_id", team.getUserId(),
                            "repo_id", team.getRepoId()));
            return;
        }
        template.update("UPDATE teams SET user_id = :user_id, repo_id = :repo_id",
                Map.of("user_id", team.getUserId(),
                        "repo_id", team.getRepoId()));
    }

    public void delete(long id) {
        template.update("DELETE FROM teams WHERE id = :id",
                Map.of("id", id));
    }
}
