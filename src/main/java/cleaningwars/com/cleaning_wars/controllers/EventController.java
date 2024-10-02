package cleaningwars.com.cleaning_wars.controllers;

import cleaningwars.com.cleaning_wars.dto.EventRequest;
import cleaningwars.com.cleaning_wars.entities.Event;

import cleaningwars.com.cleaning_wars.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    @Lazy
    private EventService eventService;

    @PostMapping("/new")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest request) {

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
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventRequest updatedEvent) {

        Event event = eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}