package cleaningwars.com.cleaning_wars.repositories;

import org.springframework.data.repository.CrudRepository;

import cleaningwars.com.cleaning_wars.entity.Task;

public interface TaskRepository extends CrudRepository<Task,Long>{
    
}
