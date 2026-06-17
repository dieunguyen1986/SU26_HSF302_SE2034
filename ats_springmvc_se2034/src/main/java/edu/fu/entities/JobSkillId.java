package edu.fu.entities;

import lombok.*;

import java.io.Serializable;

// Khóa kép cho bảng job_skills, tên field phải trùng tên thuộc tính @Id bên JobSkill
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JobSkillId implements Serializable {
    private Long skill; // map theo skill_id
    private Long job;   // map theo job_id
}
