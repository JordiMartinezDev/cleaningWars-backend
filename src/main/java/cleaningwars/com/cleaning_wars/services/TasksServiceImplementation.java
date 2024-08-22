package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cleaningwars.com.cleaning_wars.Utils.Constants;
import cleaningwars.com.cleaning_wars.model.Task;
import cleaningwars.com.cleaning_wars.repositories.TasksRepository;

@Component
public class TasksServiceImplementation implements TaskService{

    @Autowired
    private TasksRepository tasksRepository;

    public List<Task> getTaskList() {
        return tasksRepository.getTaskList();
    }

    public Task getTask(int index) {
        return tasksRepository.getTask(index);
    }
    public String getTaskIndex(String id){
        List<Task> tasks = tasksRepository.getTaskList();
        for(int i = 0; i< tasks.size(); i++){
            if(tasks.get(i).getId().equals(id)) return id;
        }

        return Constants.NOT_FOUND;
    }


}
