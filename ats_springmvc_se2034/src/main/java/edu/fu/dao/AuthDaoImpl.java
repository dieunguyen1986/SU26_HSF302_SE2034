package edu.fu.dao;

import edu.fu.entities.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {
    private final SessionFactory sessionFactory;

    @Override
    public Boolean isExisted(String email) {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return count > 0;
    }

    @Override
    public User login(String email, String password) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.email = :email AND u.passwordHash = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        return query.getSingleResult();
    }

    @Override
    public User register(User user) {
        Session session = sessionFactory.openSession();
        session.persist(user);
        return user;
    }

}
