package edu.fu.dao;

import edu.fu.dto.CategoryResponse;
import edu.fu.dto.CategorySkill;

import java.util.List;

public interface SkillDao {
    List<CategorySkill> getAll();
}
