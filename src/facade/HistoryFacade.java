/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.History;
import static entity.History_.model;
import entity.Model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class HistoryFacade extends AbstractFacade<History> {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20ShoeShopDaniilMozgovPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }
      
    public List<History> findListSoldShoes() {
        try {
            return (List<History>) (History) em.createQuery("SELECT h FROM History h WHERE h.model = :model")
                .setParameter("model", model)
                .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
