package si.cedeladela.managers;

import si.cedeladela.entitete.Location;
import si.cedeladela.entitete.StepathonUser;
import si.cedeladela.utils.DistanceCalculator;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class StepathonUserManager {

    private final Logger log = Logger.getLogger(StepathonUserManager.class.getName());

    private final int LOCATION_SCORE = 2000;
    private final double LOCATION_MIN_DISTANCE_KM = 0.050;

    @PersistenceContext(unitName = "stepathon-jpa")
    private EntityManager em;

    @Inject
    private LocationManager locationManager;

    public StepathonUser getById(int id){
        try {
            Query q = em.createQuery("SELECT u FROM stepathon_user u WHERE u.id = :id");
            q.setParameter("id", id);
            return (StepathonUser) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public StepathonUser getByUsername(String username){
        try {
            Query q = em.createQuery("SELECT u FROM stepathon_user u WHERE u.username = :username");
            q.setParameter("username", username);
            StepathonUser stepathonUser = (StepathonUser) q.getSingleResult();
            stepathonUser.setScore(calculateScore(stepathonUser));
            return stepathonUser;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<StepathonUser> getAll(){
        Query q = em.createQuery("SELECT u FROM stepathon_user u");
        List<StepathonUser> users = (List<StepathonUser>) q.getResultList();

        if (users != null) {
            for (StepathonUser user : users) {
                user.setScore(calculateScore(user));
            }
        }
        return users;
    }

    @Transactional
    public StepathonUser create(StepathonUser user){

        List<Location> existingLocations = getById(user.getId()).getLocations();
        if (user.getLocations().size() > 0) {
            List<Location> locationsInProximity = locationProximity(user);
            existingLocations.addAll(locationsInProximity);
        }
        user.setLocations(existingLocations);
        return em.merge(user);
    }

    private List<Location> locationProximity(StepathonUser user) {
        List<Location> locations = locationManager.getAll();
        List<Location> toAdd = new ArrayList<>();
        if(locations != null) {
            if(user.getLocations() != null && user.getLocations().size() > 0) {
                Location userLocation = user.getLocations().get(0);
                for (Location location : locations) {
                    if (DistanceCalculator.distance(userLocation.getLatitude(), userLocation.getLongitude(), location.getLatitude(), location.getLongitude(), "M") < LOCATION_MIN_DISTANCE_KM) {
                        if(location.getActive()){
                            toAdd.add(location);
                        }
                    }
                }
            }
        }

        return toAdd;
    }

    public int calculateScore(StepathonUser user){
        return user.getSteps() + user.getLocations().size() * LOCATION_SCORE;
    }

}
