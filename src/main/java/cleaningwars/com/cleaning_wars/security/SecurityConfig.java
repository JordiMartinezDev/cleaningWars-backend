package cleaningwars.com.cleaning_wars.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection (not needed for stateless APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll() // Allow any user without any authorization to register
                .requestMatchers("/api/**").permitAll()  // Allow access to all API endpoints without authentication, just development purposes, comment this line to require auth
                .requestMatchers("*/h2/**").permitAll()
                .anyRequest().authenticated()  // Any other request requires authentication
            )
            .httpBasic(Customizer.withDefaults())  // Enable HTTP Basic authentication
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless session
            )
            .build();
    }

    

    
}
