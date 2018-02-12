package com.appspot.dmutti.calculator.dao;

import java.util.*;

import javax.persistence.*;

import com.appspot.dmutti.calculator.pojo.*;

public class CompartilhadoDAO {
    
    private EntityManager entityManager;
    
    public List<Compartilhado> find() {
        String sql = "select  c                                               " +
                     "from com.appspot.dmutti.calculator.pojo.Compartilhado c ";

        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }
    
    public List<Compartilhado> findByConta(Conta conta) {
        String sql = "select c                                                " +
                     "from com.appspot.dmutti.calculator.pojo.Compartilhado c " +
                     "where   c.idConta = :idConta                            ";

        Query query = entityManager.createQuery(sql);
        query.setParameter("idConta", conta.getId());

        return query.getResultList();
    }
    
    public void save(Compartilhado compartilhado) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(compartilhado);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void insert(List<Compartilhado> compartilhados, Conta conta) {
        for (Compartilhado compartilhado : compartilhados) {
            compartilhado.setIdConta(conta.getId());
            save(compartilhado);
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
