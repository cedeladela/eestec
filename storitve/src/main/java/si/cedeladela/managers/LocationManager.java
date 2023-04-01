package si.cedeladela.managers;

import si.cedeladela.entitete.Location;
import si.cedeladela.entitete.StepathonUser;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class LocationManager {


    @PersistenceContext(unitName = "stepathon-jpa")
    private EntityManager em;

    public List<Location> getAll(){
        try {
            Query q = em.createQuery("SELECT l FROM location l");
            return (List<Location>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
