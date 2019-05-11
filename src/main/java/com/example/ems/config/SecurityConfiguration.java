package com.example.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ems.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder authenticationMgr)
	// throws Exception {
	// authenticationMgr.inMemoryAuthentication()
	// .withUser("admin").password("admin").authorities("admin")
	// .and()
	// .withUser("shiva").password("shiva").authorities("admin");
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/", "/css/**.css", "/index.html", "/public/**", "/getLoggedInUser","/favicon.ico", "/**.js").permitAll()
				.antMatchers("/employee/**").hasAuthority("EMPLOYEE")
				.antMatchers("/public/**").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/hr/**").hasAuthority("HR")
				.anyRequest().authenticated().and().csrf().disable().logout().logoutSuccessUrl("/");
	};

}
