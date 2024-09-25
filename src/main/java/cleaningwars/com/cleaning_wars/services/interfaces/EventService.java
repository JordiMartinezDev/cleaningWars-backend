package cleaningwars.com.cleaning_wars.services.interfaces;

import java.util.List;
import java.util.Date;
import cleaningwars.com.cleaning_wars.entities.Event;

public interface EventService {

    Event createEvent(Event event);
    Event getEventById(Long id);
    public List<Event> getEventsOnDate(Date date);
    List<Event> getAllEvents();
    Event updateEvent(Long id, Event updatedEvent);
    void deleteEvent(Long id);
}
