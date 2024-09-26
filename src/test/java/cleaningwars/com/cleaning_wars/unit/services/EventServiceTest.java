package cleaningwars.com.cleaning_wars.unit.services;
import cleaningwars.com.cleaning_wars.dto.CreateEventRequest;
import cleaningwars.com.cleaning_wars.entities.Event;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.repositories.EventRepository;
import cleaningwars.com.cleaning_wars.services.implementations.EventServiceImplementation;
import cleaningwars.com.cleaning_wars.services.interfaces.TaskService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest{

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TaskService taskService;

    @Mock
    private UserService userService;

    @InjectMocks
    private EventServiceImplementation eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent_success() {
        // Mocking data
        Task task = new Task();
        User user = new User();
        Date date = new Date();

        CreateEventRequest request = new CreateEventRequest();
        request.setTaskId(1L);
        request.setUserId(1L);
        request.setDate(date);

        when(taskService.getTaskById(1L)).thenReturn(task);
        when(userService.getUserById(1L)).thenReturn(user);
        when(eventRepository.save(any(Event.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Action
        Event createdEvent = eventService.createEvent(request);

        // Checks
        assertNotNull(createdEvent);
        assertEquals(task, createdEvent.getTask());
        assertEquals(user, createdEvent.getUser());
        assertEquals(date, createdEvent.getDate());

        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void createEvent_taskOrUserNotFound_throwsException() {
        // Mocking data
        CreateEventRequest request = new CreateEventRequest();
        request.setTaskId(1L);
        request.setUserId(1L);

        when(taskService.getTaskById(1L)).thenReturn(null);
        when(userService.getUserById(1L)).thenReturn(null);

        // Action and checks
        assertThrows(IllegalArgumentException.class, () -> {
            eventService.createEvent(request);
        });
    }

    @Test
    void getEventById_returnNull_IdNotFound() {
        // Mocking
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        // Action
        Event foundEvent = eventService.getEventById(1L);

        // checks
        assertNull(foundEvent);
    }
    @Test
    void testGetEventsOnDate() {
        
    }
    @Test
    void testGetEventById() {
        Event event = new Event();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(1L);

        assertNotNull(result);
        assertEquals(event, result);
    }
     @Test
    void testUpdateEvent() {
        Event event = new Event();
        event.setId(1L);

        when(eventRepository.existsById(1L)).thenReturn(true);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event updatedEvent = eventService.updateEvent(1L, event);

        assertNotNull(updatedEvent);
        assertEquals(1L, updatedEvent.getId());
    }
    @Test
    void testDeleteEvent() {
        eventService.deleteEvent(1L);
        verify(eventRepository, times(1)).deleteById(1L);
    }
}