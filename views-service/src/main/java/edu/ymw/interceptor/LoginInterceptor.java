
package edu.ymw.interceptor;

import edu.ymw.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 1. 准备字符串数组 requireAuthPages，存放那些需要登录才能访问的路径
 2. 获取uri
 3. 去掉前缀/tmall_springboot
 4. 判断是否是以 requireAuthPages 里的开头的
 4.1 如果是就判断是否登陆，未登陆就跳转到 login 页面
 4.2 如果不是就放行
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private RedisTemplate redisTemplate;
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
    	HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
        		"buy",

        		"foredoreview"
        		
        };
 
        
        String uri = httpServletRequest.getRequestURI();
		log.info("uri+"+uri);
        uri = StringUtils.remove(uri, contextPath+"/");
		log.info("conteext+"+contextPath);
        String page = uri;
		log.info("uri+"+page);



//		if(begingWith(page, requireAuthPages)){
//			User nowUser =(User) redisTemplate.opsForValue().get("NowUser");
//			log.info("nowUser+"+nowUser.toString());
//			if(nowUser==null) {
//				httpServletResponse.sendRedirect("login");
//				return false;
//			}
//		}
        return true;   
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
    	boolean result = false;
    	for (String requiredAuthPage : requiredAuthPages) {
			if(StringUtils.startsWith(page, requiredAuthPage)) {
				result = true;	
				break;
			}
		}
    	return result;
	}

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}


