package com.wps.studyplatform.jwt.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title ApplicationConfig
 * @Description 配置JWTIntercept拦截器
 * @auther wps
 * @Date 2020/4/1616:09
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JWTIntercept jwtIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath=new ArrayList<>();
        excludePath.add("/**/login");
        excludePath.add("/swagger-ui.html");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/v2/**");
        excludePath.add("/webjars/**");
        registry.addInterceptor(jwtIntercept)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);

    }
    //不拦截swagger测试
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");
         registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
}
