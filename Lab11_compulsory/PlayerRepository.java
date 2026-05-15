package org.example.repository;

import org.example.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PlayerRepository {
    private EntityManagerFactory emf;

    public PlayerRepository() {
        emf = Persistence.createEntityManagerFactory("QuizPU");
    }

    public void save(Player player) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
    }

    public Player findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Player p = em.find(Player.class, id);
        em.close();
        return p;
    }

    public void close() {
        emf.close();
    }
}