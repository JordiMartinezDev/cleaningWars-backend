package cleaningwars.com.cleaning_wars.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import cleaningwars.com.cleaning_wars.model.Task;

@Component
public class TasksRepository {
    
    // Populating the data as we don't have DB connection yet
    private List<Task> tasks = Arrays.asList(
        new Task("Kitchen","kitchen",5),
        new Task("Bathroom","toilet",4),
        new Task("Sweep room","toilet",2),
        new Task("Cook lunch","toilet",3)

    );

    public List<Task> getTaskList(){
        return tasks;
    }
    public Task getTask(int index){
        return tasks.get(index);
    }
}
