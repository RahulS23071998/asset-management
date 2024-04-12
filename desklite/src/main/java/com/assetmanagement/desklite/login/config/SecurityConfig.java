package com.assetmanagement.desklite.login.config;

import com.assetmanagement.desklite.login.filter.JwtAuthFilter;
import com.assetmanagement.desklite.login.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Value("${allowed.origins}")
	private String allowedOrigin;

	@Autowired
	private JwtAuthFilter authFilter;

	// User Creation
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize ->
						authorize
								// Permit unrestricted access
								.requestMatchers(HttpMethod.GET).permitAll()
								.requestMatchers("/api/employee/**").permitAll()
								.requestMatchers("/api/v1/**").permitAll()
								.requestMatchers("/api/dashboard/**").permitAll()
								.requestMatchers(
										"/auth/welcome",
										"/auth/addNewUser",
										"/auth/generateToken",
										"/auth/userSignUp",
										"/auth/companySignUp",
										"/auth/getAllEmployee",
										"/user/logout"
								).permitAll()
								.requestMatchers("/swagger-ui/**").permitAll()
								.requestMatchers("/v3/api-docs/**").permitAll()
								// Require authentication
								.requestMatchers("/auth/user/**").authenticated()
								.requestMatchers("/auth/admin/**").authenticated())

				.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsCconfig = new CorsConfiguration();
				corsCconfig.setAllowedOrigins(Collections.singletonList(allowedOrigin));
				corsCconfig.setAllowedMethods(Collections.singletonList("*"));
				corsCconfig.setAllowCredentials(true);
				corsCconfig.setAllowedHeaders(Collections.singletonList("*"));
				corsCconfig.setExposedHeaders(Collections.singletonList("Authorization"));
				corsCconfig.setMaxAge(3600L);
				return corsCconfig;
			}
		}))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}


	// Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
