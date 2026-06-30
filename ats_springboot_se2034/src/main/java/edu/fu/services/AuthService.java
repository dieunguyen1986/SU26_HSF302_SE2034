package edu.fu.services;

import edu.fu.dto.UserRequest;
import edu.fu.entities.User;

public interface AuthService {
    User authenticate(UserRequest user);

    User register(UserRequest user);
}
