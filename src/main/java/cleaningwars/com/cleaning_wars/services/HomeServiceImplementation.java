package cleaningwars.com.cleaning_wars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entity.Home;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;

@Service
public class HomeServiceImplementation implements HomeService{

    @Autowired
    HomeRepository homeRepository;

    public void createHome(Home home){
        homeRepository.save(home);
    }
    public void editHomeName(Home home, Long id){

    }
    public void addUserToHome(){

    }
    public void removeUserFromHome(){

    }
    public void removeHome(Long id){

    }
}
