package cleaningwars.com.cleaning_wars.services.interfaces;

import java.util.List;

import cleaningwars.com.cleaning_wars.entities.User;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);    
    User getUserByUsername(String username);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
