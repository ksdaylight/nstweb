/**
* 模仿天猫整站 springboot 教程 为 how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package edu.ymw.config;


import edu.ymw.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
class WebMvcConfigurer extends WebMvcConfigurerAdapter {


	@Bean
	public LoginInterceptor getLoginIntercepter() {
		return new LoginInterceptor();
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry){
//
//		registry.addInterceptor(getLoginIntercepter())
//				.addPathPatterns("/**");
//	}
}


