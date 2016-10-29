package com.ecas.service.impl;

import com.ecas.base.AbstractDomain;
import com.ecas.service.GenericDataService;
import com.ecas.service.ServiceRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("serviceRegistry")
public class ServiceRegistryImpl implements ServiceRegistry, ApplicationContextAware, InitializingBean, Serializable {
    private static final long serialVersionUID = 8780965031510484214L;
    private transient ApplicationContext context;
    protected Map<Class<?>, GenericDataService> cachedServiceBeans = new ConcurrentHashMap<Class<?>, GenericDataService>();

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        final String[] beanNamesToLoad = context.getBeanNamesForType(GenericDataService.class);
        for (final String name : beanNamesToLoad) {
            final GenericDataService service = (GenericDataService) context.getBean(name);
            if (!cachedServiceBeans.values().contains(service)) {
                Class<?> domainClass = service.getDomainClass();
                if (domainClass != null) {
                    cachedServiceBeans.put(domainClass, service);
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
            result = (GenericDataService) context.getBean("genericDataService", new Object[]{clzz});
            cachedServiceBeans.put(clzz, result);
        }
        return result;
    }
}
