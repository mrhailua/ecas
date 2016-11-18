package com.ecas.service;

import com.ecas.domain.Application;

import java.util.List;

/**
 * CAS is a system that help us control authorization
 * Created by tupham on 10/29/16.
 */
public interface ApplicationDataService extends GenericDataService<Application> {
    void addApplication(Application application);

    void enableApplication(Application application);

    void disableApplication(Application application);

    boolean isNameExisted(String name);

}
