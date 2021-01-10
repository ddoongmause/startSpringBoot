package com.ddoongmause.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	/*
	 * @Autowired DataSource dataSource;
	 */
	@Autowired
	DdoongmauseUsersService ddoongmauseUsersService;
	
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
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		// 세션 무효화
		//http.logout().invalidateHttpSession(true);
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		http.userDetailsService(ddoongmauseUsersService);
		
	}
	
	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder
	 * auth)throws Exception{ log.info("build Auth global...........");
	 * 
	 * String query1 =
	 * "SELECT uid username, CONCAT('{noop}', upw) password, true enabled FROM tbl_members WHERE uid=?"
	 * ;
	 * 
	 * String query2 =
	 * "SELECT member uid, role_name role FROM tbl_member_roles WHERE member = ?";
	 * auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1)
	 * .rolePrefix("ROLE_").authoritiesByUsernameQuery(query2);
	 * //auth.inMemoryAuthentication().withUser("manager").password("{noop}1111").
	 * roles("MANAGER"); }
	 */
	
	
}
