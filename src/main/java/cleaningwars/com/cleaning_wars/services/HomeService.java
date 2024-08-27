package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import cleaningwars.com.cleaning_wars.entity.Home;

public interface HomeService {

    void createHome(Home home);
    Home getHomeById(Long id);
    List<Home> getAllHomes();
    void editHomeName(Home home, Long id);
    void addUserToHome(); // addUserToHome(User user, Long id) once users are created
    void removeUserFromHome(); // same as above
    void deleteHome(Long id);
} 
