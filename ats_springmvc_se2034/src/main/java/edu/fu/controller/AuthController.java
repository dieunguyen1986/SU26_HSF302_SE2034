package edu.fu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // --> Spring Bean
@RequestMapping(path = {"/auths", ""})
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(path = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "auth/login"; // prefix + index + suffix --> WEB-INF/views/index.jsp
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam(name = "email") String emailAddress,
                               @RequestParam(name = "password") String password,
                               Model model
                               ){
        log.info("Email address {} ",emailAddress);
        // Call service?

        if(!"rec@example.com".equals(emailAddress)){
            model.addAttribute("error","Email address is not correct");
            return "auth/login";
        }

        return "jobs/job_management";
    }
}
