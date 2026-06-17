package edu.fu.controller;

import edu.fu.dto.CategoryResponse;
import edu.fu.dto.JobRequest;
import edu.fu.entities.Department;
import edu.fu.entities.Job;
import edu.fu.services.DepartmentService;
import edu.fu.services.JobService;
import edu.fu.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final DepartmentService departmentService;
    private final SkillService skillService;

    @GetMapping
    public ModelAndView getJobs() {
        ///  Call Jobservice
        List<Job> jobs = jobService.findAllJobs();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jobs/job_management");
        mv.addObject("jobs", jobs);

        return mv;
    }

    @GetMapping("/detail")
    public String jobDetail(Model model) {
        // Lấy danh sách department cho dropdown
        List<Department> departments = departmentService.findAll();

        // Lấy skill đã gom theo category cho phần checkbox
        List<CategoryResponse> categories = skillService.getCategories();

        model.addAttribute("departments", departments);
        model.addAttribute("categories", categories);
        model.addAttribute("jobRequest", new JobRequest());
        return "/jobs/job_detail";
    }

    @PostMapping
    public String createJob(@ModelAttribute("jobRequest") JobRequest jobRequest) {
        jobService.createJob(jobRequest);

        return "redirect:/jobs";
    }

}
