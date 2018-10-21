package com.nekoo.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author qxnkeoo
 */
@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	@Bean
	/**
	 * 自定义Filter
	 */
	public FilterRegistrationBean httpFilter(){
		//新建一个FilterRegistratioBean 对象
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		//给该对象设置一个我们自己定义的filter对象
		registrationBean.setFilter(new HttpFilter());
		//设置filter拦截的地址url
		registrationBean.addUrlPatterns("/threadLocal/*");
		//返回该对象
		return registrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}

}
