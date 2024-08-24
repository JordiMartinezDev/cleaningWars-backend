package cleaningwars.com.cleaning_wars.model;


import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;

    private String name;
    private String home;
    private String icon;
    private int score;

}
