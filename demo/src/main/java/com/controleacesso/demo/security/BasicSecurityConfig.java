package com.controleacesso.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@Configuration
@EnableWebMvc
public class BasicSecurityConfig{	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http			
			.csrf( csrf -> csrf.disable())
//			.cors((cors -> cors.disable()))
			.httpBasic(httpBasic -> httpBasic.init(http))
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers(HttpMethod.GET, "/api/*").authenticated()
//				.requestMatchers(HttpMethod.GET, "/api/acesso/user/*").permitAll()
//				.requestMatchers(HttpMethod.PUT, "/api/acesso/").permitAll()
//				.requestMatchers(HttpMethod.POST, "/api/*").permitAll()
				.requestMatchers(HttpMethod.POST, "api/user/cadastrar").permitAll()
				.requestMatchers(HttpMethod.POST, "api/user/logar").permitAll()	
				.anyRequest().authenticated()
			);

		return http.build();
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
