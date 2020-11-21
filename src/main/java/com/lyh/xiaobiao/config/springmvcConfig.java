package com.lyh.xiaobiao.config;

import com.lyh.xiaobiao.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class springmvcConfig implements WebMvcConfigurer {
	@Autowired
	LoginInterceptor  loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loginInterceptor).addPathPatterns(new String[]{"/xiaobiao/**","/*/*.html"}).excludePathPatterns(new String[]{"/index.html","/forget.html","/register.html"});
	
	}
}
