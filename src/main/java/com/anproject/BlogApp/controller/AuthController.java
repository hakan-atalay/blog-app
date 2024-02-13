package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.payload.request.RegularUserRequestDto;
import com.anproject.BlogApp.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "/login/login";
        }
        return "redirect:/home/index";
    }

    @GetMapping("/login/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new RegularUserRequestDto());
        return "/login/register";
    }

    @PostMapping("/login/registration-process")
    public String registrationProcess(RegularUserRequestDto user){
        userService.save(user);
        return "redirect:/login";
    }

}
