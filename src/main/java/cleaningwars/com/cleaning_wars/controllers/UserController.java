package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.services.HomeService;
import cleaningwars.com.cleaning_wars.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, HomeService homeService) {
        this.userService = userService;
    }
    
    @PostMapping("/usersignup") 
    public User registerUser(@RequestBody User newUser) {
        
         User user = new User();
         user.setUserName(newUser.getUserName());
         userService.createUser(user);
         return user;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
