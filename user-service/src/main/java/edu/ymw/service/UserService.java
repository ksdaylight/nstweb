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

@Service
@CacheConfig(cacheNames="user")
public class UserService {

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
        userDao.save(user);
    }

}
