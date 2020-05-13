package edu.ymw.service;

import edu.ymw.dao.LikeProductDao;
import edu.ymw.pojo.LikeProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


@Service
@CacheConfig(cacheNames="Likeroducts")
public class LikeProductService {
    @Autowired
    LikeProductDao likeProductDao;

    @CacheEvict(allEntries=true)
    public void add(LikeProduct bean) {
        if (null!=likeProductDao.findByUIdAndLikeId(bean.getuId(),bean.getLikeId())){
            return;
        }else {
            likeProductDao.save(bean);
        }

    }
    @CacheEvict(allEntries=true)
    public void delete(int userId, int likeId) {
        if (null!=likeProductDao.findByUIdAndLikeId(userId,likeId)){
            likeProductDao.deleteByUIdAndLikeId(userId,likeId);
        }else {
            return;
        }

    }

}
