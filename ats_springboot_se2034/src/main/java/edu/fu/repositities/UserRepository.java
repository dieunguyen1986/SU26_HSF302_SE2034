package edu.fu.repositities;

import edu.fu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email); // FROM User u WHERE u.email = :email
    Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);
}
