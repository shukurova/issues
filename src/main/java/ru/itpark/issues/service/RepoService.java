package ru.itpark.issues.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.issues.domain.Repo;
import ru.itpark.issues.repository.RepoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final RepoRepository repository;

    public List<Repo> getAll() {
        return repository.getAll();
    }

    public void save(Repo repo) {
        repository.save(repo);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
