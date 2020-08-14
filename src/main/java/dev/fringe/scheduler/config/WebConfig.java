package dev.fringe.scheduler.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfig.class };
	}

	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { CommonConfig.class, SchedulerConfig.class};
	}

	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter };
	}
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
