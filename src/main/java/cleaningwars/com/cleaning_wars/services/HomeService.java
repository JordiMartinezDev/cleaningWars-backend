package cleaningwars.com.cleaning_wars.services;

import cleaningwars.com.cleaning_wars.entity.Home;

public interface HomeService {

    public void createHome(Home home);
    public void editHomeName(Home home, Long id);
    public void addUserToHome(); // addUserToHome(User user, Long id) once users are created
    public void removeUserFromHome(); // same as above
    public void removeHome(Long id);
} 
