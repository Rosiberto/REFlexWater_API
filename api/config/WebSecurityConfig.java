package com.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.service.ImplementsUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers("/register", "/user", "/contact", 
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
		.loginPage("/login")
		.defaultSuccessUrl("/",true)
		.permitAll()
		.and().logout()
		.invalidateHttpSession(false)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/dist/**", "/plugins/**");
	}
}
