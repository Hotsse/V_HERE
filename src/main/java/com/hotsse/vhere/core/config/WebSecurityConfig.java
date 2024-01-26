package com.hotsse.vhere.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hotsse.vhere.user.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/test_db/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/manifest.json").permitAll()
				.antMatchers("/sw.js").permitAll()
				.antMatchers("/firebase-messaging-sw.js").permitAll()
				.antMatchers("/asset/**").permitAll()
				.antMatchers("/.well-known/assetlinks.json").permitAll()
				.antMatchers("/index.html").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/login")
				.defaultSuccessUrl("/board/list")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/user/login")
				.invalidateHttpSession(true)
				.permitAll();
		
		// H2 연동을 위한 disable 처리
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
}
