package cleaningwars.com.cleaning_wars.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.TaskRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.TaskService;

import org.springframework.core.io.Resource;

@Service
@Lazy
public class TasksServiceImplementation implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    HomeRepository homeRepository;

    @Value("classpath:predefined-tasks.json")
    private Resource tasksJsonFile;

    @Override
    public List<Task> getHomeTasks(Long homeId) {

        homeRepository.findById(homeId)
                .orElseThrow(() -> new EntityNotFound(homeId, Home.class));

        return taskRepository.findByHomeId(homeId);

    }

    @Override
    public Task addNewTask(Task task, Long homeId) {

        Home home = homeRepository.findById(homeId).get();
        task.setHome(home);
        return taskRepository.save(task);

    }

    @Override
    public Task getTaskById(Long id) {

        return taskRepository.findById(id).get();

    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(id, Task.class));

        existingTask.setName(updatedTask.getName());
        existingTask.setIcon(updatedTask.getIcon());
        existingTask.setPoints(updatedTask.getPoints());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(id, Task.class));

        taskRepository.delete(task);

    }

}
