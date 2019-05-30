package ru.itpark.issues.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.issues.domain.Issue;
import ru.itpark.issues.service.IssueService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/issues")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService service;

    public List<Issue> getAll() {
        return service.getAll();
    }

    public void save(Issue issue) {
        service.save(issue);
    }

    public void delete(long id) {
        service.delete(id);
    }

    public Issue getById(long id) {
       return service.getById(id);
    }

    public List<Issue> findByParam(String param) {
        return service.findByParam(param);
    }
}
