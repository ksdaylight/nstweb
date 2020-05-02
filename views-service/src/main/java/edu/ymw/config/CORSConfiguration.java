package edu.ymw.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("file:G:\\project\\nstweb\\views-service\\src\\main\\webapp\\css\\");
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//        registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\aaa\\");\\不要忘了最后这两个斜杠
        registry.addResourceHandler("/img/**").addResourceLocations("file:G:\\project\\nstweb\\views-service\\src\\main\\webapp\\img\\");
        registry.addResourceHandler("/js/**").addResourceLocations("file:G:\\project\\nstweb\\views-service\\src\\main\\webapp\\js\\");
    }
    //所有请求都允许跨域
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
