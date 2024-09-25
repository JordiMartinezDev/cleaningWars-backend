package cleaningwars.com.cleaning_wars.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CreateEventRequest {
    private Long taskId; // ID of the task
    private Long userId; // ID of the user
    private Date date; // Date of the event
}