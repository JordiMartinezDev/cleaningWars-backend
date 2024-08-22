package cleaningwars.com.cleaning_wars;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import cleaningwars.com.cleaning_wars.model.Task;

import cleaningwars.com.cleaning_wars.repositories.TasksRepository;
import cleaningwars.com.cleaning_wars.services.TasksServiceImplementation;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TasksServiceTest {

    @Mock
    private TasksRepository tasksRepository;

    @InjectMocks
    private TasksServiceImplementation tasksService;

    @Test
    public void getTasksFromRepository() {
        when(tasksRepository.getTaskList()).thenReturn(Arrays.asList(new Task("1","kitchen","baby",3), new Task("2","32sdf","baby23",3), new Task("3","asf2","baby",3)));

        List<Task> result = tasksService.getTaskList();
        
        assertEquals("kitchen", result.get(0).getName());
        assertEquals("baby23", result.get(1).getIcon());

    }

    public void getTaskIndexTest(){
        when(tasksRepository.getTaskList()).thenReturn(Arrays.asList(new Task("1","kitchen","baby",3), new Task("2","32sdf","baby23",3), new Task("3","asf2","baby",3)));

        List<Task> result = tasksService.getTaskList();



    }
}
