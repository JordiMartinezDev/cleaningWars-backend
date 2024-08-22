package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.model.Task;
import cleaningwars.com.cleaning_wars.repositories.TasksRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController()

public class Tasks {
    @Autowired
    TasksRepository tasksRepository;

    Task task = new Task("kitchen","baby",3);

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

    
    
    
    
}
