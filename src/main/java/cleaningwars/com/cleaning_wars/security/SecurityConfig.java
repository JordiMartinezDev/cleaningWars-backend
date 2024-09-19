package cleaningwars.com.cleaning_wars.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import cleaningwars.com.cleaning_wars.security.filter.AuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authFilter = new AuthenticationFilter();
        authFilter.setFilterProcessesUrl("/api/users/authenticate");
        http
            .csrf(csrf -> csrf
                .disable()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()  // Allow register path
                .requestMatchers("/h2/**").permitAll()  // H2 console
                .requestMatchers("/api/**").permitAll()  // Allow access to all the API for dev purposes
                // .requestMatchers("/api/users/login").access(new AuthorizationManager())
                .anyRequest().authenticated()  // All other requests require authentication
                
            ).addFilter(authFilter)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless sessions
            );
            

            return http.build();
    }
}
