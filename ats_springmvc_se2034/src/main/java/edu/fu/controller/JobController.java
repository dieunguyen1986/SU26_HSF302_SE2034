package edu.fu.controller;

import edu.fu.entities.Job;
import edu.fu.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ModelAndView getJobs() {
        ///  Call Jobservice
        List<Job> jobs = jobService.findAllJobs();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jobs/job_management");
        mv.addObject("jobs", jobs);

        return mv;
    }
}
