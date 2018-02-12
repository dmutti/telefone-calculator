package com.appspot.dmutti.calculator.support;

import javax.persistence.*;

public class EMF {
    
    private EntityManagerFactory entityManagerFactory;
    
    public EntityManager entityManager() {  
        return entityManagerFactory.createEntityManager();  
    }
    
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}