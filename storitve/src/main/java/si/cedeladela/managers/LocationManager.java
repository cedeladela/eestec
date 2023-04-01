package si.cedeladela.managers;

import si.cedeladela.entitete.Location;
import si.cedeladela.entitete.StepathonUser;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LocationManager {


    @PersistenceContext(unitName = "stepathon-jpa")
    private EntityManager em;

    public Location getById(int id){
        try {
            Query q = em.createQuery("SELECT l FROM location l WHERE l.id = :id");
            q.setParameter("id", id);
            Location location = (Location) q.getSingleResult();
            return location;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Location> getAll(){
        try {
            Query q = em.createQuery("SELECT l FROM location l");
            return (List<Location>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Location create(Location location){
        return em.merge(location);
    }

    @Transactional
    public Location activateLocation(int id){
        setAllLocationsInactive();
        Location locationToActivate = getById(id);
        if(locationToActivate != null){
            locationToActivate.setActive(true);
            return em.merge(locationToActivate);
        }
        return null;
    }

    @Transactional
    private void setAllLocationsInactive(){
        List<Location> locations = getAll();
        for (Location location : locations) {
            location.setActive(false);
            em.merge(location);
        }
    }

    public Location getChallenge() {
        try {
            Query q = em.createQuery("SELECT l FROM location l WHERE l.active = :active");
            q.setParameter("active", true);
            Location location = (Location) q.getSingleResult();
            return location;
        } catch (NoResultException e) {
            return null;
        }
    }
}
