package cleaningwars.com.cleaning_wars.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cleaningwars.com.cleaning_wars.security.JWTConfig;
import cleaningwars.com.cleaning_wars.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

    @Autowired
    JWTConfig jwtConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


            if (request.getRequestURI().equals(SecurityConstants.REGISTER_PATH)) {
                filterChain.doFilter(request, response);
                return;
            }
            
            // Retrieving and remove the "Bearer" to get the token itself
            String header = request.getHeader("Authorization");
            String token = header.replace(SecurityConstants.BEARER, "");
            String user = JWT.require(Algorithm.HMAC512(jwtConfig.getSecretKey()))
                .build()
                .verify(token)
                .getSubject();

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
    }
    
}
