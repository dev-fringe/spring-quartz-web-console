package dev.fringe.scheduler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = "dev.fringe.scheduler.*")
@EnableWebMvc
//@EnableScheduling
@Configuration
//@EnableAspectJAutoProxy
//@EnableMustache
public class ServletConfig implements WebMvcConfigurer {
		
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
       resourceHandlerRegistry.addResourceHandler("/**").addResourceLocations("classpath:/dist/");
    }

//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//		UrlPathHelper urlPathHelper = new UrlPathHelper();
//		urlPathHelper.setAlwaysUseFullPath(true);
//		configurer.setUrlPathHelper(urlPathHelper);
//	}
}
