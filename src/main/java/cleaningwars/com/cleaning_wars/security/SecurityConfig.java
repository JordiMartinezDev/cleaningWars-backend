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
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf
                .disable()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()  // Allow register path
                .requestMatchers("/h2/**").permitAll()  // Allow H2 console access
                .requestMatchers("/api/**").permitAll()  // Allow access to API for dev purposes
                .anyRequest().authenticated()  // All other requests require authentication
            )
            .httpBasic(Customizer.withDefaults())  // Enable HTTP Basic auth
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless sessions
            )
            .build();
    }
}
