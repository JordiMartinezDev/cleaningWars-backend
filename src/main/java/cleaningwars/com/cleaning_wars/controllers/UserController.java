package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    @Lazy
    UserService userService;

    @Autowired
    @Lazy
    HomeService homeService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {

        userService.createUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> postMethodName(@RequestBody User user) {

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user.getUsername()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/update")
    public ResponseEntity<User> getMethodName(@PathVariable Long id, @RequestParam User updatedUser) {

        userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

}
