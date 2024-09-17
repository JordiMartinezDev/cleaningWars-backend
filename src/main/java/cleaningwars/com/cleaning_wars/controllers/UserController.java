package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cleaningwars.com.cleaning_wars.entity.Home;
import cleaningwars.com.cleaning_wars.entity.User;
import cleaningwars.com.cleaning_wars.services.HomeService;
import cleaningwars.com.cleaning_wars.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final HomeService homeService;

    public UserController(UserService userService, HomeService homeService) {
        this.userService = userService;
        this.homeService = homeService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam Long homeId) {
        Home home = homeService.getHomeById(homeId);
        if (home == null) {
            return ResponseEntity.badRequest().body(null);
        }
        //User user = userService.createUser(username, password, home);
        //return ResponseEntity.ok(user);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
