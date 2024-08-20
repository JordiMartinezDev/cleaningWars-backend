package cleaningwars.com.cleaning_wars.models;

public class Task {

    public Task(String name, String icon, Double score) {
        this.name = name;
        this.icon = icon;
        this.score = score;
    }
    private Long id;
    private String name;
    private String creator;
    private String icon;
    private Double score;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

}
