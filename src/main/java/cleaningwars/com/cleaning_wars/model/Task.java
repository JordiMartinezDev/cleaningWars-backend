package cleaningwars.com.cleaning_wars.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Task {

    // --- Constructor ---

    public Task( String id, String name, String icon, int i) {
        this.id = 1;
        this.name = name;
        this.icon = icon;
        this.score = i;
    }

    // --- Variables ---

    private String id;
    @NotBlank
    private String name;
    private String creator;
    @NotBlank
    private String icon;
    @Min(0)
    private int score;

    // --- Getters & Setters ---

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
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
