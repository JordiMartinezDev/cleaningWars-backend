package cleaningwars.com.cleaning_wars.services;

import cleaningwars.com.cleaning_wars.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);    
    User getUserByUsername(String username);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
