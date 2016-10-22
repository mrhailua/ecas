package com.ecas.validate;

import com.ecas.service.ServiceRegistry;


/**
 * Base information for Rule validator
 * 
 * @author LENOVO
 *
 */
public abstract class BaseRule {
	private ServiceRegistry serviceRegistry;

	public BaseRule(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	protected ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
}
