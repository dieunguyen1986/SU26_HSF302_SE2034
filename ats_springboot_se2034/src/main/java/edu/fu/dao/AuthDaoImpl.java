package edu.fu.dao;

import edu.fu.entities.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {
    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public Boolean isExisted(String email) {
        Session session = sessionFactory.getCurrentSession();
        Long count = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return count > 0;
    }

    @Override
    @Transactional
    public User login(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.email = :email AND u.passwordHash = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public User register(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        return user;
    }

}
