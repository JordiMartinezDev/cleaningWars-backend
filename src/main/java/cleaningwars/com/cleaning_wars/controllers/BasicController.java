package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BasicController {
    @GetMapping("/")
    public String getMethodName() {
        return "UserName";
    }
    
}
