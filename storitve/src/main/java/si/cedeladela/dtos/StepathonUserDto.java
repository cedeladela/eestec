package si.cedeladela.dtos;

import java.util.List;

public class StepathonUserDto {

    private int id;
    private String username;
    private String password;
    private Integer score;
    private Integer steps;
    private List<LocationDto> locations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }
}
