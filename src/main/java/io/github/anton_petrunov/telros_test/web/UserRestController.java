package io.github.anton_petrunov.telros_test.web;

import io.github.anton_petrunov.telros_test.model.User;
import io.github.anton_petrunov.telros_test.repositoty.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
@Slf4j
public class UserRestController {

    private final UserRepository userRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> get(@PathVariable Integer id) {
        log.info("get user {}", id);
        return userRepository.findById(id);
    }
}
