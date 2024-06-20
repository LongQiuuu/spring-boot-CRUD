package com.web.store.config;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	//這邊是在說 我用Class 當作一個承接的容器 是觀念而已
//	Class<RootAppConfig> c1 =RootAppConfig.class;
//	Class<WebAppConfig> c2 =WebAppConfig.class;
//	Class<Integer> c3 =Integer.class;
//	Class<String> c4 =String.class;
	
	
	
	
	private static Logger Log =LoggerFactory.getLogger(WebAppInitializer.class);
	
	
	
	//跟其他系統共用的Bean
	@Override
	protected Class<?>[] getRootConfigClasses() {
			//ingo 準備類型
		Log.info("準備執行getRootConfigClasses()");
		return  new Class [] {RootAppConfig.class};
	}
	//	本應用系統專屬的Bean
	@Override
	protected Class<?>[] getServletConfigClasses() {
		Log.info("getServletConfigClasses()");
		return  new Class [] {WebAppConfig.class};
	}
	//哪些請求要交給分派器處理
	@Override
	protected String[] getServletMappings() {
		Log.info("準備執行getServletMappings()");
		//要傳回一格字串陣列 
		//{"/"};這樣寫是所有請求交給分派器處理
		return new String[] {"/"};
	}
	
	//解決亂碼
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] {characterEncodingFilter};
	}
}
