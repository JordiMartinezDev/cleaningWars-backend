package cleaningwars.com.cleaning_wars.services.interfaces;

public interface PasswordEncoderService {

    String encodePassword(String rawPassword);

    boolean matchPassword(String rawPassword, String encodedPassword);

}
