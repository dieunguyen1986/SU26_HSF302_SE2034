package edu.fu.dao;

import edu.fu.dto.CategorySkill;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillDaoImpl implements  SkillDao {
    private final SessionFactory sessionFactory;


    @Override
    public List<CategorySkill> getAll() {
        Session session = sessionFactory.openSession();

        return session.createQuery("SELECT new edu.fu.dto.CategorySkill(s.category, s.id, s.skillName) FROM Skill s", CategorySkill.class).getResultList();

    }
}
