package io.github.anton_petrunov.telros_test.web;

import io.github.anton_petrunov.telros_test.model.Photo;
import io.github.anton_petrunov.telros_test.model.User;
import io.github.anton_petrunov.telros_test.repositoty.PhotoRepository;
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
import java.util.Set;

import static io.github.anton_petrunov.telros_test.util.ValidationUtil.*;

@RestController
@RequestMapping(value = PhotoRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PhotoRestController {
    static final String REST_URL = "/users/{userId}/photos";

    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    @GetMapping("{id}")
    public Photo get(@PathVariable Integer userId, @PathVariable Integer id) {
        log.info("get photo {} of user {}", id, userId);
        User user = checkAndGetUser(userId);
        return checkNotFoundWithId(photoRepository.getPhotoByUserIdAndId(user.getId(), id), id);
    }

    @GetMapping
    public Set<Photo> getAll(@PathVariable Integer userId) {
        log.info("getAll photos of user {}", userId);
        User user = checkAndGetUser(userId);
        return photoRepository.getPhotosByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer userId, @PathVariable Integer id) {
        log.info("delete photo {} of user {}", id, userId);
        User user = checkAndGetUser(userId);
        photoRepository.deleteByUserIdAndId(user.getId(), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Photo> create(@PathVariable Integer userId, @Valid @RequestBody Photo photo) {
        log.info("create photo {} of user {}", photo, userId);
        checkNew(photo);
        User user = checkAndGetUser(userId);

        if (photo.getUser() != null) {
            assureIdConsistent(photo.getUser(), userId);
        }
        photo.setUser(user);
        photo = photoRepository.save(photo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(photo);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer userId, @Valid @RequestBody Photo photo, @PathVariable Integer id) {
        log.info("update {} of user {} with id={}", photo, userId, id);
        Photo updated = get(userId, id);
        assureIdConsistent(photo, id);
        User user = updated.getUser();
        if (photo.getUser() != null) {
            assureIdConsistent(photo.getUser(), userId);
        }
        photo.setUser(user);
        photoRepository.save(photo);
    }

    private User checkAndGetUser(Integer userId) {
        return checkNotFoundWithId(userRepository.findById(userId).orElseThrow(), userId);
    }
}
