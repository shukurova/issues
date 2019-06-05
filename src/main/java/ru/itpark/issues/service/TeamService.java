package ru.itpark.issues.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.issues.domain.Team;
import ru.itpark.issues.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository repository;

    public List<Team> getAll() {
        return repository.getAll();
    }

    public void save(Team team) {
        repository.save(team);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
