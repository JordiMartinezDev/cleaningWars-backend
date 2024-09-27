package cleaningwars.com.cleaning_wars.unit.services;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import cleaningwars.com.cleaning_wars.repositories.TaskRepository;
import cleaningwars.com.cleaning_wars.services.implementations.TasksServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private HomeRepository homeRepository;

    @InjectMocks
    private TasksServiceImplementation taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewTask() {
        Task task = new Task();
        Home home = new Home();

        when(homeRepository.findById(1L)).thenReturn(Optional.of(home));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.addNewTask(task, 1L);

        assertNotNull(result);
        assertEquals(home, result.getHome());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(task, result);
    }

    @Test
    void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setName("Old Task");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        Task updatedTask = new Task();
        updatedTask.setName("New Task");

        taskService.updateTask(1L, updatedTask);

        assertEquals("New Task", existingTask.getName());
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    void testDeleteTaskById() {
        taskService.deleteTaskById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}