package dev.fringe.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.dexcoder.demo.quartz.JobFactoryConfiguration;

@Configuration
public class SchedulerConfig {
	
	@Bean
	public JobFactoryConfiguration jobFactory() {
		return new JobFactoryConfiguration();
	}
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactoryConfiguration jobFactory) {
		SchedulerFactoryBean factoryBean= new SchedulerFactoryBean();
		factoryBean.setJobFactory(jobFactory);
		return factoryBean;
	}
}
