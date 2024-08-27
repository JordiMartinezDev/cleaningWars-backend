package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import cleaningwars.com.cleaning_wars.entity.Task;

public interface TaskService {

    
    public Task addNewTask(Task task, Long homeId);
    public List<Task> getallTasks();
    public Task getTaskById(Long id);
    public void updateTask(Long id, Task updatedTask) ;
    public void deleteTaskById(Long id);

}
