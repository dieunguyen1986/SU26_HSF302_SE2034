package edu.fu.dao;

import edu.fu.entities.Job;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobDaoImpl implements JobDao {
    private final SessionFactory sessionFactory;

    @Override
    public Job findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Job.class, id);
        }
    }

    @Override
    public Job createJob(Job job) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                session.persist(job);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return job;
    }

    @Override
    public List<Job> findAllJobs() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Job> typedQuery = session.createQuery(
                    "SELECT j FROM Job j", Job.class);

            return typedQuery.getResultList();
        }
    }

    @Override
    public boolean isExisted(String title) {
        // Try with resources
        try (Session session = sessionFactory.openSession()) {
            Long result = session.createQuery("SELECT COUNT(j) FROM Job j WHERE j.title = :title", Long.class)
                    .setParameter("title", title)
                    .getSingleResult();

            return (result > 0);
        }
    }
}
