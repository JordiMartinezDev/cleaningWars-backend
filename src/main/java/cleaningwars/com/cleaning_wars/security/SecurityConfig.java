package cleaningwars.com.cleaning_wars.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import cleaningwars.com.cleaning_wars.security.filter.AuthenticationFilter;
import cleaningwars.com.cleaning_wars.security.filter.ExceptionHandlerFilter;
import cleaningwars.com.cleaning_wars.security.filter.JWTAuthorizationFilter;
import cleaningwars.com.cleaning_wars.security.manager.CustomAuthenticationManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomAuthenticationManager customAuthenticationManager;
    @Autowired
    JWTConfig jwtConfig;
    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authFilter = new AuthenticationFilter(customAuthenticationManager, jwtConfig);
        authFilter.setFilterProcessesUrl("/api/users/authenticate");
        http
            .csrf(csrf -> csrf
                .disable()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()  // Allow register path
                .requestMatchers("/h2/**").permitAll()  // H2 console
                .requestMatchers("/api/**").permitAll()  // Allow access to all the API for dev purposes
                .anyRequest().authenticated()  // All other requests require authentication
                
            )
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authFilter)
            .addFilterAfter(jwtAuthorizationFilter, AuthenticationFilter.class)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless sessions
            );
            

            return http.build();
    }
}
