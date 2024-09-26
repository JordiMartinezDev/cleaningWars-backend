package cleaningwars.com.cleaning_wars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class CreateEventRequest {

    private Long taskId; 
    private Long userId; 
    private Date date; 

}