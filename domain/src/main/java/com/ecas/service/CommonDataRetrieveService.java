package com.ecas.service;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;

public interface CommonDataRetrieveService<T> {
	Class<?> getDomainClass();

	List<T> getAll();

	Criteria getCriteria();

	List<T> load(int first, int pageSize, HashMap<String, Object> params);

	Long loadCount(HashMap<String, Object> params);
}
