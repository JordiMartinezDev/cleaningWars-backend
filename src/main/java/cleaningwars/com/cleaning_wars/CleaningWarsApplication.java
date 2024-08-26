package cleaningwars.com.cleaning_wars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cleaningwars.com.cleaning_wars.entity.Task;
import cleaningwars.com.cleaning_wars.repositories.TaskRepository;

@SpringBootApplication
public class CleaningWarsApplication implements CommandLineRunner{

	@Autowired
	TaskRepository taskRepository;
	public static void main(String[] args) {
		SpringApplication.run(CleaningWarsApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception{
		Task[] preSetTasks = new Task[] {
			new Task(1L, "Kitchen", "Test home", "dishes", 4),
			new Task(2L, "Bathroom", "Test home", "toilet", 2),
			new Task(3L, "Sweep room", "Test home", "sweep", 2),
			new Task(4L, "Iron clothes", "Test home", "iron", 6)

		};

		for (Task task : preSetTasks) {
			taskRepository.save(task);
		}
		// for(int i=0; preSetTasks.length > i; i++ ){
		// 	taskRepository.save(preSetTasks[i]);
		// }
	}
}
