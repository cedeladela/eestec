package si.cedeladela.managers;

import si.cedeladela.entitete.StepathonUser;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.logging.Logger;

@ApplicationScoped
public class StepathonUserManager {

    private final Logger log = Logger.getLogger(StepathonUserManager.class.getName());

    @PersistenceContext(unitName = "stepathon-jpa")
    private EntityManager em;

    public StepathonUser getByUsername(String username){
        try {
            Query q = em.createQuery("SELECT u FROM stepathon_user u WHERE u.username = :username");
            q.setParameter("username", username);
            return (StepathonUser) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
