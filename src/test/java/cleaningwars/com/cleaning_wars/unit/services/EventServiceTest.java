package cleaningwars.com.cleaning_wars.unit.services;

import cleaningwars.com.cleaning_wars.dto.CreateEventRequest;
import cleaningwars.com.cleaning_wars.entities.Event;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.exceptions.EmptyInput;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.factories.EventFactory;
import cleaningwars.com.cleaning_wars.repositories.EventRepository;
import cleaningwars.com.cleaning_wars.services.implementations.EventServiceImplementation;
import cleaningwars.com.cleaning_wars.services.interfaces.TaskService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TaskService taskService;

    @Mock
    private UserService userService;

    @InjectMocks
    private EventServiceImplementation eventService;

    private Task task;
    private User user;
    private Date date;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        task = new Task();
        user = new User();
        date = new Date();
    }

    @Test
    public void createEvent__Success() {

        CreateEventRequest request = new CreateEventRequest(task.getId(), user.getId(), date);
        request.setTaskId(1L);
        request.setUserId(1L);
        request.setDate(date);

        when(taskService.getTaskById(1L)).thenReturn(task);
        when(userService.getUserById(1L)).thenReturn(user);
        when(eventRepository.save(any(Event.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Event createdEvent = eventService.createEvent(request);

        assertNotNull(createdEvent);
        assertEquals(task, createdEvent.getTask());
        assertEquals(user, createdEvent.getUser());
        assertEquals(date, createdEvent.getDate());

        verify(eventRepository, times(1)).save(any(Event.class));

    }

    @Test
    public void createEvent__UserNotFound__ThrowsException() {

        CreateEventRequest request = new CreateEventRequest(task.getId(), user.getId(), date);
        request.setTaskId(1L);
        request.setUserId(1L);

        when(taskService.getTaskById(1L)).thenReturn(null);
        when(userService.getUserById(1L)).thenReturn(user);

        assertThrows(EmptyInput.class, () -> {
            eventService.createEvent(request);
        });

    }

    @Test
    public void getEventById() {
        Event event = new Event();
        event.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event foundEvent = eventService.getEventById(1L);

        assertNotNull(foundEvent);
        assertEquals(event.getId(), foundEvent.getId());
    }

    @Test
    public void getEventById__eventNotFound__throwsException() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> {
            eventService.getEventById(1L);
        });
    }

    @Test
    public void deleteEvent() {
        eventService.deleteEvent(1L);
        verify(eventRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateEvent() {
        Event existingEvent = new Event();
        existingEvent.setId(1L);
        existingEvent.setTask(task);
        existingEvent.setUser(user);
        existingEvent.setDate(date);

        Event updatedEvent = new Event();
        updatedEvent.setTask(new Task());
        updatedEvent.setUser(new User());
        updatedEvent.setDate(new Date());

        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(any(Event.class))).thenReturn(existingEvent);

        Event result = eventService.updateEvent(1L, updatedEvent);

        assertNotNull(result);
        assertEquals(existingEvent.getId(), result.getId());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

}