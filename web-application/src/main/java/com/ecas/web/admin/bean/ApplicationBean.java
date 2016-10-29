package com.ecas.web.admin.bean;

import com.ecas.domain.Application;
import com.ecas.service.ApplicationDataService;
import com.ecas.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ManagedBean(name = "applicationBean")
public class ApplicationBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = -6255646714469511339L;
    private Application application;

    @ManagedProperty(value = "#{applicationDataService}")
    private ApplicationDataService applicationDataService;

    @ManagedProperty(value = "#{serviceRegistry}")
    private ServiceRegistry serviceRegistry;

    @PostConstruct
    public void init() {
        application = new Application();
    }

    public void saveNewApplication() {
        this.applicationDataService.addApplication(application);
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ApplicationDataService getApplicationDataService() {
        return applicationDataService;
    }

    public void setApplicationDataService(ApplicationDataService applicationDataService) {
        this.applicationDataService = applicationDataService;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    @Override
    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
}
