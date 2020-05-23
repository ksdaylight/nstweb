package edu.ymw;
import java.util.List;

import edu.ymw.dao.LikeProductDao;
import edu.ymw.dao.TemplateDao;
import edu.ymw.pojo.LikeProduct;
import edu.ymw.pojo.Template;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductsServiceApplication.class)
public class Test2 {
    @Autowired
    TemplateDao productDao;
    @Autowired
    LikeProductDao likeProductDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Test2.class);

    @Test
    public void test3() {

    }

    @Test
    public void test() {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(0, 5,sort);
//        Page pageFromJPA = productDao.findAll(pageable);
        redisTemplate.delete("NowUser");

//        for (Template c : cs) {
//            System.out.println("c.getName():"+ c.getName());
//        }

    }
    @Test
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void test2(){
//        System.out.println(request.getServletContext().getRealPath("img/template"));
//        LikeProduct likeProduct= likeProductDao.findByUIdAndLikeId(5,7);
//        if (null!=likeProduct){
//            log.info("we\\\\:"+likeProduct.toString());
//        }else {
//            log.info("不存在");
//        }
        if (null!=likeProductDao.findByUIdAndLikeId(5,6)){
            likeProductDao.deleteByUIdAndLikeId(5,6);
            log.info("删除成功");
        }else {
            log.info("不存在");
            return;
        }


    }

}