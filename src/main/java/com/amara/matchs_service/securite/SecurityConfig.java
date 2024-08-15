package com.amara.matchs_service.securite;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		return new InMemoryUserDetailsManager(
				User.withUsername("user1").password("{noop}1234").authorities("USER").build(),
				User.withUsername("user2").password("{noop}12345").authorities("USER").build(),
				User.withUsername("admin").password("{noop}54321").authorities("ADMIN").build()
					);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf->csrf.disable())
				.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers(h->h.frameOptions(fo->fo.disable()))
				.authorizeHttpRequests(ar->ar.requestMatchers("/league/**").permitAll())
				.authorizeHttpRequests(ar->ar.anyRequest().authenticated())
				.build();
		
	}

}
