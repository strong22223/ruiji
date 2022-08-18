package com.strong.config;

import com.strong.interceptor.CheckLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@ComponentScan({"com.strong.interceptor"})
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private CheckLoginInterceptor checkLoginInterceptor;

    /**
     * 实现资源的转义
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //开始进行静态资源映射
       registry.addResourceHandler("/backend/**").addResourceLocations("classpath:static/backend/");
       registry.addResourceHandler("/font/**").addResourceLocations("classpath:static/font/");
    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //SpringMvc资源拦截
        registry.addInterceptor(checkLoginInterceptor).addPathPatterns("/employee/*");
    }
}
