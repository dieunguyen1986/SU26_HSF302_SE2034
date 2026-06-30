package edu.fu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_skills")
@IdClass(JobSkillId.class)
@Getter@Setter
public class JobSkill {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private Skill skill; // 1,3

    @Id
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
}
