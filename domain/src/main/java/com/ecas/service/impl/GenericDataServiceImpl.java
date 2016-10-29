package com.ecas.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecas.base.AbstractDomain;
import com.ecas.service.GenericDataService;

@Service("genericDataService")
@Scope(value = "prototype")
@Transactional
public class GenericDataServiceImpl<T extends AbstractDomain> extends CommonDataRetrieveServiceImpl<T> implements
        GenericDataService<T>, Serializable {

    private static final long serialVersionUID = 6374820376047533536L;

    public GenericDataServiceImpl() {
        try {
            this.setKlazz((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);
        } catch (final Throwable e) {
            // ignore issues (e.g. when the subclass is not a parametrized type)
            // in that case, one needs to set the persistencClass otherwise.
        }
    }

    public GenericDataServiceImpl(Class<T> clzz) {
        super(clzz);
    }

    @Override
    public T get(Integer id) {
        return (T) getSessionFactory().getCurrentSession().get(getDomainClass(), id);
    }

    @Override
    public T save(T obj) {
        getSessionFactory().getCurrentSession().saveOrUpdate(obj);
        return obj;
    }

    @Override
    public List<T> save(List<T> objs) {
        for (T obj : objs) {
            this.save(obj);
        }
        return objs;
    }

    @Override
    public List<T> getAll() {
        return getSessionFactory().getCurrentSession().createCriteria(getDomainClass()).list();
    }

    @Override
    public void delete(T obj) {
        getSessionFactory().getCurrentSession().delete(obj);
    }

    @Override
    public void delete(Integer id) {
        T obj = (T) getSessionFactory().getCurrentSession().get(getDomainClass(), id);
        delete(obj);
    }

    public Criteria getCriteria() {
        return getSessionFactory().getCurrentSession().createCriteria(getDomainClass());
    }

    @Override
    public T refesh(T obj) {
        if (!obj.isNew()) {
            getSessionFactory().getCurrentSession().refresh(obj);
        }
        return obj;
    }
}
