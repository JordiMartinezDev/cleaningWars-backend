package cleaningwars.com.cleaning_wars.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;

    private String name;
    private String icon;
    private int points;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    @JsonIgnore
    private Home home;
}
