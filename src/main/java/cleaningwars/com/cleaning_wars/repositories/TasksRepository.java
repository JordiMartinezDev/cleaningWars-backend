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
        new Task("1","Kitchen","kitchen",5),
        new Task("2","Bathroom","toilet",4),
        new Task("3","Sweep room","toilet",2),
        new Task("4","Cook lunch","toilet",3)

    );

    public List<Task> getTaskList(){
        return tasks;
    }
    public Task getTask(int index){
        return tasks.get(index);
    }
}
