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
@CacheConfig(cacheNames="template")
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
    @CacheEvict(allEntries=true)
    public void delete(int id) {
        templateDao.deleteById(id);
    }

    @Cacheable(key="'template-one-'+ #p0")
    public Template get(int id) {

        Template t= templateDao.findById(id).orElse(null);
        log.info("找到了模组");
        log.info("找到了模组");
        return t;
    }
    @CacheEvict(allEntries=true)
    public void update(Template bean) {
        log.info("service 这里的bean"+bean.toString());
        templateDao.save(bean);
    }

}
