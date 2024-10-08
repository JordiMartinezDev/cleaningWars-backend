package cleaningwars.com.cleaning_wars.controllers;

import org.springframework.web.bind.annotation.RestController;

import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.services.interfaces.TaskService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController()
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("{homeId}/getlist")
    public ResponseEntity<List<Task>> getTaskList(@PathVariable Long homeId) {

        return new ResponseEntity<>(taskService.getHomeTasks(homeId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);

    }

    @PostMapping("/addnewtask")
    public ResponseEntity<Task> postMethodName(@RequestBody @Valid Task task, @RequestParam Long homeId) {

        return new ResponseEntity<>(taskService.addNewTask(task, homeId), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {

        return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {

        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
