package edu.fu.services;

import edu.fu.dao.JobDao;
import edu.fu.dto.JobRequest;
import edu.fu.entities.Department;
import edu.fu.entities.Job;
import edu.fu.entities.JobSkill;
import edu.fu.entities.JobStatus;
import edu.fu.entities.Skill;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {
    private JobDao jobDao;  // DI

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

//    public void setJobDao(JobDao jobDao) {
//        this.jobDao = jobDao;
//    }

    @Override
    public Job findById(Long id) {
        // Validate, rule?

        if (id == null) {
            throw new IllegalArgumentException("Job id is null");
        }

        return jobDao.findById(id);
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

        if (jobDao.isExisted(job.getTitle())) {
            throw new RuntimeException("Job title already exists");
        }

        return jobDao.createJob(fromDto(job));
    }


    @Override
    public List<Job> findAllJobs() {
        return jobDao.findAllJobs();
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
