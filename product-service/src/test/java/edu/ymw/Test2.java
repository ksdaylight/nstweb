package edu.ymw;
import java.util.List;

import edu.ymw.dao.TemplateDao;
import edu.ymw.pojo.Template;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class)
public class Test2 {
    @Autowired
    TemplateDao dao;
    @Test
    public void test() {
        List<Template> cs=  dao.findAll();
        for (Template c : cs) {
            System.out.println("c.getName():"+ c.getName());
        }

    }
    @Test
    public void test2(){
        System.out.println("asgsadg");
    }

}
