package cleaningwars.com.cleaning_wars.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.security.PasswordEncoder;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserService userService;
    private PasswordEncoder pwEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userService.getUserByEmail(authentication.getName());
        if (!pwEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect Login/Password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
    }

}
