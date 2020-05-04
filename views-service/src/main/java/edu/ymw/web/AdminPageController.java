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

    @GetMapping(value="/admin_template_list")
    @CrossOrigin
    public String listTemplate(){
        return "admin/listTemplate";
    }

    @GetMapping(value="/admin_template_edit")
    @CrossOrigin
    public String editCategory(){
        return "admin/editTemplate";

    }
    @GetMapping(value="/admin_product_list")
    @CrossOrigin
    public String listProduct(){
        return "admin/listProduct";

    }

    @GetMapping(value="/test")
    @CrossOrigin
    public  String test() throws Exception{
        return  "test 测试controller,产生500错误";

    }
}
