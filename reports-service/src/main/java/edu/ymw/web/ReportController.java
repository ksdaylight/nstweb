package edu.ymw.web;

import edu.ymw.pojo.Report;
import edu.ymw.pojo.User;
import edu.ymw.pojo.Product;
import edu.ymw.service.ReportService;
import edu.ymw.util.Const;
import edu.ymw.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@RestController
public class ReportController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReportController.class);
    @Autowired
    ReportService reportService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/addOneProductReport/{id}")
    @CrossOrigin
    public Object addProduct(@PathVariable("id") int id) throws Exception {

        User fromUser =(User) redisTemplate.opsForValue().get("NowUser");
        if(fromUser!=null) log.info("获取到的user ::"+fromUser.toString());
        Product product = restTemplate.getForObject("http://127.0.0.1:8031/api-products/getOne/"+ id,Product.class);
        log.info("获取到的product ::"+product.toString());
        Report report = new Report();
        report.setFromId(fromUser.getId());
        report.setTargetId(product.getUser().getId());
        report.setEntityId(product.getId());
        report.setEntityType(Const.Product);
        report.setCreateTime(new Date());
        report.setRemark("");
        reportService.add(report);
        log.info("添加举报成功");


        return Result.success();
    }

}
