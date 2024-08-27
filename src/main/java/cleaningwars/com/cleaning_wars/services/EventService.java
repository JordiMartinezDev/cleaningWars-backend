package cleaningwars.com.cleaning_wars.services;

import cleaningwars.com.cleaning_wars.entity.Event;
import java.util.List;

public interface EventService {

    Event addEvent(Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    void deleteEvent(Long id);
}
