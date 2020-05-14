package edu.ymw.web;
import edu.ymw.pojo.*;
import edu.ymw.service.LikeProductService;
import edu.ymw.service.ProductService;
import edu.ymw.service.ShieldService;
import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ProductController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TemplateController.class);
    @Autowired
    ProductService productService;
    @Autowired
    LikeProductService likeProductService;

    @Autowired
    ShieldService shieldService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/products")
    @CrossOrigin
    //请求跨域
    public Page4Navigator<Product> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        log.info("当前size为： "+size);
        start = start<0?0:start;
        Page4Navigator<Product> page =productService.list(start, size, 5);  //5表示导航分页最多有5个，如 [1,2,3,4,5]
        return page;
    }
    @PostMapping("/products/pBanned/{id}")
    @CrossOrigin
    public String  pBanned(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
                Product product = productService.get(id);
                product.setStatus(3);
                log.info("当前更新为： "+product.toString());
                productService.update(product);
                 return  null;
    }
    @PostMapping("/products/pUnlock/{id}")
    @CrossOrigin
    public  String pUnlock(@PathVariable("id") int id, HttpServletRequest request)throws Exception {
        Product product = productService.get(id);
        product.setStatus(1);
        log.info("当前更新为： "+product.toString());
        productService.update(product);
        return  null;
    }
    @PostMapping("/products/pLike/{id}")
    @CrossOrigin
    public String  pLike(@PathVariable("id")  int pid)  throws Exception {
        log.info("开始喜欢 ");
//        log.info("sessin ID 此处为"+session.getId());
//        User user =(User)  session.getAttribute("user");
        User user =(User) redisTemplate.opsForValue().get("NowUser");
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
    public String  pUlike(@PathVariable("id")   int pid)throws Exception {
        log.info("开始解除 ");
        User user =(User) redisTemplate.opsForValue().get("NowUser");
        log.info("获取到的user ::"+user.toString());
        likeProductService.delete(user.getId(),pid);
        return  null;
    }
    @PostMapping("/products/pShield/{id}")
    @CrossOrigin
    public String pShield(@PathVariable("id")   int pid)throws Exception {
        log.info("开始屏蔽 ");
        User user =(User) redisTemplate.opsForValue().get("NowUser");
        Shield shield = new Shield();
        shield.setuId(user.getId());
        shield.setpId(pid);
        shieldService.add(shield);
        log.info("屏蔽加入成功");
        return  null;
    }
}
