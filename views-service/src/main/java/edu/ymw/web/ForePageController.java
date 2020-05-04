package edu.ymw.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForePageController {

    @GetMapping(value="/home")
    @CrossOrigin
    public String home(){
        return "fore/home";
    }

    @GetMapping(value="/register")
    @CrossOrigin
    public String register(){
        return "fore/register";
    }
    @GetMapping(value="/registerSuccess")
    @CrossOrigin
    public String registerSuccess(){
        return "fore/registerSuccess";
    }
    @GetMapping(value="/login")
    public String login(){
        return "fore/login";
    }
    @GetMapping("/forelogout")
    public String logout( ) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        return "redirect:home";
    }
}
