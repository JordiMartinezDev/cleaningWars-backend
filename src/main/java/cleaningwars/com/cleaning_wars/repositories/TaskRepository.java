package cleaningwars.com.cleaning_wars.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
import cleaningwars.com.cleaning_wars.entities.Task;

public interface TaskRepository extends CrudRepository<Task,Long>{
    
    List<Task> findByHomeId(Long homeId); // findBy{foreign reference, such as homeId} will automatically create the SQL query
}
