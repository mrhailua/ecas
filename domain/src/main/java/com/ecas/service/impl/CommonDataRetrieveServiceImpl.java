package com.ecas.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecas.service.CommonDataRetrieveService;

@Service("CommonDataRetrieveService")
@Transactional
@Scope("prototype")
public class CommonDataRetrieveServiceImpl<T> implements CommonDataRetrieveService<T>, Serializable {

	private static final long serialVersionUID = 6374820376047533536L;
	private Class<T> klazz;
	@Autowired
	private SessionFactory sessionFactory;

	public CommonDataRetrieveServiceImpl() {
		try {
			this.klazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
			        .getActualTypeArguments()[0];
		} catch (final Throwable e) {
			// ignore issues (e.g. when the subclass is not a parametrized type)
			// in that case, one needs to set the persistencClass otherwise.
		}
	}

	public CommonDataRetrieveServiceImpl(Class<T> clzz) {
		klazz = clzz;
	}

	@Override
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(klazz).list();
	}

	public Criteria getCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(klazz);
	}

	@Override
	public List<T> load(int first, int pageSize, HashMap<String, Object> params) {
		return sessionFactory.getCurrentSession().createCriteria(klazz).setFirstResult(first).setFetchSize(pageSize)
		        .list();
	}

	@Override
	public Long loadCount(HashMap<String, Object> params) {
		return (Long) sessionFactory.getCurrentSession().createCriteria(klazz).setProjection(Projections.rowCount())
		        .uniqueResult();
	}

	/**
	 * @return the klazz
	 */
	protected Class<T> getKlazz() {
		return klazz;
	}

	/**
	 * @param klazz
	 *            the klazz to set
	 */
	public void setKlazz(Class<T> klazz) {
		this.klazz = klazz;
	}

	@Override
	public Class<?> getDomainClass() {
		return klazz;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
