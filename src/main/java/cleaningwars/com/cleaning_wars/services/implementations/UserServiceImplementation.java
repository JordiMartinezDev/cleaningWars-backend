package cleaningwars.com.cleaning_wars.services.implementations;

import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.exceptions.EmailAlreadyRegistered;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.exceptions.UserNotFound;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.security.PasswordEncoder;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    HomeService homeService;
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new EmailAlreadyRegistered(newUser.getEmail());
        }

        newUser.setPassword(passwordEncoder.encodePassword(newUser.getPassword()));
        newUser.setHome(homeService.createHome(newUser));
        newUser.setInvitations(null);

        return userRepository.save(newUser);

    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);

    }

    @Override
    public User getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);

    }

    @Override
    public void updateUser(Long id, User updatedUser) {

        User dbUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(dbUser.getEmail())) {
            if (userRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
                throw new EmailAlreadyRegistered(updatedUser.getEmail());
            }
            dbUser.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getUsername() != null) {
            dbUser.setUsername(updatedUser.getUsername());
        }

        if (updatedUser.getPassword() != null) {
            dbUser.setPassword(passwordEncoder.encodePassword(updatedUser.getPassword()));
        }

        userRepository.save(dbUser);

    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException("User with ID " + id + " not found");

    }

    @Override
    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFound(email));

        return user;
    }

}
