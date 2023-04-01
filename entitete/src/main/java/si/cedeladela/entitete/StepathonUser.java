package si.cedeladela.entitete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "stepathon_user")
public class StepathonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private Integer score;

    @Column(name = "steps")
    private Integer steps;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "stepathon_user_location",
            joinColumns = @JoinColumn(name = "stepathon_user_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Location> locations = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}