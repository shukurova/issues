package ru.itpark.issues.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itpark.issues.domain.Issue;
import ru.itpark.issues.repository.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository repository;

    @GetMapping
    public List<Issue> getAll() {
        return repository.getAll();
    }

    @PostMapping
    public void save(@RequestBody Issue issue) {
        repository.save(issue);
    }

    @DeleteMapping
    public void delete(long id) {
        repository.delete(id);
    }

    @GetMapping
    public Issue getById(long id) {
        return repository.getById(id);
    }

    @PostMapping
    public List<Issue> findByParam(@RequestBody String param) {
        return repository.findByParam(param);
    }
}
