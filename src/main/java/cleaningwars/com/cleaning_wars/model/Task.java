package cleaningwars.com.cleaning_wars.model;


import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Task {

    // --- Constructor ---

    // public Task( String id, String name, String icon, int i) {
    //     this.id = id;
    //     this.name = name;
    //     this.icon = icon;
    //     this.score = i;
    // }

    public Task(){

    }
    // --- Variables ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Not sure if this is actually needed
    private Long id;
    private String name;
    private String home;
    private String icon;
    private int score;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHome() {
        return home;
    }
    public void setHome(String home) {
        this.home = home;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

}
