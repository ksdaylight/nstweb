package edu.ymw.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForePageController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value="/home")
    @CrossOrigin
    public String home(){
        return "fore/home";
    }
//    @GetMapping(value="/home")
//    @CrossOrigin
//    public String home(){
//        return "fore/home2";
//    }

    @GetMapping(value="/Mypage")
    @CrossOrigin
    public String mypage(){
        return "fore/mypage2";
    }

    @GetMapping(value="/message")
    @CrossOrigin
    public String message(){
        return "fore/message2";
    }

    @GetMapping(value="/add")
    @CrossOrigin
    public String add(){
        return "fore/add2";
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
        if(subject.isAuthenticated()){

            subject.logout();
            redisTemplate.delete("NowUser");
        }

        return "redirect:home";
    }
}
