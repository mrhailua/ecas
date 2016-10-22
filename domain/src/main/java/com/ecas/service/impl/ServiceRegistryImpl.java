package com.ecas.service.impl;

import com.ecas.base.AbstractDomain;
import com.ecas.service.CommonDataRetrieveService;
import com.ecas.service.GenericDataService;
import com.ecas.service.ServiceRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceRegistryImpl implements ServiceRegistry, ApplicationContextAware, InitializingBean, Serializable {
    private static final long serialVersionUID = 8780965031510484214L;
    private transient ApplicationContext context;
    protected Map<Class<?>, GenericDataService> cachedServiceBeans = new ConcurrentHashMap<Class<?>, GenericDataService>();
    protected Map<Class<?>, CommonDataRetrieveService> cachedRetrieveServiceBeans = new ConcurrentHashMap<Class<?>, CommonDataRetrieveService>();
    private String fallbackPrototypeBeanName;
    private String defaultStatusBeanName;

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        final String[] beanNamesToLoad = context.getBeanNamesForType(GenericDataService.class);
        for (final String name : beanNamesToLoad) {
            if (!fallbackPrototypeBeanName.equals(name) && !defaultStatusBeanName.equals(name)) {
                final GenericDataService service = (GenericDataService) context.getBean(name);
                if (!cachedServiceBeans.values().contains(service)) {
                    Class<?> domainClass = service.getDomainClass();
                    if (domainClass != null) {
                        cachedServiceBeans.put(domainClass, service);
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public <T extends AbstractDomain> GenericDataService<T> getService(Class<T> clzz) {
        GenericDataService result = (GenericDataService) cachedServiceBeans.get(clzz);
        if (result == null) {
            result = (GenericDataService) context.getBean(fallbackPrototypeBeanName, new Object[]{clzz});
            cachedServiceBeans.put(clzz, result);
        }
        return result;
    }

    @Override
    public <T> CommonDataRetrieveService getRetrieveService(Class<T> clzz) {
        CommonDataRetrieveService result = (CommonDataRetrieveService) cachedRetrieveServiceBeans.get(clzz);
        if (result == null) {
            result = (CommonDataRetrieveService) context.getBean("CommonDataRetrieveService", new Object[]{clzz});
            cachedRetrieveServiceBeans.put(clzz, result);
        }
        return result;
    }

    /**
     * @return the fallbackPrototypeBeanName
     */
    public String getFallbackPrototypeBeanName() {
        return fallbackPrototypeBeanName;
    }

    /**
     * @param fallbackPrototypeBeanName the fallbackPrototypeBeanName to set
     */
    public void setFallbackPrototypeBeanName(String fallbackPrototypeBeanName) {
        this.fallbackPrototypeBeanName = fallbackPrototypeBeanName;
    }

    /**
     * @return the defaultStatusBeanName
     */
    public String getDefaultStatusBeanName() {
        return defaultStatusBeanName;
    }

    /**
     * @param defaultStatusBeanName the defaultStatusBeanName to set
     */
    public void setDefaultStatusBeanName(String defaultStatusBeanName) {
        this.defaultStatusBeanName = defaultStatusBeanName;
    }


}
