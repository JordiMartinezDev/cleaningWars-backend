package cleaningwars.com.cleaning_wars.factories;

import java.util.Set;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cleaningwars.com.cleaning_wars.entities.Home;
import cleaningwars.com.cleaning_wars.entities.Task;
import cleaningwars.com.cleaning_wars.entities.User;
import cleaningwars.com.cleaning_wars.repositories.HomeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeFactory {

    private HomeRepository homeRepository;

    @Value("classpath:predefined-tasks.json")
    private Resource tasksJsonFile;

    
    public Home createHomeForUser(User user) {
        
        Home newHome = createDefaultTemplate();
        newHome.setUsers(Set.of(user));  
        return homeRepository.save(newHome);
    }

    
    private Home createDefaultTemplate() {

        Home home = new Home();
        home.setName("Default Home");

        List<Task> tasks = loadPredefinedTasks();
        tasks.forEach(task -> task.setHome(home)); 
        home.setTasks(new HashSet<>(tasks));

        return home;
    }

    private List<Task> loadPredefinedTasks() {

        ObjectMapper mapper = new ObjectMapper();
        
        try {
          
            InputStream inputStream = tasksJsonFile.getInputStream();
            
            TypeReference<List<Task>> taskListType = new TypeReference<List<Task>>() {};
            return mapper.readValue(inputStream, taskListType);
        } catch (IOException e) {
           
            throw new RuntimeException("Failed to load predefined tasks", e);
        }
    }
}
