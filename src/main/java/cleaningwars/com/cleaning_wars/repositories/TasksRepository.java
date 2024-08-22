package cleaningwars.com.cleaning_wars.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cleaningwars.com.cleaning_wars.model.Task;

@Component
public class TasksRepository {
    
    
    private List<Task> allTasks = new ArrayList<>();

    public List<Task> getTaskList(){
        return allTasks;
    }

}
