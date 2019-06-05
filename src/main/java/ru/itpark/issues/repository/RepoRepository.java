package ru.itpark.issues.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.issues.domain.Repo;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RepoRepository {
    private final NamedParameterJdbcTemplate template;

    public List<Repo> getAll() {
        return template.query("SELECT id, name, user_id FROM repositories",
                new BeanPropertyRowMapper<>(Repo.class));
    }

    public void save(Repo repo) {
        if (repo.getId() == 0) {
            template.update("INSERT INTO repositories (name, user_id) VALUES (:name, :user_id)",
                    Map.of("name", repo.getName(),
                            "user_id", repo.getUserId()));
            return;
        }
        template.update("UPDATE repositories SET name = :name, user_id = :user_id",
                Map.of("name", repo.getName(),
                        "user_id", repo.getUserId()));
    }

    public void delete(long id) {
        template.update("DELETE FROM repositories WHERE id = :id",
                Map.of("id", id));
    }
}
