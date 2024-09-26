package cleaningwars.com.cleaning_wars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cleaningwars.com.cleaning_wars.repositories.TaskRepository;

@SpringBootApplication
public class CleaningWarsApplication {

	@Autowired
	TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(CleaningWarsApplication.class, args);
	}

}
