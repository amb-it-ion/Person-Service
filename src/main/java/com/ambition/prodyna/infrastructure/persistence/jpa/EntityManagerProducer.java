package com.ambition.prodyna.infrastructure.persistence.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer
{
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Produces
    @Default
    @RequestScoped
    public EntityManager create()
    {
	return this.emf.createEntityManager();
    }

    public void dispose(@Disposes @Default final EntityManager entityManager)
    {
	if (entityManager.isOpen())
	{
	    entityManager.close();
	}
    }
}
