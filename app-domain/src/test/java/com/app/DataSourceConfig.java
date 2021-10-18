package com.app;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

//@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory" ,basePackages = "com.app.repository.UserRepository")
public class DataSourceConfig {
	
	
	
	   @Bean(name = "datasource")
	    public DataSource datasource() {
	        return DataSourceBuilder.create()
	          .driverClassName("com.mysql.cj.jdbc.Driver")
	          .url("jdbc:mysql://localhost:3306/salebd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
	          .username("root")
	          .password("mahjoub")
	          .build();	
	    }


	   @Bean(name = "entityManagerFactory")
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("datasource") DataSource dataSource) {
		   HashMap<String,Object> map = new HashMap<String, Object>();
		   map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		   map.put("hibernate.hbm2ddl.auto" ,"create"); 
		   return builder.dataSource(dataSource).properties(map).packages("com.app.entity").persistenceUnit("AppUser").build();
	   }
	   
	   
}
