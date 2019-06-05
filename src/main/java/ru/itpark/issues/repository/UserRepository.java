package ru.itpark.issues.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.issues.domain.User;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final NamedParameterJdbcTemplate template;

    public List<User> getAll() {
        return template.query("SELECT id, name FROM users",
                new BeanPropertyRowMapper<>(User.class));
    }

    public void save(User user) {
        if (user.getId() == 0) {
            template.update("INSERT INTO users (name) VALUES (:name)",
                    Map.of("name", user.getName()));
            return;
        }
        template.update("UPDATE users SET name = :name",
                Map.of("name", user.getName()));
    }

    public void delete(long id) {
        template.update("DELETE FROM users WHERE id = :id",
                Map.of("id", id));
    }
}
