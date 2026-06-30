package edu.fu.controller;

import edu.fu.dto.UserRequest;
import edu.fu.entities.User;
import edu.fu.services.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // --> Spring Bean
@RequestMapping(path = {"/auths"})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(path = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "views/auth/login"; // prefix + index + suffix --> WEB-INF/views/index.jsp
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam(name = "email") String emailAddress,
                               @RequestParam(name = "password") String password,
                               Model model,
                               HttpSession session
    ) {
        log.info("Email address {} ", emailAddress);
        // Call service?

        User user = authService.authenticate(UserRequest.builder().email(emailAddress).password(password).build());
        session.setAttribute("user", user);

        return "redirect:/jobs";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute UserRequest userRequest) { // ??
        System.out.println(userRequest);

        return "auth/login";
    }
}
