package cleaningwars.com.cleaning_wars.services.interfaces;

import java.util.List;

import cleaningwars.com.cleaning_wars.entities.Event;

public interface EventService {

    Event addEvent(Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    void deleteEvent(Long id);
}
