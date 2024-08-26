package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.Utils.Constants;
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
    public List<Task> getall(){
        return (List<Task>) taskRepository.findAll();
    }
    @Override
    public Task getTaskById(Long id){
        return taskRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }
    
}
