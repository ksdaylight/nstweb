package edu.ymw.service;
import edu.ymw.Dao.UserDao;
import edu.ymw.pojo.User;
import edu.ymw.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@CacheConfig(cacheNames="users")
public class UserService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao userDao;

    public boolean isExist(String name) {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        User user = userService.getByName(name);
        return null!=user;
    }
    @Cacheable(key="'users-one-name-'+ #p0",unless="#result == null")
    public User getByName(String name) {
        return userDao.findByName(name);
    }

    @CacheEvict(allEntries=true)
    public void add(User user) {
        user.setBorn(new Date());
        user.setEmail("11111");
        user.setLogin_time(new Date());
        user.setSex("男");
        user.setPhone("112231321");
        userDao.saveAndFlush(user);
        log.info("立即查找：++++++"+ userDao.findByName(user.getName()).toString());
    }

    @Cacheable(key="'users-one-name-'+ #p0 +'-password-'+ #p1")
    public User get(String name, String password) {
        return userDao.getByNameAndPassword(name,password);
    }

}
