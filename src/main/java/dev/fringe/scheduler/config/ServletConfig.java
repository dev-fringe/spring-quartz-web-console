package dev.fringe.scheduler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.mjeanroy.springmvc.view.mustache.configuration.EnableMustache;
import com.github.mjeanroy.springmvc.view.mustache.configuration.MustacheProvider;

@ComponentScan(basePackages = "com.dexcoder.demo.*")
@EnableWebMvc
@EnableScheduling
@Configuration
@EnableAspectJAutoProxy
public class ServletConfig implements WebMvcConfigurer {
	
	@EnableMustache(provider = MustacheProvider.AUTO)
	public class DctmRestMustachViewConfig {
	}

}
