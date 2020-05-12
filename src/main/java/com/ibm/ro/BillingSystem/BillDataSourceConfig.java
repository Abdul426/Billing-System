package com.ibm.ro.BillingSystem;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
	    basePackages = "com.ibm.ro.BillingSystem",
	    entityManagerFactoryRef = "billEntityManagerFactory",
	    transactionManagerRef = "billTransactionManager"
	)
public class BillDataSourceConfig {

	@Autowired
	private Environment env;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.billingsystem")
	public DataSourceProperties billDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource billDataSource() {
		DataSourceProperties primaryDataSourceProperties = billDataSourceProperties();
		DataSource ds = DataSourceBuilder.create().driverClassName(primaryDataSourceProperties.getDriverClassName())
				.url(primaryDataSourceProperties.getUrl()).username(primaryDataSourceProperties.getUsername())
				.password(primaryDataSourceProperties.getPassword()).build();
		System.out.println("######### DS Created: " + ds.toString());
		return ds;
	}

	@Bean
	public PlatformTransactionManager billTransactionManager() {
		EntityManagerFactory factory = billEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean billEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(billDataSource());
		factory.setPackagesToScan(new String[] { "com.ibm.ro.BillingSystem.model" });
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
		jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		factory.setJpaProperties(jpaProperties);

		return factory;

	}

}
