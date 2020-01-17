package com.khaled.BipBackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.khaled.BipBackend.dto")
@EnableTransactionManagement
public class HibernateConfig {

	// H2 Database Configuration params...
	private final static String H2_DATABASE_URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String H2_DATABASE_DRIVER = "org.h2.Driver";
	private final static String H2_DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private final static String H2_DATABASE_USERNAME = "sa";
	private final static String H2_DATABASE_PASSWORD = "";

	// MySQL Database Configuration params...
	private final static String MYSQL_DATABASE_URL = "jdbc:mysql://localhost:3306/onlineshopping?characterEncoding=latin1";
	private final static String MYSQL_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private final static String MYSQL_DATABASE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	private final static String MYSQL_DATABASE_USERNAME = "root";
	private final static String MYSQL_DATABASE_PASSWORD = "root";

	// Creating DataSouce Bean
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(MYSQL_DATABASE_DRIVER);
		dataSource.setUrl(MYSQL_DATABASE_URL);
		dataSource.setUsername(MYSQL_DATABASE_USERNAME);
		dataSource.setPassword(MYSQL_DATABASE_PASSWORD);

		return dataSource;
	}

	// Creating SessionFactory Bean
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.khaled.OnlineShoppingBackend.dto");

		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", MYSQL_DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");

		return properties;
	}

	// Creating HibernateTransactionManager Bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
