package cleaningwars.com.cleaning_wars.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cleaningwars.com.cleaning_wars.dto.EventRequest;
import cleaningwars.com.cleaning_wars.entities.Event;
import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.exceptions.EmptyInput;
import cleaningwars.com.cleaning_wars.exceptions.EntityNotFound;
import cleaningwars.com.cleaning_wars.factories.EventFactory;
import cleaningwars.com.cleaning_wars.repositories.EventRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.EventService;
import cleaningwars.com.cleaning_wars.services.interfaces.HomeService;
import cleaningwars.com.cleaning_wars.services.interfaces.TaskService;
import cleaningwars.com.cleaning_wars.services.interfaces.UserService;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;
    @Autowired
    HomeService homeService;

    @Override
    public Event createEvent(EventRequest request) {

        Task task = taskService.getTaskById(request.getTaskId());
        User user = userService.getUserById(request.getUserId());
        Home home = homeService.getHomeById(user.getHome().getId()); // Users always belong to a single home
        Date date = request.getDate();

        if (task == null || user == null || date == null || home == null) {
            throw new EmptyInput("There are empty fields ( home, task, user, date )");
        }

        Event event = EventFactory.createEvent(home, task, user, date);
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) {

        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(id, Event.class));

    }

    @Override
    public List<Event> getEventsOnDate(Date date) {
        // TODO: Implement this method based on your business logic
        throw new UnsupportedOperationException("Unimplemented method 'getEventsOnDate'");

    }

    @Override
    public List<Event> getAllEvents(Long homeId) {
        return eventRepository.findByHomeId(homeId);

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);

    }

    @Override
    public Event updateEvent(Long id, EventRequest updatedEvent) {
        Event dbEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(id, Event.class));

        dbEvent.setTask(taskService.getTaskById(updatedEvent.getTaskId()));
        dbEvent.setUser(userService.getUserById(updatedEvent.getUserId()));
        dbEvent.setDate(updatedEvent.getDate());

        return eventRepository.save(dbEvent);

    }

}
