package cleaningwars.com.cleaning_wars.exceptions;

public class EmailAlreadyRegistered extends RuntimeException {

    public EmailAlreadyRegistered(String email) {

        super("The email " + email + " already exists.");

    }
}
