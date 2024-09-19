package cleaningwars.com.cleaning_wars.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entity.Home;
import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.UserRepository;

@Service
public class HomeServiceImplementation implements HomeService{

    @Autowired
    HomeRepository homeRepository;
    @Autowired
    UserRepository userRepository;

    public Home createDefaultHome(){
        return new Home();
    }
    public void createHome(Home home){
        homeRepository.save(home);
    }
    public Home getHomeById(Long id){
        return homeRepository.findById(id).orElse(null);
    }

    public List<Home> getAllHomes(){
        return (List<Home>)homeRepository.findAll();
    }
    public void editHomeName(Home home, Long id){

    }
    public void addUserToHome(Long homeId, User user){
    Home home = homeRepository.findById(homeId).orElseThrow(() -> new RuntimeException("Home not found"));

    if (home.getUsers() == null) {
        home.setUsers(new HashSet<>());
    }

    home.getUsers().add(user);
    user.setHome(home);

    userRepository.save(user);
    homeRepository.save(home);
    }
    public void removeUserFromHome(Long homeId, Long userId) {
        //Retrieve both user and Home
        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new RuntimeException("Home not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        // Ensure user belongs to home before attempting to remove it
        if (home.getUsers() != null && home.getUsers().contains(user)) {

            home.getUsers().remove(user);
            user.setHome(null);
    
            // Save both entities to repo/DB
            userRepository.save(user);
            homeRepository.save(home);
        } else {
            throw new RuntimeException("User does not belong to this Home");
        }
    }

    public void deleteHome(Long id){
        homeRepository.deleteById(id);
    }

    
}
