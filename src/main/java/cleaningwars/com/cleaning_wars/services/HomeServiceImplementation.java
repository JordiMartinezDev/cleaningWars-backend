package cleaningwars.com.cleaning_wars.services;

import java.util.List;

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
    public Home getHomeById(Long id){
        return null;
    }

    public List<Home> getAllHomes(){
        return null;
    }
    public void editHomeName(Home home, Long id){

    }
    public void addUserToHome(){

    }
    public void removeUserFromHome(){

    }
    public void deleteHome(Long id){

    }

    
}
