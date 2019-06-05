package ru.itpark.issues.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.issues.domain.Issue;
import ru.itpark.issues.repository.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository repository;

    public List<Issue> getAll() {
        return repository.getAll();
    }

    public void save(Issue issue) {
        repository.save(issue);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public List<Issue> findByParam(String param) {
        return repository.findByParam(param);
    }
}
