package edu.ymw.service;

import edu.ymw.dao.TemplateDao;
import edu.ymw.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
 @Autowired TemplateDao templateDao;
    public List<Template> list(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return templateDao.findAll(sort);

    }
}
