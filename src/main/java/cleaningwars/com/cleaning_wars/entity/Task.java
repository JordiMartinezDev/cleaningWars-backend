package cleaningwars.com.cleaning_wars.entity;


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

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    private Home home;
}
