package com.crud.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.crud.rest.beans.Vehicle;

public class VehicleDaoImpl implements VehicleDao {

	@PersistenceContext
	private EntityManager em;

	private EntityManagerFactory emf;

	private EntityManager getDbConnector() {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
		return em;
	}

	public void persistData(Object enObj) {
		em.persist(enObj);
	}

	public void startTransaction() {
		if (null == em) {
			em = getDbConnector();
		} else {
			em = emf.createEntityManager();
		}
		em.getTransaction().begin();
	}

	public void closeConnection() {
		em.close();
	}

	public void commitTransaction() {
		em.getTransaction().commit();
	}

	public void rollbackTransaction() {
		EntityTransaction et = em.getTransaction();
		if (et.isActive())
			et.rollback();
	}

	public Object readObject(Class<?> cls, int id) {
		return em.find(cls, id);
	}

	public void removeObject(Object obj) {
		em.remove(obj);
	}

	public void mergeObject(Object obj) {
		em.merge(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> createSelectQuery(String query) {
		return em.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional //Test Transactional
	public void createDeleteQuery(String query) {
		em.createQuery(query).executeUpdate();
	}

}
