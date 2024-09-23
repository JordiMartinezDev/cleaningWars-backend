package cleaningwars.com.cleaning_wars.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig {

    @Value("${jwt.secret}")
    private  String secretKey;  // This should map to your property

    public  String getSecretKey() {
        return secretKey;
    }
}
