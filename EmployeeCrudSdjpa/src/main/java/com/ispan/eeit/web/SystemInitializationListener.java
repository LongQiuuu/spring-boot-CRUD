package com.ispan.eeit.web;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ispan.eeit.utils.SystemService;

@WebListener
public class SystemInitializationListener implements ServletContextListener {
    Logger log = LoggerFactory.getLogger(SystemInitializationListener.class);
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	log.info("SystemInitializationListener#contextInitialized()執行中...");
    	File imageMainFolder = new File(SystemService.EMPLOYEE_IMAGE_FILE_FOLDER);
    	if (!imageMainFolder.exists()) {
    		imageMainFolder.mkdirs();
    	}
    }
	
}
