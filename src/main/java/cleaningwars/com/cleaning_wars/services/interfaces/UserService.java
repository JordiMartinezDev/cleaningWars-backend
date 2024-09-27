package cleaningwars.com.cleaning_wars.services.interfaces;

import cleaningwars.com.cleaning_wars.entities.User;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

}
