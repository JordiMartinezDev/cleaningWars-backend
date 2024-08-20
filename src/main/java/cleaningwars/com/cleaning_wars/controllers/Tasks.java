package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Tasks {


    @GetMapping("/tasks")
    public String getMethodName(Model model) {
        return "Tasks";
    }
    
}
