package cleaningwars.com.cleaning_wars.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.TaskRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.TaskService;

import org.springframework.core.io.Resource;

@Service
public class TasksServiceImplementation implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired HomeRepository homeRepository;
    @Value("classpath:predefined-tasks.json")
    private Resource tasksJsonFile;

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
        existingTask.setPoints(updatedTask.getPoints());
        existingTask.setHome(updatedTask.getHome());

        
        taskRepository.save(existingTask);
    }
    @Override
    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }
    
}
