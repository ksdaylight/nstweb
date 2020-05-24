package edu.ymw;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.ymw.dao.LikeProductDao;
import edu.ymw.dao.ProductDao;
import edu.ymw.dao.TemplateDao;
import edu.ymw.pojo.LikeProduct;
import edu.ymw.pojo.Product;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductsServiceApplication.class)
public class Test2 {
    @Autowired
    ProductDao productDao;
    @Autowired
    LikeProductDao likeProductDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Test2.class);
    @Test
    public void test4() {

        int start = 0; int size=5;
        int pSort= 3; int navigatePages= 5;
        Specification querySpecifi = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date e = c.getTime();
//        String endTime=format.format(s);
//        String startTime="";
        switch(pSort){
            //太赶，不屑道Const中了
            case 0:
                c.add(Calendar.DATE, - 1);
                Date d = c.getTime();
                querySpecifi = tiaoJian(d,e);
//                startTime = format.format(d);
                break;
            case 1:
                c.add(Calendar.DATE, - 7);
                Date w = c.getTime();
                querySpecifi = tiaoJian(w,e);
//                startTime = format.format(w);
                break;
            case 2:
                c.add(Calendar.MONTH, -1);
                Date m = c.getTime();
                querySpecifi = tiaoJian(m,e);

//                startTime = format.format(m);
                break;
            case 3:
                c.add(Calendar.YEAR, -2);
                Date y = c.getTime();
                querySpecifi = tiaoJian(y,e);
//                startTime = format.format(y);
                break;

            default:
                c.add(Calendar.DATE, - 1);
                d = c.getTime();
                querySpecifi = tiaoJian(d,e);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA = productDao.findAll(querySpecifi,pageable);
       log.info("分页类的内容是："+pageFromJPA.toString());
    }
    private Specification tiaoJian(Date startTime, Date endTime){
        Specification querySpecifi = new Specification<Product>(){
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(criteriaBuilder.between(root.get("create_time"),startTime,endTime));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return querySpecifi;


    }
    @Test
    public void test3() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date s = c.getTime();
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();

        String endTime = format.format(d);
        String startTime = format.format(s);
        System.out.println("过去七天："+endTime);
        System.out.println("现在："+startTime);
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