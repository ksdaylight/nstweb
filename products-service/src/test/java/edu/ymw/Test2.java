package edu.ymw;
import java.util.List;

import edu.ymw.dao.TemplateDao;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
    @Test
    public void test() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 5,sort);
        Page pageFromJPA = productDao.findAll(pageable);


//        for (Template c : cs) {
//            System.out.println("c.getName():"+ c.getName());
//        }

    }
    @Test
    public void test2(){
//        System.out.println(request.getServletContext().getRealPath("img/template"));
    }

}