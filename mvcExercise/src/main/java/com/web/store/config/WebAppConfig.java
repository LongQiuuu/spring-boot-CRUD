package com.web.store.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
//你要掃描哪些套件下
@ComponentScan({"com.web.store.controller","com.web.store.service","com.web.store.dao","com.web.store.config"})
public class WebAppConfig implements WebMvcConfigurer {
	
	//建立一個內部資源視圖解析器
	@Bean 
	 public ViewResolver viewResolver() {
	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	  //setPrefix前導字
	  resolver.setPrefix("/WEB-INF/views/");
	  //setSuffix前導字 後贅字
	  resolver.setSuffix(".jsp");
	  return resolver;
	 }
	
	// messageSourceu一定要取這個 不然抓不到
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		rbms.setBasename("messages");
		return rbms;
		
	}
	//靜態請求
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	     //只要是前面有 /css/都去這個資料夾找
		registry.addResourceHandler("/css/**")
	             .addResourceLocations("/WEB-INF/views/css/");
	     registry.addResourceHandler("/image/**")
	             .addResourceLocations("/WEB-INF/views/images/");
	} 
	
	
	
}
