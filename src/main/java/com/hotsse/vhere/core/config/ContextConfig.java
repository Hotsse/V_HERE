package com.hotsse.vhere.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.hotsse.vhere.core.interceptor.AuthInterceptor;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class ContextConfig implements WebMvcConfigurer {

	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        templateEngine.addDialect(new LayoutDialect()); // Thymeleaf Layout 적용
        return templateEngine;
    }

	@Bean
    public ClassLoaderTemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/"); // 모든 뷰 페이지는 /resources/templates/ 내부에서 검색한다.
        templateResolver.setSuffix(".html"); // 모든 뷰 페이지는 .html 이다.
        templateResolver.setTemplateMode("HTML"); // HTML 형식으로 읽는다.
        templateResolver.setCacheable(false); // 캐싱하지 않는다.
        return templateResolver;
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
			.excludePathPatterns("/user/login")
			.excludePathPatterns("/user/signup");		
	}
}
