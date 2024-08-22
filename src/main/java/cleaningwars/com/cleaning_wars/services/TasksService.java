package cleaningwars.com.cleaning_wars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cleaningwars.com.cleaning_wars.model.Task;
import cleaningwars.com.cleaning_wars.repositories.TasksRepository;

@Component
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<Task> getTaskList() {
        return tasksRepository.getTaskList();
    }
}
