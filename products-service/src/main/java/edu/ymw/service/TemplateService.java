package edu.ymw.service;

import edu.ymw.dao.TemplateDao;
import edu.ymw.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
@Service
@CacheConfig(cacheNames="products")
public class TemplateService {
 @Autowired TemplateDao templateDao;
    @Cacheable(key="'all_Template'")
    public List<Template> list(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return templateDao.findAll(sort);

    }
}
