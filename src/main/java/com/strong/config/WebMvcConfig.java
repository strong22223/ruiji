package com.strong.config;

import com.strong.common.JacksonObjectMapper;
import com.strong.interceptor.CheckLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
@ComponentScan({"com.strong.interceptor"})
//@EnableWebMvc//测试转化 对象数据
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

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("转换器加载...");
//        1.创建消息转换器
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//2.设置对象转换器,底层使用Jackson将Jva对象准换为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
//        3. 将上面的消息转换器追加到mvc框架中
        converters.add(0, messageConverter);
    }
}
