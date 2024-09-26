package cleaningwars.com.cleaning_wars.services.interfaces;

import java.util.List;
import java.util.Date;

import cleaningwars.com.cleaning_wars.dto.CreateEventRequest;
import cleaningwars.com.cleaning_wars.entities.Event;

public interface EventService {

    Event createEvent(CreateEventRequest request);;

    Event getEventById(Long id);

    List<Event> getEventsOnDate(Date date);

    List<Event> getAllEvents(Long homeId);

    Event updateEvent(Long id, Event updatedEvent);

    void deleteEvent(Long id);

}
