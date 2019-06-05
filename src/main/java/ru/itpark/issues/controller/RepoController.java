package ru.itpark.issues.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.issues.domain.Repo;
import ru.itpark.issues.service.RepoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/repos")
@RequiredArgsConstructor
public class RepoController {
    private final RepoService service;

    @GetMapping
    public List<Repo> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody Repo repo) {
        service.save(repo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
