package cleaningwars.com.cleaning_wars.security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");

        try{
            filterChain.doFilter(request, response);
        }
        catch(RuntimeException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
}
