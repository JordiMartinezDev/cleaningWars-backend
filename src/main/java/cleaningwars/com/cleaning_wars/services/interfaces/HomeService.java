package cleaningwars.com.cleaning_wars.services.interfaces;

import java.util.List;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;

public interface HomeService {

    Home createDefaultHome();
    void createHome(Home home);
    Home getHomeById(Long id);
    List<Home> getAllHomes();
    void editHomeName(Home home, Long id);
    void addUserToHome(Long homeId, User user); 
    void removeUserFromHome(Long homeId, Long userId); 
    void deleteHome(Long id);
} 
