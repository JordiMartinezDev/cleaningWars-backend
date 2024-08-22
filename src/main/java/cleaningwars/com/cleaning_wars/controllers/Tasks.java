package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.model.Task;
import cleaningwars.com.cleaning_wars.repositories.TasksRepository;
import jakarta.validation.Valid;

import java.util.List;

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


    @GetMapping("/getTaskList")
    public List<Task> getMethodName() {
        return tasksRepository.getTaskList();
    }
    
    
}
