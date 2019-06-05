package ru.itpark.issues.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private long id;
    private long repoId;
    private String name;
    private String description;
    private LocalDate date;
    private int rate;
    private List<String> tags;
    private long ownerId;
    private long assignmentId;
}