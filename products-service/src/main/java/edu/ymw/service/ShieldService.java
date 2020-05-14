package edu.ymw.service;

import edu.ymw.dao.ShieldDao;
import edu.ymw.pojo.Shield;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShieldService {
    @Autowired
    ShieldDao shieldDao;
    public void add(Shield shield){
        shieldDao.save(shield);
    }
}
