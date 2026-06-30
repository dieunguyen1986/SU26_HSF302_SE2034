package edu.fu.services;

import edu.fu.dto.CategoryResponse;
import edu.fu.dto.CategorySkill;
import edu.fu.dto.SkillResponse;
import edu.fu.repositities.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    @Override
    public List<CategoryResponse> getCategories() {

        List<CategorySkill> categorySkills = skillRepository.getAll();

        // Gom skill theo category. Dùng LinkedHashMap để giữ nguyên thứ tự câu query trả về
        Map<String, CategoryResponse> categories = new LinkedHashMap<>();
        for (CategorySkill categorySkill : categorySkills) {
            CategoryResponse category = categories.get(categorySkill.getCategoryName());

            // Lần đầu gặp category này thì tạo mới kèm list skill rỗng
            if (category == null) {
                category = new CategoryResponse();
                category.setCategoryName(categorySkill.getCategoryName());
                category.setSkills(new ArrayList<>());
                categories.put(categorySkill.getCategoryName(), category);
            }

            SkillResponse skillResponse = new SkillResponse();
            skillResponse.setId(categorySkill.getSkillId());
            skillResponse.setSkillName(categorySkill.getSkillName());

            category.getSkills().add(skillResponse);
        }

        return new ArrayList<>(categories.values());
    }
}
