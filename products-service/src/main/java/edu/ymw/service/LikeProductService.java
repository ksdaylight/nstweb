package edu.ymw.service;

import edu.ymw.dao.LikeProductDao;
import edu.ymw.pojo.LikeProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@CacheConfig(cacheNames="Likeroducts")
public class LikeProductService {
    @Autowired
    LikeProductDao likeProductDao;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LikeProductService.class);
    @CacheEvict(allEntries=true)
    public void add(LikeProduct bean) {
        if (null!=likeProductDao.findByUIdAndLikeId(bean.getuId(),bean.getLikeId())){
            log.info("已经有了，不加入");
            return;
        }else {
            log.info("没有，加入");
            likeProductDao.save(bean);
        }

    }
    @CacheEvict(allEntries=true)
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public void delete(int userId, int likeId) {
        if (null!=likeProductDao.findByUIdAndLikeId(userId,likeId)){
            log.info("已经有了，删除");
            likeProductDao.deleteByUIdAndLikeId(userId,likeId);
        }else {
            log.info("本来没有，不删除");
            return;
        }

    }

}
