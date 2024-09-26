package cleaningwars.com.cleaning_wars.services.implementations;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.factories.HomeFactory;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;

@Service
public class HomeServiceImplementation implements HomeService{

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    HomeFactory homeFactory;  

    
    public Home createHome(User user) {
        return homeFactory.createDefaultHome(user);
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

    public void removeUserFromHome(Long homeId, Long userId) {

        Home home = homeRepository.findById(homeId)
                                  .orElseThrow(() -> new RuntimeException("Home not found"));
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new RuntimeException("User not found"));

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
        homeRepository.deleteById(id);
    }

    @Override
    public void inviteUserToHome(Long newHomeId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inviteUserToHome'");
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
    
}
