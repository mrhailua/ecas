package com.ecas.batch;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Keep basic information for batch job
 * 
 * @author LENOVO
 *
 */
public class BaseJob {

	@Autowired
	protected com.ecas.service.ServiceRegistry serviceRegistry;
	@Autowired
	private SessionFactory sessionFactory;

	protected void init() {
		Session session = openSession(sessionFactory);
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("system", "root"));
	}

	protected void cleanUp() {
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.closeSession(sessionHolder.getSession());
	}

	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		try {
			Session session = SessionFactoryUtils.openSession(sessionFactory);
			session.setFlushMode(FlushMode.MANUAL);
			return session;
		} catch (HibernateException ex) {
			throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
		}
	}

}
