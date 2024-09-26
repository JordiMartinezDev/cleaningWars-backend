package cleaningwars.com.cleaning_wars.exceptions;

public class UserNotFound extends RuntimeException {

    public UserNotFound(Long id) {
        super("The user with id " + id + " doesn't exist");

    }
}
