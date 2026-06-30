package edu.fu.services;

import edu.fu.dto.JobRequest;
import edu.fu.entities.*;
import edu.fu.repositities.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service("jobService")
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;  // DI qua constructor do @RequiredArgsConstructor sinh ra

    @Override
    public Job findById(Long id) {
        // Validate, rule?

        if (id == null) {
            throw new IllegalArgumentException("Job id is null");
        }

        return jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Job id " + id + " not found"));
    }

    @Override
    public Job createJob(JobRequest job) {
        // Validate
        if (job.getDeadline() == null || job.getDeadline().compareTo(LocalDate.now()) < 0) {
            throw new IllegalArgumentException("Job deadline must be after the current date");
        }

        // Max salary có thể bỏ trống, chỉ so sánh khi nhập cả hai
        if (job.getMaxSalary() != null && job.getMinSalary() >= job.getMaxSalary()) {
            throw new RuntimeException("Job max salary must be greater than min salary");
        }

        // check existing

        if (jobRepository.existsByTitle(job.getTitle())) {
            throw new RuntimeException("Job title already exists");
        }

        return jobRepository.save(fromDto(job));
    }

    public Page<Job> findPublishedJob(Integer pageIndex, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "publishAt");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

//        jobRepository.findAll(specification, pageable);
        return jobRepository.findPublishedJobByPage(pageable);
    }


    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }


    private Job fromDto(JobRequest jobRequest) {
        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setMinSalary(jobRequest.getMinSalary());
        job.setMaxSalary(jobRequest.getMaxSalary());
        job.setUtmSource(jobRequest.getUtmSource());
        job.setUtmMedium(jobRequest.getUtmMedium());
        job.setStatus(JobStatus.DRAFT.toString());

        // Deadline trên form là LocalDate, đổi sang Instant theo mốc đầu ngày giờ hệ thống
        if (jobRequest.getDeadline() != null) {
            job.setDeadline(jobRequest.getDeadline().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (jobRequest.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(jobRequest.getDepartmentId());

            job.setDepartment(department);
        }

        // Mỗi skill được tick tạo một dòng job_skill, cascade theo job khi lưu
        if (jobRequest.getSkillIds() != null) {
            for (Long skillId : jobRequest.getSkillIds()) {
                Skill skill = new Skill();
                skill.setId(skillId);

                JobSkill jobSkill = new JobSkill();
                jobSkill.setSkill(skill);
                jobSkill.setJob(job);

                job.getJobSkills().add(jobSkill);
            }
        }

        return job;
    }
}
