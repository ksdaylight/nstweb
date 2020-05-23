package edu.ymw.service;

import edu.ymw.dao.ShieldDao;
import edu.ymw.pojo.Shield;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="Shile")
public class ShieldService {
    @Autowired
    ShieldDao shieldDao;
    @CacheEvict(allEntries=true)
    public void add(Shield shield){
        shieldDao.save(shield);
    }
    @Cacheable(key="'userShileProList-'+#p0")
    public List<Shield> userList(int id){
        List<Shield> list  = shieldDao.findByUId(id);
        return list;
    }


}
