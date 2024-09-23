package cleaningwars.com.cleaning_wars.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }
        catch(EntityNotFoundException e){

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().write("User not found");
        response.getWriter().flush();
        
    } 
        catch(RuntimeException e){

            // Optionally include stack trace in the response for debugging purposes (not recommended in production)
            // StringWriter sw = new StringWriter();
            // e.printStackTrace(new PrintWriter(sw));
            // String exceptionAsString = sw.toString();

            // Set error status and return exception message
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            // response.getWriter().write("Error occurred: " + exceptionAsString);
        }
    }
    
}
