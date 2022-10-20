package io.github.anton_petrunov.telros_test.web;

import io.github.anton_petrunov.telros_test.model.User;
import io.github.anton_petrunov.telros_test.repositoty.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static io.github.anton_petrunov.telros_test.util.ValidationUtil.*;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserRestController {
    static final String REST_URL = "/users";

    private final UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
        log.info("get user {}", id);
        return checkNotFoundWithId(userRepository.findById(id).orElseThrow(), id);
    }

    @GetMapping
    public List<User> getAll() {
        log.info("getAll users");
        return userRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        log.info("create new user {}", user);
        checkNew(user);
        user = userRepository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable Integer id) {
        User updated = userRepository.findById(id).orElseThrow();
        checkNotFoundWithId(updated, id);
        assureIdConsistent(user, id);
        userRepository.save(user);
    }
}
