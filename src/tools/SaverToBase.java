/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Buyer;
import entity.History;
import entity.Model;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class SaverToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTV20ShoeShopDaniilMozgovPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    @Override
    public void saveModels(List<Model> models) {
        tx.begin();
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getId() == null) {
                em.persist(models.get(i));
            }
        }
        tx.commit();
        }

    @Override
    public List<Model> loadModels() {
        List<Model> models = null;
        try {
            return em.createQuery("SELECT m FROM models m").getResultList();
        } catch (Exception e) {
            models = new ArrayList<>();
        }
        return models;
    }

    @Override
    public void saveBuyers(List<Buyer> buyers) {
        tx.begin();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i).getId() == null) {
                em.persist(buyers.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Buyer> loadBuyers() {
        List<Buyer> buyers = null;
        try {
            return em.createQuery("SELECT b FROM buyers b").getResultList();
        } catch (Exception e) {
            buyers = new ArrayList<>();
        }
        return buyers;
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getId() == null) {
                em.persist(histories.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories = null;
        try {
            return em.createQuery("SELECT h FROM histories h").getResultList();
        } catch (Exception e) {
            histories = new ArrayList<>();
        }
        return histories;
    }
}
