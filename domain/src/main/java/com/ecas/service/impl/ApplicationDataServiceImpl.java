package com.ecas.service.impl;

import com.ecas.domain.Application;
import com.ecas.service.ApplicationDataService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CAS is a system that help us control authorization
 * Created by tupham on 10/29/16.
 */
@Service("applicationDataService")
@Transactional
public class ApplicationDataServiceImpl extends GenericDataServiceImpl<Application> implements ApplicationDataService {
    @Override
    public void addApplication(Application application) {
        this.save(application);
    }

    @Override
    public void enableApplication(Application application) {
        application.enable();
        this.save(application);
    }

    @Override
    public void disableApplication(Application application) {
        application.disable();
        this.save(application);
    }

    @Override
    public boolean isNameExisted(String name) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("name", name)).setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult() > 0;
    }

}
