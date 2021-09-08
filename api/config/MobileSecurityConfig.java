package com.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.service.ImplementsUserDetailsServiceMobile;

@Configuration
@Order(1)
@EnableWebSecurity
public class MobileSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsServiceMobile userDetailsServiceMobile;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.antMatcher("/mobile/**")
			.authorizeRequests()
				.antMatchers(//"/mobile/register",						
							 "/user-mobile",				   							 
							 "/contact", 
							 "/configuration/ui",
							 "/configuration/security",
							 "/webjars/**",
							 "/api/v2/**").permitAll()			
				.anyRequest().authenticated()
			.and().formLogin().loginPage("/mobile/login")
				.defaultSuccessUrl("/mobile/page", true)
				.failureUrl("/mobile/accessdenied")
			.permitAll()
			.and().logout()
					.invalidateHttpSession(false)					
			.and().logout().logoutUrl("/mobile/logout").logoutSuccessUrl("/mobile/login-mobile")
			.and().exceptionHandling().accessDeniedPage("/mobile/accessdenied");

		http.csrf().disable();
	
		/*
		http.csrf().disable().authorizeRequests()
								.antMatchers("/register",
											 "/user",				   							 
				   							 "/contact", 
				   							 "/v2/api-docs", 
				   							 "/swagger-resources",
				   							 "/swagger-resources/**",
				   							 "/configuration/ui",
				   							 "/configuration/security",
				   							 "/webjars/**",
				   							 "/swagger-ui.html",
				   							 "/api/v2/**").permitAll()
				   			.anyRequest().authenticated()
				   			.and().formLogin()
				   			.loginPage("/loginMobile")
				   			.defaultSuccessUrl("/mobile",true)
				   			.permitAll()
				   			.and().logout()
				   			.invalidateHttpSession(false)
				   			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				   			.permitAll();
		*/
				   			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsServiceMobile)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/dist/**", "/plugins/**");
	}
}
