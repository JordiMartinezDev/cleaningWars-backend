package cleaningwars.com.cleaning_wars.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name = "Home";

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private Set<User> users;

    @OneToMany(mappedBy = "newHome", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Invitation> invitations;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Event> events;
}
