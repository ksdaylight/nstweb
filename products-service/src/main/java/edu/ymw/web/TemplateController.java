package edu.ymw.web;
import edu.ymw.pojo.Template;
import edu.ymw.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
public class TemplateController{
    @Autowired TemplateService tempService;
    @GetMapping("/templates")
    @CrossOrigin
    public List<Template> list() throws Exception{
       return tempService.list();
    }
}
