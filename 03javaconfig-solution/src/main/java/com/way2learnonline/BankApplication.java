package com.way2learnonline;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.way2learnonline.service.BankService;



@Configuration
@ComponentScan
//@PropertySource("classpath:db.properties")	

//@SpringBootApplication
@EnableAutoConfiguration
public class BankApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ConfigurableApplicationContext context = SpringApplication.run(BankApplication.class);
		BankService bankService =	context.getBean(BankService.class);
		try {
			bankService.transfer(1L, 2L, 2000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//@Value("classpath:dbscripts.sql")private Resource dbscript;
	@Autowired
	private Environment env;
	//one more way of writing 
	@Bean
	public DataSource dataSource(@Value("${db.driverclassname}") String driverClassName, @Value("${db.url}")
			String url , @Value("${db.username}") String username,@Value("${db.password}") String password)
{		
		BasicDataSource dataSource= new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty(driverClassName));
  dataSource.setUrl(env.getProperty(url));
		dataSource.setUsername(env.getProperty(password));
	dataSource.setPassword(env.getProperty(username));
	return dataSource;		
}

/*
	//second day of writing 
	@Bean
	@ConfigurationProperties(prefix="db")
	public DataSource dataSource(){		
         BasicDataSource dataSource= new BasicDataSource();
	
	return dataSource;		
}
	*/
	
	/*@Bean(name="ds")
	public DataSource dataSource(){		
		BasicDataSource dataSource= new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/springdb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;		
	}
	


	
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(DataSource dataSource){
//		DataSourceInitializer initializer= new DataSourceInitializer();
//		initializer.setDataSource(dataSource);
//		initializer.setDatabasePopulator(databasePopulator());
//		return initializer;
//	}
//	
//	private DatabasePopulator databasePopulator() {
//	     ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//	    populator.addScript(dbscript);		   
//	    return populator;
//	}

}*/
}