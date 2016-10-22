package com.ecas.service;

import com.ecas.base.AbstractDomain;

public interface ServiceRegistry {

	<T extends AbstractDomain> GenericDataService<T> getService(Class<T> clzz);

	<T> CommonDataRetrieveService getRetrieveService(Class<T> clzz);
}
