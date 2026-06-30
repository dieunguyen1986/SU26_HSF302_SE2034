package edu.fu.dao;

import edu.fu.dto.CategorySkill;

import java.util.List;

public interface SkillDao {
    List<CategorySkill> getAll();
}
