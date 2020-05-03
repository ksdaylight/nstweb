package edu.ymw.service;

import edu.ymw.dao.TemplateDao;
import edu.ymw.pojo.Template;
import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
@Service
@CacheConfig(cacheNames="products")
public class TemplateService {
 @Autowired TemplateDao templateDao;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TemplateService.class);

    @Cacheable(key="'templates-page-'+#p0+ '-' + #p1")
    public Page4Navigator<Template> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =templateDao.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    @Cacheable(key="'all_Template'")
    public List<Template> list(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return templateDao.findAll(sort);

    }
    @CacheEvict(allEntries=true)
    public void add(Template bean) {
        log.trace("Dao增加");
        templateDao.save(bean);
        log.trace("Dao增加成功");
    }
}
