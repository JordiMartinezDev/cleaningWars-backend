package cleaningwars.com.cleaning_wars.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Event extends Task {

    @Temporal(TemporalType.DATE)
    private Date eventDate; 
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    // Not sure if the constructor is needed... Needs testing
    
    public Event(Long id, String name, String icon, int points, Home home, Date eventDate, User user) {
        super(id, name, icon, points, home);
        this.eventDate = eventDate;
        this.user = user;
    }
}
