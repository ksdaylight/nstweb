package edu.ymw.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ForePageController {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ForePageController.class);
    @GetMapping(value="/home")
    @CrossOrigin
    public String home(){
        return "fore/home";
    }
    @GetMapping(value="/home2")
    @CrossOrigin
    public String home2(){
        return "fore/home2";
    }

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

    //无奈改为post,zuul转发貌似不支持改method
    @GetMapping(value="/login")
    public String login(){
        return "fore/login";
    }
    @GetMapping("/forelogout")
    public String logout( HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        if(subject.isAuthenticated()){
            log.info("进入判断后退出");
            subject.logout();

            log.info("清楚成功");
//            session.removeAttribute("user");
        }
        redisTemplate.delete("NowUser");
        log.info("跳转");
        return "redirect:http://127.0.0.1:8031/api-views/home";
    }
    @GetMapping(value="/search")
    public String searchResult(){
        return "fore/search";
    }

}
