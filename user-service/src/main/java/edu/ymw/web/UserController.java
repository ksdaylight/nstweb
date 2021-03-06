package edu.ymw.web;

import edu.ymw.pojo.User;
import edu.ymw.service.UserService;
import edu.ymw.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/userName")
    @CrossOrigin
    //请求跨域
    public User getUserName() throws Exception {
        try {
            User user =(User) redisTemplate.opsForValue().get("NowUser");
            log.info("获取到的user ::"+user.toString());
            return  user;
        }catch (Exception e) {
            User user =new User();
            user.setName("");
            return user;
        }

    }
    @PostMapping("/foreregister")
    public Object register(@RequestBody User user)  throws Exception {
        String name =  user.getName();
        String password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        log.info("进入ForeRC");
        boolean exist = userService.isExist(name);

        if(exist){
            String message ="用户名已经被使用,不能使用";
            return Result.fail(message);
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userService.add(user);
        log.info("注册成功");
        return Result.success();
    }
    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam)  throws Exception {
        String name =  userParam.getName();
        name = HtmlUtils.htmlEscape(name);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, userParam.getPassword());
        try {
            subject.login(token);
            User user = userService.getByName(name);
//	    	subject.getSession().setAttribute("user", user);
            log.info("开始存入session"+user.toString());

//            session.setAttribute("user", user);
            //还是直接存入redis吧
            redisTemplate.opsForValue().set("NowUser",user);
            log.info("存入的session了user为"+user.toString());
//            log.info("sessionID 为："+session.getId());
            return Result.loginsuccess(user.getName());
        } catch (AuthenticationException e) {
            String message ="账号密码错误";
            return Result.fail(message);
        }

    }
}
