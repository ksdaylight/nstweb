package edu.ymw.filter;
import edu.ymw.pojo.User;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;

@Component
public class LoginFilter extends ZuulFilter{
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginFilter .class);
    @Autowired
    private RedisTemplate redisTemplate;
    String[] requireAuthPages = new String[]{
            "pLike",
            "pUnlike",
            "pShield",
            "addOneProductReport",
            "0.0"

    };
    String[] zuulApi = new String[]{
            "api-products",
            "api-views",
            "api-user",
            "api-reports"
    };

    //网关的过滤器如何编写
    //过滤类型 pre 表示在请求之前进行执行
    @Override
    public String filterType() {
        /**
         * pre：路由之前
         * routing：路由之时
         * post： 路由之后
         * error：发送错误调用
         */

        return FilterConstants.PRE_TYPE;
    }
    //过滤器执行顺序，当一个请求在同一个阶段存在多个过滤器的时候，多个过滤器执行顺序。
    @Override
    public int filterOrder() {
        return 0;
    }
    //判断过滤器是否生效
    @Override
    public boolean shouldFilter() {
        return true;
    }
    //编写过滤器拦截业务逻辑代码
    @Override
    public Object run() throws ZuulException {
        //案例：拦截所有的服务接口，判断服务接口上是否有传递userToken参数。
        //1.获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //2.获取Request对象
        HttpServletRequest request = currentContext.getRequest();
        //3.获取token的时候从请求头中获取
        String userToken = request.getParameter("userToken");

        String uri = request.getRequestURI();
        log.info("uri+"+uri);
        for (String contextPath: zuulApi ) {
//            log.info("uri:"+uri);
            uri = StringUtils.remove(uri, "/"+contextPath+"/");
//            log.info("conteext:"+contextPath);
            String page = uri;
//            log.info("page:"+page);
        }
        int i = uri.indexOf("/");//首先获取字符的位置
        if(i>0){
            uri = uri.substring(0,i);
        }
        String page = uri;
        log.info("page:"+page);

        if(begingWith(page, requireAuthPages)){
            User nowUser =(User) redisTemplate.opsForValue().get("NowUser");

            if(nowUser==null) {
                //需要转发到登录
                log.info("转发到登录");
                //不会继续执行....不会去调用服务接口，网关服务直接响应给客户端
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("userToken is null");
            currentContext.setResponseStatusCode(401);
            log.info("没有权限");
//                try {
//                currentContext.setSendZuulResponse(false);
//                currentContext.put(FORWARD_TO_KEY, "http://127.0.0.1:8031/api-views/login");
//                currentContext.setResponseStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT);
//                currentContext.getResponse().sendRedirect("http://127.0.0.1:8031/api-views/login");
//                } catch (IOException e) {
//                    log.error("unable to send a redirect to the login page");
//                }
//                currentContext.put(FilterConstants.REQUEST_URI_KEY, "login");
//                currentContext.put(FilterConstants.SERVICE_ID_KEY,"views-service");
                return null;
            }
            else
                log.info("nowUser+"+nowUser.toString());
        }

//        if (StringUtils.isEmpty(userToken)){
//            //不会继续执行....不会去调用服务接口，网关服务直接响应给客户端
//            currentContext.setSendZuulResponse(false);
//            currentContext.setResponseBody("userToken is null");
//            currentContext.setResponseStatusCode(401);
//            log.info("没有权限");
//            return null;
//        }
        //正常执行调用其他服务接口...
        log.info("正常调用");
        return null;
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

}
