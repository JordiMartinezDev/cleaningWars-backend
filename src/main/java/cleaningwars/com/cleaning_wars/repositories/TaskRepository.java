package cleaningwars.com.cleaning_wars.repositories;

import org.springframework.data.repository.CrudRepository;

import cleaningwars.com.cleaning_wars.entities.Task;

public interface TaskRepository extends CrudRepository<Task,Long>{
    
    Task findByHomeId(Long id); // findBy{foreign reference, such as homeId} will automatically create the SQL query
}
