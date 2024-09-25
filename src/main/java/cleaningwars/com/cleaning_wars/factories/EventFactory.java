package cleaningwars.com.cleaning_wars.factories;

import cleaningwars.com.cleaning_wars.entities.Event;
import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.User;

import java.util.Date;

public class EventFactory {
    
    public static Event createEvent(Long id, String name, String icon, int points, Home home, User user) {
        Date eventDate = new Date(); 
        return new Event(id, name, icon, points, home, eventDate, user);
    }
}
