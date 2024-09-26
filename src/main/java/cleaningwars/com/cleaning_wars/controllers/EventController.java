package cleaningwars.com.cleaning_wars.controllers;

import cleaningwars.com.cleaning_wars.dto.CreateEventRequest;
import cleaningwars.com.cleaning_wars.entities.Event;

import cleaningwars.com.cleaning_wars.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/new")
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest request) {
        
        eventService.createEvent(request);
        return ResponseEntity.ok(eventService.createEvent(request));
    }

    @GetMapping("/{homeId}/getlist")
    public ResponseEntity<List<Event>> getAllEvents(@PathVariable Long homeId) {
        List<Event> events = eventService.getAllEvents(homeId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        Event event = eventService.updateEvent(id, updatedEvent);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}