package edu.fu.services;

import edu.fu.dto.UserRequest;
import edu.fu.entities.User;
import edu.fu.mapper.UserMapper;
import edu.fu.repositities.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;// WebApplicationContext
    private final UserMapper userMapper;

    @Override
    public User authenticate(UserRequest user) {
        return userRepository.findByEmailAndPasswordHash(user.getEmail(), user.getPassword()).orElseThrow(() ->
                new RuntimeException("Email or password is invalid!"));

    }

    @Override
    public User register(UserRequest user) {
        // Check email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already exists!");
        }

        return userRepository.save(userMapper.toEntity(user));
    }
}
