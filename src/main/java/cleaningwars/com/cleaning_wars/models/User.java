package cleaningwars.com.cleaning_wars.models;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;  // Store hashed passwords

    @ManyToOne
    private Home home;  // Each user belongs to one home
}