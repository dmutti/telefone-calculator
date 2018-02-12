package com.appspot.dmutti.calculator.dao;

import java.util.*;

import javax.persistence.*;

import com.appspot.dmutti.calculator.pojo.*;

public class DonoDAO {
    
    private EntityManager entityManager;

    public List<Dono> find() {
        String sql = "select d                                       " +
                     "from com.appspot.dmutti.calculator.pojo.Dono d " +
                     "order by d.doador asc, d.nomeExibicao asc      ";

        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }
    
    public List<Dono> findByPrimaryKey(String email) {
        String sql = "select d                                       " +
                     "from com.appspot.dmutti.calculator.pojo.Dono d " +
                     "where d.email = :email";

        Query query = entityManager.createQuery(sql);
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    public void save(Dono dono) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dono);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
