package edu.ymw.web;

import edu.ymw.pojo.LikeProduct;
import edu.ymw.pojo.User;
import edu.ymw.service.LikeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class LikeProductController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LikeProductController.class);
    @Autowired
    LikeProductService likeProductService;

    @PostMapping("/products/pLike/{id}")
    @CrossOrigin
    public String  pLike(HttpSession session,
                         @PathVariable("id")  int pid)  throws Exception {
        log.info("开始喜欢 ");
        log.info("sessin ID 此处为"+session.getId());
        User user =(User)  session.getAttribute("user");
        log.info("获取到的user ::"+user.toString());

        LikeProduct likeProduct = new LikeProduct();
        likeProduct.setuId(user.getId());
        likeProduct.setLikeId(pid);
        likeProduct.setCreateTime(new Date());
        likeProductService.add(likeProduct);

        return  null;

    }
    @PostMapping("/products/pUnlike/{id}")
    @CrossOrigin
    public String  pUlike(@RequestParam(value = "uid", defaultValue = "-1") int uid,
                          @RequestParam(value = "pid", defaultValue = "-1") int pid)throws Exception {

        log.info("开始解除喜欢 ");
    likeProductService.delete(uid,pid);
        return  null;
    }
}
