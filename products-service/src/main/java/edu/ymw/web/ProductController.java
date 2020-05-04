package edu.ymw.web;
import edu.ymw.pojo.Product;
import edu.ymw.pojo.Template;
import edu.ymw.service.ProductService;
import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ProductController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TemplateController.class);
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    @CrossOrigin
    //请求跨域
    public Page4Navigator<Product> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        log.info("当前size为： "+size);
        start = start<0?0:start;
        Page4Navigator<Product> page =productService.list(start, size, 5);  //5表示导航分页最多有5个，如 [1,2,3,4,5]
        return page;
    }
    @PostMapping("/products/pBanned/{id}")
    @CrossOrigin
    public String  pBanned(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
                Product product = productService.get(id);
                product.setStatus(3);
                log.info("当前更新为： "+product.toString());
                productService.update(product);
                 return  null;
    }
    @PostMapping("/products/pUnlock/{id}")
    @CrossOrigin
    public  String pUnlock(@PathVariable("id") int id, HttpServletRequest request)throws Exception {
        Product product = productService.get(id);
        product.setStatus(1);
        log.info("当前更新为： "+product.toString());
        productService.update(product);
        return  null;
    }
}
