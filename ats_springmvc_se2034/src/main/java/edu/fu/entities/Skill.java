package edu.fu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "skills")
@NamedQueries({@NamedQuery(name = "findByName",
        query = "SELECT s FROM Skill s WHERE s.skillName LIKE :keyword", resultClass = Skill.class)
        , @NamedQuery(name = "findAll", query = "SELECT s FROM Skill s", resultClass = Skill.class)
        , @NamedQuery(name = "findById", query = "SELECT s FROM Skill s WHERE s.id = :id", resultClass = Skill.class)
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
