package com.web.store.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//連接資料庫



@Configuration
//EnableTransactionManagement 使用註識驅動管理 交易
@EnableTransactionManagement
//抓取常態檔過來
@PropertySource("classpath:db.properties")
public class RootAppConfig {
	
	//定義從常態檔抓取過來
	@Value("${jdbc.user}")
	String root;
	
	@Value("${jdbc.password}")
	String password;
	
	@Value("${jdbc.jdbcUrl}")
	String jdbcUrl;
	
	@Value("${jdbc.initPoolSize}")
	Integer initPoolSize;
	
	@Value("${jdbc.maxPoolSize}")
	Integer maxPoolSize;
	
	
	
	
	
	
	
	
	
	//DateSource建立一個連線物件
	@Bean
	public DataSource dataSource() {
	    ComboPooledDataSource ds = new ComboPooledDataSource();
	    //測試有沒有成功
		System.out.println("root=" + root);
		System.out.println("password=" + password);
		System.out.println("jdbcUrl=" + jdbcUrl);
		System.out.println("initPoolSize=" + initPoolSize);
		System.out.println("maxPoolSize=" + maxPoolSize);
	    
	    
	    
	    
	    
	    
	    
	    
	    ds.setUser(root);
	    ds.setPassword(password);
//	    try {
//	        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
//	    } catch (PropertyVetoException e) {
//	        e.printStackTrace();
//	    }
	    ds.setJdbcUrl(jdbcUrl);
	    ds.setInitialPoolSize(initPoolSize);
	    ds.setMaxPoolSize(maxPoolSize);
	    return ds;
	}
	//sessione工廠
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
	    LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
	    factory.setDataSource(dataSource());   // dataSource()為定義在前一頁的方法
	    //要掃描哪些永續物別的套件
	    factory.setPackagesToScan(new String[] {
	            "com.web.store.model"
	        });
	     // additionalProperties ()為定義在後面的方法
	    factory.setHibernateProperties(additionalProperties());  
	    return factory;
	}
	//交易管理員
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
	     HibernateTransactionManager txManager = new HibernateTransactionManager();
	     txManager.setSessionFactory(sessionFactory);
	     return txManager;
	}   
	
	
	
	//Properties標準的java 類別 
	// properties.put("key", value);
	private Properties additionalProperties() {
	    Properties properties=new Properties();
	    properties.put("hibernate.dialect", org.hibernate.dialect.MySQL8Dialect.class);
	    properties.put("hibernate.show_sql", Boolean.TRUE);
	    properties.put("hibernate.format_sql", Boolean.TRUE);
	    properties.put("default_batch_fetch_size", 10);
	    properties.put("hibernate.hbm2ddl.auto", "update");
	    return properties;
	}
}
