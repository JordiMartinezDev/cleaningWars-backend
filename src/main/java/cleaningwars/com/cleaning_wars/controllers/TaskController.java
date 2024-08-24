package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.model.Task;
import cleaningwars.com.cleaning_wars.services.TaskService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController()
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;


    @GetMapping("/getall")
    public List<Task> getTaskList() {
        return taskService.getall(); // Can be done this way, no need for the status codes and so on... it looks to be handled automatically
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);

    }
    
    @PostMapping("/addnewtask")
    public ResponseEntity<Task /*HttpStatus */> postMethodName(@RequestBody @Valid Task task) {

        taskService.addNewTask(task);
        return new ResponseEntity<>(taskService.addNewTask(task),HttpStatus.CREATED);
        // return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public void putMethodName(@PathVariable String id, @RequestBody Task task) {
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
