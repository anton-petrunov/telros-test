package io.github.anton_petrunov.telros_test.repositoty;

import io.github.anton_petrunov.telros_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
