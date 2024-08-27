package cleaningwars.com.cleaning_wars.services;

import cleaningwars.com.cleaning_wars.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
