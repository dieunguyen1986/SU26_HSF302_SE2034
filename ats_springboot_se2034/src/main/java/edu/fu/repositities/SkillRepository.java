package edu.fu.repositities;

import edu.fu.dto.CategorySkill;
import edu.fu.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT new edu.fu.dto.CategorySkill(s.category, s.id, s.skillName) FROM Skill s")
    List<CategorySkill> getAll();
}
