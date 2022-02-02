package com.zee.zee5app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zee.zee5app.utils.PasswordUtils;

@Configuration
//@ComponentScan("com.zee.zee5app")
//@PropertySource("classpath:application.properties")
public class Config {

//	@Autowired
//	Environment env;
//
//	@Bean
//	@Scope("singleton")
//	public DataSource dataSource() {
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setUsername(env.getProperty("jdbc.username"));
//		basicDataSource.setPassword(env.getProperty("jdbc.password"));
//		basicDataSource.setUrl(env.getProperty("jdbc.url"));
//		basicDataSource.setDefaultAutoCommit(false);
//		basicDataSource.setMaxTotal(20);
//		return basicDataSource;
//	}

	@Bean
	public PasswordUtils passwordUtils() {
		return new PasswordUtils();
	}

}
