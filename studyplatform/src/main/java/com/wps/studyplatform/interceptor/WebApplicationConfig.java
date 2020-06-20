package com.wps.studyplatform.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebApplicationConfig extends WebMvcConfigurationSupport {
    @Autowired
    private HttpInterceptor httpInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor);
        super.addInterceptors(registry);
    }
}
