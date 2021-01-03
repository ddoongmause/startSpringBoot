package com.ddoongmause.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		log.info("security config.............");
		
		http.authorizeRequests()
			.antMatchers("/guest/**").permitAll()
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		http.formLogin().loginPage("/login");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		log.info("build Auth global...........");
		
		auth.inMemoryAuthentication().withUser("manager").password("{noop}1111").roles("MANAGER");
	}

	
}
