package ru.itpark.issues.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.issues.domain.Team;
import ru.itpark.issues.service.TeamService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService service;

    @GetMapping
    public List<Team> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody Team team) {
        service.save(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
