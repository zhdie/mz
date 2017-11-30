package com.deloitte.hk.boot;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {	
	
	@Bean  
    public FilterRegistrationBean filterRegistrationBean(AccesssFilter serviceFilter){  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(serviceFilter);  
        filterRegistrationBean.setEnabled(true);
        String[] patterns = new String[]{"/*","/project/*", "/login/*", "/isync/*", "/order/*", "/project/*"};
        filterRegistrationBean.addUrlPatterns(patterns);  
        return filterRegistrationBean;  
    }
	

}
