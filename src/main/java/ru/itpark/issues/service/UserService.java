package ru.itpark.issues.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.issues.domain.User;
import ru.itpark.issues.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAll() {
        return repository.getAll();
    }

    public void save(User user) {
        repository.save(user);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
