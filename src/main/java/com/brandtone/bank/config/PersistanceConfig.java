package com.brandtone.bank.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.brandtone.bank.persist.AccountRepository;
import com.brandtone.bank.persist.TransactionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.sql.SQLException;;

@Configuration
@EnableJpaRepositories(basePackages = "com.brandtone.bank.persist",
	includeFilters = @ComponentScan.Filter(value = {AccountRepository.class, TransactionRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
@EnableTransactionManagement
public class PersistanceConfig {

	@Bean
	  public DataSource dataSource() throws SQLException {

	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    return builder.setType(EmbeddedDatabaseType.H2).addDefaultScripts().build();
	  }
	
	  @Bean
	  public EntityManagerFactory entityManagerFactory() throws SQLException {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("com.brandtone.bank.domain");
	    factory.setDataSource(dataSource());
	    factory.afterPropertiesSet();

	    return factory.getObject();
	  }

	  @Bean
	  public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
	    return entityManagerFactory.createEntityManager();
	  }

	  @Bean
	  public PlatformTransactionManager transactionManager() throws SQLException {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory());
	    return txManager;
	  }

	  @Bean
	  public HibernateExceptionTranslator hibernateExceptionTranslator() {
	    return new HibernateExceptionTranslator();
	  }
	}
