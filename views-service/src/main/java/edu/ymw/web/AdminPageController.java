package edu.ymw.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class AdminPageController {
    @GetMapping(value="/")
    @CrossOrigin
    public String none(){
        return "redirect:admin";
    }
    @GetMapping(value="/admin")
    @CrossOrigin
    public String admin(){
        return "redirect:admin_template_list";
    }
    @GetMapping(value="/test")
    @CrossOrigin
    public  String test(){
        return  "test 测试controller";

    }
    @GetMapping(value="/admin_template_list")
    @CrossOrigin
    public String listTemplate(){
        return "admin/listTemplate";
    }
}
