package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entity.Home;
import cleaningwars.com.cleaning_wars.entity.Task;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.TaskRepository;

@Service
public class TasksServiceImplementation implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired HomeRepository homeRepository;

    @Override
    public Task addNewTask(Task task, Long homeId){

        Home home = homeRepository.findById(homeId).get();
        task.setHome(home);
        return taskRepository.save(task);

    }
    @Override
    public List<Task> getallTasks(){
        return (List<Task>) taskRepository.findAll();
    }
    @Override
    public Task getTaskById(Long id){
        return taskRepository.findById(id).get();
    }

    @Override
    public void updateTask(Long id, Task updatedTask) {
        // Find the existing task by ID
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        // Replace the fields with the updatedTaskData
        existingTask.setName(updatedTask.getName());
        existingTask.setIcon(updatedTask.getIcon());
        existingTask.setScore(updatedTask.getScore());
        existingTask.setHome(updatedTask.getHome());

        // Save the updated task to the database
        taskRepository.save(existingTask);
    }
    @Override
    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }
    
    
}
