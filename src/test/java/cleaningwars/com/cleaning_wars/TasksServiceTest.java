package cleaningwars.com.cleaning_wars;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import cleaningwars.com.cleaning_wars.model.Task;

import cleaningwars.com.cleaning_wars.repositories.TasksRepository;
import cleaningwars.com.cleaning_wars.services.TasksService;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TasksServiceTest {

    @Mock
    private TasksRepository tasksRepository;

    @InjectMocks
    private TasksService tasksService;

    @Test
    public void getTasksFromRepository() {
        when(tasksRepository.getTaskList()).thenReturn(Arrays.asList(new Task("kitchen","baby",3), new Task("32sdf","baby",3), new Task("asf2","baby",3)));

        List<Task> result = tasksService.getTaskList();
        
    }
}
