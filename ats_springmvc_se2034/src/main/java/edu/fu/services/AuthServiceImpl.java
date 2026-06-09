package edu.fu.services;

import edu.fu.dao.AuthDao;
import edu.fu.dto.UserRequest;
import edu.fu.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService {
    private final AuthDao authDao;

    @Override
    public User authenticate(UserRequest user) {
        // Validate ?

        return authDao.login(user.getEmail(), user.getPassword());
    }

    @Override
    public User register(UserRequest user) {
        // Check email
        if(authDao.isExisted(user.getEmail())) {
            throw new IllegalArgumentException("User already exists!");
        }
        return null;
    }
}
