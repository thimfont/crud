package br.com.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> all() {
        log.info("listing all users");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        log.info("creating new user {}", user);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user) {
        log.info("updating user with id {}", id);
        user.setId(id);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable long id) {
        log.info("deleting user with id {}", id);
        userRepository
                .findById(id)
                .ifPresent(user -> userRepository.delete(user));
        return ResponseEntity.ok().build();
    }
}
