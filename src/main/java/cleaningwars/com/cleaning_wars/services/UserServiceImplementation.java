package cleaningwars.com.cleaning_wars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    HomeService homeService;

    @Override
    public User createUser(User user) {
        user.setHome(homeService.createDefaultHome());
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, User user){
        
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
