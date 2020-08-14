package dev.fringe.scheduler.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dev.fringe.scheduler.Application.ApplicationProfile;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "com.dexcoder.demo.service")
@Log4j2
@EnableTransactionManagement
@EnableJpaRepositories("com.dexcoder.demo.repositories")
@EntityScan("com.dexcoder.demo.model")
@EnableAutoConfiguration
public class CommonConfig implements InitializingBean{
	
	@Autowired Environment env;

	
	@Profile("LOC")
	@Bean
	public void loc() {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, ApplicationProfile.LOC);
	}

	@Profile("!LOC")
	@Bean
	public void notLoc() {
//		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME));
	}

	@SneakyThrows
	public void afterPropertiesSet(){
		log.info("initialized");
	}
	
	public class DataBaseConfig {
		@Value("${app.jdbc.driver}")
		private String jdbcDriver;
		@Value("${app.jdbc.url}")
		private String jdbcUrl;	
		@Value("${app.jdbc.user}")
		private String jdbcUser;	
		@Value("${app.jdbc.password}")
		private String jdbcPassword;	
		@Value("${jdbcTemplate.fetchSize}")
		private int fetchSize;
		
		@Bean
		public DataSource dataSource() {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(jdbcDriver);
			dataSource.setUrl(jdbcUrl);
			dataSource.setUsername(jdbcUser);
			dataSource.setPassword(jdbcPassword);
			return dataSource;
		}
//		@Bean
//		public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//			jdbcTemplate.setFetchSize(fetchSize);
//			return jdbcTemplate;
//		}
//		@Bean
//		public JdbcDaoImpl jdbcDao(JdbcTemplate jdbcTemplate) {
//			JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
//			jdbcDaoImpl.setJdbcTemplate(jdbcTemplate);
//			return jdbcDaoImpl;
		}	

}
