package edu.fu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "skills")
// Tên named query phải duy nhất toàn app nên prefix bằng tên entity để khỏi đụng với entity khác
@NamedQueries({@NamedQuery(name = "Skill.findByName",
        query = "SELECT s FROM Skill s WHERE s.skillName LIKE :keyword")
        , @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
        , @NamedQuery(name = "Skill.findById", query = "SELECT s FROM Skill s WHERE s.id = :id")
})
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", nullable = false, columnDefinition = "VARCHAR(150)")
    private String skillName;

    private String category;

    @OneToMany(mappedBy = "skill")
    Set<JobSkill> jobSkills;

}
