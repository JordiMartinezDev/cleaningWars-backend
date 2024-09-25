package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    

    
    @PostMapping("/adduser")
    public ResponseEntity<String> addUserToHome(@RequestParam Long userId, @RequestParam Long homeId) {
        
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        
        Home home = homeService.getHomeById(homeId);
        if (home == null) {
            return new ResponseEntity<>("Home not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("User added to home successfully", HttpStatus.OK);
    }
}