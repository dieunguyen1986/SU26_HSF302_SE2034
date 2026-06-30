package edu.fu.controller;

import edu.fu.dto.JobCriteria;
import edu.fu.entities.Job;
import edu.fu.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/public/jobs")
@RequiredArgsConstructor
@Slf4j
public class PublicJobController {

    private final JobService jobService;

    @GetMapping("/browse")
    public String browse(@ModelAttribute JobCriteria jobCriteria, @RequestParam(name = "pageIndex", defaultValue = "0", required = false) Integer pageIndex,
                         @RequestParam(name = "pageSize", defaultValue = "6", required = false) Integer pageSize, Model model) {

        Page<Job> page = jobService.findPublishedJob(pageIndex, pageSize);

        model.addAttribute("jobs", page.getContent());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("currentPage", pageIndex);


        return "views/publics/browse_job";
    }
}
