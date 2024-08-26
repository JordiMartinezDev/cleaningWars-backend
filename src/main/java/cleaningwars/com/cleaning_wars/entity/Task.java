package cleaningwars.com.cleaning_wars.entity;


import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;


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
    private String home;
    private String icon;
    private int score;

}
