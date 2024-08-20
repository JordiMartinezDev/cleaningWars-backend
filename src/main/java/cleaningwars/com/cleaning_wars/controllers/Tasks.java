package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.models.Task;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController()

public class Tasks {


    @GetMapping("/tasks")
    public String getMethodName(Model model) {
        
        Task task = new Task("Clean kitchen", "kitchen", 7);
        model.addAttribute("task", task);
        return "Tasks";
    }
    @GetMapping("/tasks/newtask")
    public String getMethodName(@RequestParam String name, @RequestParam String icon, @RequestParam int score) {
        return "Created, good job";
    }

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
    
}
