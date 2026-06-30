package edu.fu.dao;

import edu.fu.entities.Job;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobDaoImpl implements JobDao {
    private final SessionFactory sessionFactory;

    @Override
    public Job findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Job.class, id);

    }

    @Override
    public Job createJob(Job job) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(job);
        return job;
    }

    @Override
    public List<Job> findAllJobs() {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Job> typedQuery = session.createQuery(
                "SELECT j FROM Job j", Job.class);

        return typedQuery.getResultList();

    }

    @Override
    public boolean isExisted(String title) {
        // Session do Spring quản lý theo transaction nên không tự đóng ở đây
        Session session = sessionFactory.getCurrentSession();
        Long result = session.createQuery("SELECT COUNT(j) FROM Job j WHERE j.title = :title", Long.class)
                .setParameter("title", title)
                .getSingleResult();

        return (result > 0);
    }
}
