package com.ecommerce.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Configuration
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		 return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		 return new String[] { "/" };
	}
	
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    	registration.setMultipartConfig(getMultipartConfigElement());
    	registration.setAsyncSupported(true);
    }
    
    @Bean
    protected MultipartConfigElement getMultipartConfigElement() {
    	return new MultipartConfigElement( null, 5000000, 5000000, 0);
    } 

}
