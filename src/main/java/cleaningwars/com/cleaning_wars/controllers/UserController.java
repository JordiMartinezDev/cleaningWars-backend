package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.services.HomeService;
import cleaningwars.com.cleaning_wars.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HomeService homeService;
    
    @PostMapping("/register") 
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
         userService.createUser(newUser);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
