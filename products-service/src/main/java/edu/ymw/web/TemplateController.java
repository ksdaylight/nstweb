package edu.ymw.web;
import edu.ymw.pojo.Template;
import edu.ymw.service.TemplateService;
import edu.ymw.util.ImageUtil;
import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@RestController
public class TemplateController{
    @Autowired TemplateService tempService;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TemplateController.class);
    @GetMapping("/templates")
    @CrossOrigin
    //请求跨域
    public Page4Navigator<Template> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Page4Navigator<Template> page =tempService.list(start, size, 5);  //5表示导航分页最多有5个，如 [1,2,3,4,5]
        return page;
    }
//PostMapping，REST 规范就是通过method的区别来辨别到底是获取还是增加的。
    @PostMapping("/templates")
    @CrossOrigin
    public Object add(Template bean, MultipartFile image, HttpServletRequest request) throws Exception {
        log.trace("进入controller方法");
        tempService.add(bean);
        log.trace("serrvice bean 保存成功");

        saveOrUpdateImageFile(bean, image, request);
        log.trace("图片 保存成功");
        return bean;
    }
    public void saveOrUpdateImageFile(Template bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File("G:\\\\project\\\\nstweb\\\\views-service\\src\\main\\webapp\\img\\template\\");
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }
    @DeleteMapping("/templates/{id}")
    @CrossOrigin
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        tempService.delete(id);
        File  imageFolder= new File("G:\\\\project\\\\nstweb\\\\views-service\\src\\main\\webapp\\img\\template\\");
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }
    @GetMapping("/templates/{id}")
    @CrossOrigin
    public Template get(@PathVariable("id") int id) throws Exception {
        Template bean=tempService.get(id);
        return bean;
    }
    @PostMapping ("/templates/{id}")
    @CrossOrigin
    public Object update(MultipartFile image,Template bean ,HttpServletRequest request) throws Exception {
//        String name = request.getParameter("name");  //bean注入不了只能用这样
//        log.info("传过来的name"+name);
//        bean.setName(name);
        log.info("传过来的name"+request.getParameter("name"));
        log.info("传过来的bean"+bean.toString());
        tempService.update(bean);

        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }

}
