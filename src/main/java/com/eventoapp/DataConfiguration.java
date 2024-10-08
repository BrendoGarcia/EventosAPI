package com.eventoapp;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfiguration {

@Bean
public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl("jdbc:mysql://sql10.db4free.net:3306/projetinho");
				dataSource.setUsername("brendofcghh");
				dataSource.setPassword("qwer1234");
				return dataSource;
				
}
	
@Bean 
public JpaVendorAdapter jpaVendorAdapter() {
			HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
				adapter.setDatabase(Database.MYSQL);
				adapter.setShowSql(true);
				adapter.setGenerateDdl(true);
				adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
				adapter.setPrepareConnection(true);
				return adapter;
}
	
	
}
