package com.strong.Filter;


import com.alibaba.fastjson.JSON;
import com.strong.common.BaseContext;
import com.strong.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "CheckLoginFilter", urlPatterns = "/*")
@Component
@Slf4j
public class CheckLoginFilter implements Filter {
    //确定匹配路径
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径 定义不需要拦截的资源
//            并且将有些路径直接放行
        String requestURI = request.getRequestURI(); //backend/index.html
//        log.info("拦截请求路径->>{}", requestURI);

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "common/**"
        };
        //2.判断这次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3.如果不需要处理,则直接放行
        if (check) {
//            log.info("本次请求不需要处理->>{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //4.判断登录条件,如果已经登录则放行
        Long employee = (Long) request.getSession().getAttribute("employee");
        if (employee != null) {
            log.info("用户已经登录{},用户id为->>{}", requestURI, employee);
            //在线程中设置id
            BaseContext.setCurrentId(employee);

            filterChain.doFilter(request, response);
            return;
        }
        //5.如果没有登录,就向客户端响应请求
//        log.info("用户未登录->>");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        //重定向
        //1.设置响应状态码 302
//        response.setStatus(302);
//        //2. 设置响应头 Location
//        int i=1;
//        response.setHeader("Location", "/page/login/login.html");
//        log.info(""+i++);
//        return;
    }

    private boolean check(String[] urls, String requestUrl) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestUrl);
            if (match) {
                return true;
            }
        }
        return false;

    }
}
