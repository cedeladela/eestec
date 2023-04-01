package si.cedeladela.entitete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(mappedBy = "locations", fetch = FetchType.EAGER)
    private List<StepathonUser> stepathonUsers = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<StepathonUser> getStepathonUsers() {
        return stepathonUsers;
    }

    public void setStepathonUsers(List<StepathonUser> stepathonUsers) {
        this.stepathonUsers = stepathonUsers;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}