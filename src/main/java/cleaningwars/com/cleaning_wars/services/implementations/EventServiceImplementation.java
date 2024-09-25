package cleaningwars.com.cleaning_wars.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleaningwars.com.cleaning_wars.entities.Event;
import cleaningwars.com.cleaning_wars.repositories.EventRepository;
import cleaningwars.com.cleaning_wars.services.interfaces.EventService;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
    @Override
    public List<Event> getEventsOnDate(Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEventsOnDate'");
    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {
        // TODO Auto-generated method stub

        if (eventRepository.existsById(id)) {
            updatedEvent.setId(id);
            return eventRepository.save(updatedEvent);
        }
        return null;
    }

    
}
