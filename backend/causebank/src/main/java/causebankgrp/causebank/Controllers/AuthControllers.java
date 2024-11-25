package causebankgrp.causebank.Controllers;

import org.springframework.web.bind.annotation.*;

// where we will handle the api

@RestController
@RequestMapping("/api/v1/")
public class AuthControllers {

    @PostMapping("auth/login")
    public String login() {

        return "login";

    }

    @PostMapping("auth/signin")
    public String register() {

        return "register";
    }

    @PostMapping("auth/logout")
    public String logout() {

        return "logout";
        
    }
   
}