package com.ecas.web.admin.bean;

import com.ecas.domain.Application;
import com.ecas.service.ApplicationDataService;
import com.ecas.service.ServiceRegistry;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.facelets.FaceletContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ManagedBean(name = "applicationBean")
public class ApplicationBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = -6255646714469511339L;
    private Application application;
    private String searchValue;
    private List<Application> applicationsList = Collections.EMPTY_LIST;
    private List<Application> currentApplications = Collections.EMPTY_LIST;

    @ManagedProperty(value = "#{applicationDataService}")
    private ApplicationDataService applicationDataService;

    @ManagedProperty(value = "#{serviceRegistry}")
    private ServiceRegistry serviceRegistry;

    @PostConstruct
    public void init() {
        application = new Application();
        this.loadApplications();
    }

    public void saveNewApplication() {
        try {
            this.applicationDataService.addApplication(application);
            this.loadApplications();
            addInfoMessage("com.cas.application.form.save.successfully", application.getName());
            hideDialog("addApplicationDialog");
        } catch (Exception ex) {
            log.error("Save application fail", ex);
        }
    }

    public void handleKeyEvent(AjaxBehaviorEvent actionEvent) {
        currentApplications = new ArrayList<Application>();
        CollectionUtils.select(applicationsList, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                Application selectApplication = (Application)o;
                return StringUtils.isBlank(searchValue)|| selectApplication.getName().contains(searchValue);
            }
        }, currentApplications);
    }

    public void requestAddApplication(){
        application.setName(searchValue);
    }

    private void loadApplications() {
        applicationsList = applicationDataService.getAll();
        currentApplications = applicationsList;
        searchValue = StringUtils.EMPTY;
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

    public List<Application> getCurrentApplications() {
        return currentApplications;
    }

    public void setCurrentApplications(List<Application> currentApplications) {
        this.currentApplications = currentApplications;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
