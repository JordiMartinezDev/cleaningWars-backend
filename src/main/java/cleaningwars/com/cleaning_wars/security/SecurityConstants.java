package cleaningwars.com.cleaning_wars.security;

public class SecurityConstants {

    public static final String REGISTER_PATH = "/api/users/register";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REFRESH_TOKEN = "Refresh-token";
    public static final String BEARER = "Bearer ";
    public static final int TOKEN_EXPIRATION = 2 * 60 * 60 * 1000;
    public static final int REFRESH_TOKEN_EXPIRATION = 30 * 24 * 60 * 60 * 1000;

}
