package com.crud.rest.dao;

import java.util.List;

import com.crud.rest.beans.Vehicle;

public interface VehicleDao {

	public void persistData(Object enObj);

	public void startTransaction();

	public void closeConnection();

	public void commitTransaction();

	public void rollbackTransaction();

	public Object readObject(Class<?> cls, int id);

	public void removeObject(Object obj);

	public void mergeObject(Object obj);

	public List<Vehicle> createSelectQuery(String query);

	public void createDeleteQuery(String query);

}
