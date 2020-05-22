package edu.ymw.service;

import edu.ymw.dao.ReportDao;
import edu.ymw.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="report")
public class ReportService {
    @Autowired
    ReportDao reportDao;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReportService.class);

    @CacheEvict(allEntries=true)
    public void add(Report bean) {
        log.trace("RDao增加");
        reportDao.save(bean);
        log.trace("RDao增加成功");
    }

}
