package io.github.anton_petrunov.telros_test.web;

import io.github.anton_petrunov.telros_test.model.Contact;
import io.github.anton_petrunov.telros_test.model.User;
import io.github.anton_petrunov.telros_test.repositoty.ContactRepository;
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
@RequestMapping(value = ContactRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class ContactRestController {
    static final String REST_URL = "/users/{userId}/contacts";

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @GetMapping("/{id}")
    public Contact get(@PathVariable Integer userId, @PathVariable Integer id) {
        log.info("get contact {} of user {}", id, userId);
        User user = checkAndGetUser(userId);
        return checkNotFoundWithId(contactRepository.getContactByUserIdAndId(user.getId(), id), id);
    }

    @GetMapping
    public List<Contact> getAll(@PathVariable Integer userId) {
        log.info("getAll contacts of user {}", userId);
        User user = checkAndGetUser(userId);
        return contactRepository.getContactsByUserId(user.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer userId, @PathVariable Integer id) {
        log.info("delete contact {} of user {}", id, userId);
        User user = checkAndGetUser(userId);
        contactRepository.deleteByUserIdAndId(user.getId(), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contact> create(@PathVariable Integer userId, @Valid @RequestBody Contact contact) {
        log.info("create contact {} of user {}", contact, userId);
        checkNew(contact);
        User user = checkAndGetUser(userId);

        if (contact.getUser() != null) {
            assureIdConsistent(contact.getUser(), userId);
        }
        contact.setUser(user);
        contact = contactRepository.save(contact);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(contact);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer userId, @Valid @RequestBody Contact contact, @PathVariable Integer id) {
        log.info("update {} of user {} with id={}", contact, userId, id);
        Contact updated = get(userId, id);
        assureIdConsistent(contact, id);
        User user = updated.getUser();
        if (contact.getUser() != null) {
            assureIdConsistent(contact.getUser(), userId);
        }
        contact.setUser(user);
        contactRepository.save(contact);
    }

    private User checkAndGetUser(Integer userId) {
        return checkNotFoundWithId(userRepository.findById(userId).orElseThrow(), userId);
    }
}
