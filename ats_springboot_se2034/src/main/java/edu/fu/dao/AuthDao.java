package edu.fu.dao;

import edu.fu.entities.User;

public interface AuthDao {
    Boolean isExisted(String email);
    User login(String email, String password);

    User register(User user);
}
