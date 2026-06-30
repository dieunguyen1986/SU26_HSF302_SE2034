package edu.fu.dao;

import edu.fu.entities.Department;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentDaoImpl implements DepartmentDao {

    private final SessionFactory sessionFactory;

//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//
//    }

    @Override
    public Department findById(long id) {
        // Connection
        Session session = sessionFactory.getCurrentSession();
        Department department = session.get(Department.class, id);
        return department;
    }

    @Override
    public List<Department> findByName(String name) {
        // Connection
        Session session = sessionFactory.getCurrentSession();

        Query<Department> query = session.createNamedQuery("findDepartmentByName", Department.class);
        query.setParameter("name", name);

        return query.getResultList();


    }

    @Override
    @Transactional
    public Department create(Department department) {

        Session session = sessionFactory.getCurrentSession();

        session.persist(department);

        return department;

    }

    @Override
    public Department update(Department department) {
        Session session = sessionFactory.getCurrentSession();
        if (session.find(Department.class, department.getId()) != null) {
            session.merge(department);
        }

        return department;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Department existedDepartment = session.find(Department.class, id);

        if (existedDepartment != null) {
            session.delete(existedDepartment);
        }

    }

    @Override
    public List<Department> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Department", Department.class).getResultList();
    }
}
