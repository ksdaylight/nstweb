package edu.ymw;

import edu.ymw.Dao.UserDao;
import edu.ymw.pojo.User;
import edu.ymw.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ViewsServiceApplication.class)
public class Test4 {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Test4.class);
    @Autowired
    UserDao userDao;
    @Autowired
    private UserService userService;
    String[] requireAuthPages = new String[]{
            "buy",

            "foredoreview"

    };
    String[] zuulApi = new String[]{
            "api-products",
            "api-views",
            "api-user",
            "api-reports"
    };
    String[] zuulApiSub = new String[]{
            "product",
            "0.0"
    };
    @Test
    public void test5(){
//        String uri = "/api-views/home/5555";
//        String uri = "/api-products/product/pShield/5555";
        String uri = "/api-products/product/5555";
        log.info("uri+"+uri);
        for (String contextPath: zuulApi ) {
            log.info("uri:"+uri);
            uri = StringUtils.remove(uri, "/"+contextPath+"/");

            log.info("conteext:"+contextPath);
            String page = uri;
            log.info("page:"+page);
        }
        int cnt = 0;
        int offset = 0;
        while((offset = uri.indexOf("/", offset)) != -1){
            offset = offset + "/".length();
            cnt++;
        }
        if(cnt>1){
            for (String contextPathSub: zuulApiSub ) {
                log.info("uri:"+uri);
                uri = StringUtils.remove(uri, contextPathSub+"/");

                log.info("conteextSub:"+contextPathSub);
                String page = uri;
                log.info("page:"+page);
            }
        }
        int i = uri.indexOf("/");//首先获取字符的位置
        uri = uri.substring(0,i);
        log.info("NoWuri+"+uri);
    }
    @Test
    public void test2(){
        String userName = "admin";
        log.info("取出的userName:"+userName );
        User user = userService.getByName(userName);
        log.info("取出的user:"+user.toString() );
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        log.info("取出密码:"+passwordInDB );
        log.info("取出的盐:"+salt);
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
//                getName());
        log.info("验证成功");
    }
    @Test
    public void test() {
        List<User >cs=  userDao.findAll();
//        User user= new User();
////        name='111', password='cdfe1807e45e1860b9e45bbd2367d66d', salt='9fX0dxwyVaILU7fsXf85vQ==',
////                phone='null', email='11111', born=2020-05-05 04:42:33.583, sex='男', login_time=2020-05-05 04:42:33.583
//        user.setName("111");
//        user.setPassword("cdfe1807e45e1860b9e45bbd2367d66d");
//        user.setSalt("9fX0dxwyVaILU7fsXf85vQ==");
//        user.setBorn(new Date());
//        user.setEmail("11111");
//        user.setLogin_time(new Date());
//        user.setSex("男");
//        user.setPhone("112231321");
//        userDao.save(user);
        log.info("查询id-------"+userDao.findByName("111"));
        for (User c : cs) {
            log.info("遍历数据"+c.toString() );
        }
    }
}
