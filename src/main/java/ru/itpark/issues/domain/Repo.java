package ru.itpark.issues.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repo {
    private long id;
    private String name;
    private long userId;
}
