package cleaningwars.com.cleaning_wars.services.implementations;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.exceptions.EmptyInput;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.factories.HomeFactory;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.security.JWTConfig;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;

@Service
@Lazy
public class HomeServiceImplementation implements HomeService {

    @Autowired
    @Lazy
    HomeRepository homeRepository;

    @Autowired
    @Lazy
    UserRepository userRepository;

    @Autowired
    @Lazy
    HomeFactory homeFactory;

    @Autowired
    @Lazy
    UserService userService;

    public Home createHome(User user) {

        return homeFactory.createDefaultHome(user);

    }

    public void addUser(Long homeId, User user) {

        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new EntityNotFound(homeId, Home.class));

        // This should never happen, homes must have a user always, otherwise a home is
        // deleted
        if (home.getUsers() == null) {
            home.setUsers(new HashSet<>());
        }

        home.getUsers().add(user);
        user.setHome(home);

        userRepository.save(user);
        homeRepository.save(home);
    }

    public Home getHomeById(Long id) {

        return homeRepository.findById(id).orElse(null);

    }

    public List<Home> getAllHomes() {

        return (List<Home>) homeRepository.findAll();

    }

    public void editHomeName(Home home, Long id) {

    }

    public void addUserToHome(Long homeId, User user) {

        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new RuntimeException("Home not found"));

        if (home.getUsers() == null) {
            home.setUsers(new HashSet<>());
        }

        home.getUsers().add(user);
        user.setHome(home);

        userRepository.save(user);
        homeRepository.save(home);

    }

    @Override
    public void removeUserFromHome(Long homeId, Long userId) {

        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new EntityNotFound(homeId, Home.class));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound(userId, User.class));

        if (home.getUsers() != null && home.getUsers().contains(user)) {
            home.getUsers().remove(user);
            user.setHome(null);

            userRepository.save(user);
            homeRepository.save(home);
        } else {
            throw new RuntimeException("User does not belong to this Home");
        }
    }

    public void deleteHome(Long id) {

        Home home = homeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(id, Home.class));

        homeRepository.delete(home);
    }

    @Override
    public void inviteUserToHome(Long newHomeId, Long userId, String token) {
        JWTConfig secretKey = new JWTConfig();
        String userEmail = JWT.require(Algorithm.HMAC512(secretKey.getSecretKey()))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject();

        User inviter = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        if (!inviter.getHome().getId().equals(newHomeId)) {
            throw new RuntimeException("You can only invite users to your own home.");
        }

        User userToInvite = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User to invite not found"));

        addUserToHome(newHomeId, userToInvite);
    }

    @Override
    public void acceptInvitation(Long invitationId) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptInvitation'");

    }

    @Override
    public void declineInvitation(Long invitationId) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'declineInvitation'");

    }

    @Override
    public void updateHomeName(Long homeId, String newName) {

        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new EntityNotFound(homeId, Home.class));

        if (newName == null || newName.trim().isEmpty() || newName.charAt(0) == ' ') {
            throw new EmptyInput("name");
        }

        home.setName(newName);
        homeRepository.save(home);

    }

}
