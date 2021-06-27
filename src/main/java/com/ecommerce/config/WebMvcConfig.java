package com.ecommerce.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.ecommerce.*")
public class WebMvcConfig implements WebMvcConfigurer {
	
	// get resource from src/main
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
       // registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");    
        registry.addViewController("/admin/home").setViewName("adminhome");
    }
	
	@Bean
    public StandardServletMultipartResolver getMultipartResolver() {
    	return new StandardServletMultipartResolver();
    }
		
	
	@Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();	
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
 
        mailSender.setUsername("ffcc298e4bad71");
        mailSender.setPassword("67f8b716802c51");
 
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
 
        return mailSender;
    }
}
