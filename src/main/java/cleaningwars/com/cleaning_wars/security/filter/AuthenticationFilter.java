package cleaningwars.com.cleaning_wars.security.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.security.JWTConfig;
import cleaningwars.com.cleaning_wars.security.SecurityConstants;
import cleaningwars.com.cleaning_wars.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    @Autowired
    CustomAuthenticationManager authenticationManager;
    @Autowired
    JWTConfig jwtConfig;

    @Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
        User loginRequest = new ObjectMapper().readValue(request.getInputStream(), User.class);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
        );

        return authenticationManager.authenticate(authentication);

    }
    
    catch (IOException e) {
        
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return null;
    }
}

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        throw new BadCredentialsException("Incorrect User or Password");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
    String secretKey = jwtConfig.getSecretKey();

    if (secretKey == null || secretKey.isEmpty()) {
        throw new IllegalArgumentException("Secret key for JWT signing cannot be null or empty.");
    }

        String token = JWT.create()
        .withSubject(authResult.getName())
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
        .sign(Algorithm.HMAC512(secretKey));

        String refreshToken = JWT.create()
            .withSubject(authResult.getName())
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_TOKEN_EXPIRATION)) // longer expiration
            .sign(Algorithm.HMAC512(secretKey));

            response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
            response.addHeader(SecurityConstants.REFRESH_TOKEN, refreshToken); 
    }
}
