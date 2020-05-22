package edu.ymw;

import edu.ymw.Dao.UserDao;
import edu.ymw.pojo.Product;
import edu.ymw.pojo.User;
import edu.ymw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReportsServiceApplication.class)
public class Test3 {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Test3.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test3(){
//        redisTemplate.delete("NowUser");
        Product product = restTemplate.getForObject("http://127.0.0.1:8031/api-products/getOne/"+ 6,Product.class);
        log.info("获取到的product ::"+product.toString());

    }

}
