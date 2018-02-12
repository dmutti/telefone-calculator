package com.appspot.dmutti.calculator.dao;

import java.util.*;

import javax.persistence.*;

import com.appspot.dmutti.calculator.pojo.*;

public class ContaDAO {
    
    private EntityManager entityManager;
    
    public Conta getContaBy(int mes, int ano) {
        String sql = "select c                                        " +
                     "from com.appspot.dmutti.calculator.pojo.Conta c " +
                     "where   c.mes = :mes                            " +
                     "and   c.ano = :ano                              ";
        
        Query query = entityManager.createQuery(sql);
        query.setParameter("mes", mes);
        query.setParameter("ano", ano);
        
        List<Conta> contas = query.getResultList();
        if (!contas.isEmpty()) {
            return contas.get(0);
        }
        
        return null;
    }
    
    public Conta getContaBy(Conta conta) {
        return getContaBy(conta.getMes(), conta.getAno());
    }
    
    public List<Conta> find() {
        String sql = "select c                                        " +
                     "from com.appspot.dmutti.calculator.pojo.Conta c " +
                     "order by c.mes desc, c.ano desc                 ";
        
        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }
    
    public void save(Conta conta) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(conta);
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
