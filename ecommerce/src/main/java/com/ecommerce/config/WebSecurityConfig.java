package com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecommerce.authentication.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("myDBAuthenticationService")
	private MyDBAuthenticationService myDBAuthenticationService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/manageProductList", "/product","/accountInfo").access("hasAnyRole('EMPLOYEE','MANAGER')");
		
		http.authorizeRequests().antMatchers("/accountList", "/account","/type","/typeList","/producer","/producerList","/orderlist",
				"/order","/manageProductList/producttype").access("hasRole('MANAGER')");
		
		http.authorizeRequests().antMatchers("/buyProduct","/shoppingCart","/myOrderList").access("hasRole('USER')");
		
		
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/userLogin")
			.defaultSuccessUrl("/accountInfo")
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/home");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider());
		auth.userDetailsService(myDBAuthenticationService);
	}
	

	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(myDBAuthenticationService);
	    authProvider.setPasswordEncoder(bCryptPasswordEncoder()); 
	    return authProvider; 
	}
	
}
