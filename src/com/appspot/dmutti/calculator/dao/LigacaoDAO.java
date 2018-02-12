package com.appspot.dmutti.calculator.dao;

import java.util.*;

import javax.persistence.*;

import com.appspot.dmutti.calculator.pojo.*;

public class LigacaoDAO {
    
    private EntityManager entityManager;
    
    public List<Ligacao> find() {
        String sql = "select l                                          " +
                     "from com.appspot.dmutti.calculator.pojo.Ligacao l " +
                     "order by l.data asc                               ";

        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }
    
    public List<Ligacao> findByConta(Conta conta) {
        String sql = "select l                                          " +
                     "from com.appspot.dmutti.calculator.pojo.Ligacao l " +
                     "where   l.idConta = :idConta                      " +
                     "order by l.data asc                               ";

        Query query = entityManager.createQuery(sql);
        query.setParameter("idConta", conta.getId());

        return query.getResultList();
    }
    
    public List<String> findByAreaTelefone(String cidadeArea, String telefone) {
        String sql = "select l.dono                                     " +
                     "from com.appspot.dmutti.calculator.pojo.Ligacao l " +
                     "where   l.cidadeArea = :cidade                    " +
                     "and     l.telefone = :fone                        " +
                     "order by l.data desc                              ";
        
        Query query = entityManager.createQuery(sql);
        query.setParameter("cidade", cidadeArea);
        query.setParameter("fone", telefone);
         
        return query.getResultList();
    }
    
    public void save(Ligacao ligacao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ligacao);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    
    public Ligacao find(long id) {
        return entityManager.find(Ligacao.class, id);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
