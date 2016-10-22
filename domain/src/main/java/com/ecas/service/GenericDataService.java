package com.ecas.service;

import java.util.List;

import com.ecas.base.AbstractDomain;

public interface GenericDataService<T extends AbstractDomain> extends CommonDataRetrieveService<T> {

    T get(Integer id);

    T save(T obj);

    List<T> save(List<T> obj);

    void delete(T obj);

    T refesh(T obj);

    void delete(Integer id);
}
