package cleaningwars.com.cleaning_wars.factories;

import cleaningwars.com.cleaning_wars.entities.Event;
import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.entities.User;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class EventFactory {

    public static Event createEvent(Home home, Task task, User user, Date date) {

        Event event = new Event();

        event.setHome(home);
        event.setDate(date);
        event.setTask(task);
        event.setUser(user);

        return event;

    }
}
