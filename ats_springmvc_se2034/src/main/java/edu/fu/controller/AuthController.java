package edu.fu.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // --> Spring Bean
@RequestMapping(path = {"/auths", ""})
public class AuthController {

    @RequestMapping(path = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "index"; // prefix + index + suffix --> WEB-INF/views/index.jsp
    }

    @PostMapping("/login")
    public String processLogin(){
        return null;
    }
}
