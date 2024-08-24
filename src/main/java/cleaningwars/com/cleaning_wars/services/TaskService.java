package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import cleaningwars.com.cleaning_wars.model.Task;

public interface TaskService {

    
    public Task addNewTask(Task task);
    // public List<Task> getall();
    public Task getTaskById(Long id);
    public void deleteById(Long id);

}
