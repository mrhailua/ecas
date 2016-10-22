package com.ecas.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecas.domain.Setting;
import com.ecas.service.ServiceRegistry;

public class AbstractBusinessService {
	@Autowired
	private ServiceRegistry serviceRegistry;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}
