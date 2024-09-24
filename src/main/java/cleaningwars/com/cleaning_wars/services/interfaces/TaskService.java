package cleaningwars.com.cleaning_wars.services.interfaces;

import java.util.List;

import cleaningwars.com.cleaning_wars.entities.Task;

public interface TaskService {

    Task addNewTask(Task task, Long homeId);
    List<Task> getallTasks();
    Task getTaskById(Long id);
    void updateTask(Long id, Task updatedTask) ;
    void deleteTaskById(Long id);

}
