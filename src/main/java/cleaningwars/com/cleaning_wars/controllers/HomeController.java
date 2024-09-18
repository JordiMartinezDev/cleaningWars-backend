package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.entity.Home;
import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.services.HomeService;
import cleaningwars.com.cleaning_wars.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;
    
    // Endpoint to create a new home
    @PostMapping("/newhome")
    public void postMethodName(@RequestBody Home home) {
        homeService.createHome(home);
    }

    // Endpoint to add an existing user to a home
    @PostMapping("/adduser")
    public ResponseEntity<String> addUserToHome(@RequestParam Long userId, @RequestParam Long homeId) {
        // Fetch the user by userId
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Fetch the home by homeId
        Home home = homeService.getHomeById(homeId);
        if (home == null) {
            return new ResponseEntity<>("Home not found", HttpStatus.NOT_FOUND);
        }

        // Associate the user with the home
        //user.setHome(home);  when user has a home reference
        //userService.updateUser(user); // Update the user in the database

        return new ResponseEntity<>("User added to home successfully", HttpStatus.OK);
    }
}