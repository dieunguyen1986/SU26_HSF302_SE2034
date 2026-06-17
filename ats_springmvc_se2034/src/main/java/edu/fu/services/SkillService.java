package edu.fu.services;

import edu.fu.dto.CategoryResponse;

import java.util.List;

public interface SkillService {
    List<CategoryResponse> getCategories();
}
