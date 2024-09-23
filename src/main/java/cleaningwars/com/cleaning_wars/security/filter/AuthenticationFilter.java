package cleaningwars.com.cleaning_wars.security.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.security.JWTConfig;
import cleaningwars.com.cleaning_wars.security.SecurityConstants;
import cleaningwars.com.cleaning_wars.security.manager.CustomAuthenticationManager;
import jakarta.persistence.EntityNotFoundException;
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
        // Deserialize the request body into a User object
        User loginRequest = new ObjectMapper().readValue(request.getInputStream(), User.class);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), // Or loginRequest.getEmail() depending on your logic
                loginRequest.getPassword()
        );

        // Delegate the authentication process to the authentication manager
        return authenticationManager.authenticate(authentication);

    }
    
    catch (IOException e) {
        
        // Return a 400 Bad Request response with the error message
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return null;
    }
}

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        throw new BadCredentialsException("Incorrect User or Password"); // Replace for WrongPwException
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                // Retrieve the secret key from JwtConfig
    String secretKey = jwtConfig.getSecretKey();

    // Check if the secret key is null or empty (add logging for better visibility)
    if (secretKey == null || secretKey.isEmpty()) {
        throw new IllegalArgumentException("Secret key for JWT signing cannot be null or empty.");
    }

        String token = JWT.create()
        .withSubject(authResult.getName())
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
        .sign(Algorithm.HMAC512(secretKey));
        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
    }
}
