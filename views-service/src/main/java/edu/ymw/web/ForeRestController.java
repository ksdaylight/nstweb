package edu.ymw.web;

import edu.ymw.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
@RestController
public class ForeRestController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ForeRestController.class);
    @PostMapping("/showlogin")
    public void register(@RequestBody String userName, HttpSession session) {
        log.info("存入session的值为："+userName);
        session.setAttribute("user", userName);
        log.info("服务端存入成功");
    }
}
