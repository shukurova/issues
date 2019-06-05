package ru.itpark.issues.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.issues.domain.Issue;
import ru.itpark.issues.service.IssueService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService service;

    @GetMapping
    public List<Issue> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody Issue issue) {
        service.save(issue);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @GetMapping(params = "param")
    public List<Issue> findByParam(@RequestParam String param) {
        return service.findByParam(param);
    }
}
