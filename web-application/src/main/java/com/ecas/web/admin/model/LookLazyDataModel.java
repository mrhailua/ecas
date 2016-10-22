package com.ecas.web.admin.model;

import com.ecas.base.AbstractDomain;
import com.ecas.service.CommonDataRetrieveService;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookLazyDataModel<T extends AbstractDomain> extends LazyDataModel<T> {
	private static final long serialVersionUID = 6107686403894739128L;
	private CommonDataRetrieveService<T> retrieveDataService;
	private List<T> datasource;
	private HashMap<String, Object> queryParams;

	public LookLazyDataModel(CommonDataRetrieveService<T> retrieveDataService, HashMap<String, Object> queryParams) {
		this.retrieveDataService = retrieveDataService;
		this.queryParams = queryParams;
	}

	@Override
	public T getRowData(String rowKey) {
		for (T dataItem : datasource) {
			if (StringUtils.equals(rowKey, String.valueOf(dataItem.getId()))) {
				return dataItem;
			}
		}
		return null;
	}

	public Object getRowKey(T object) {
		if (object != null) {
			return object.getId();
		}
		return null;
	}

	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		this.setRowCount(retrieveDataService.loadCount(queryParams).intValue());
		return retrieveDataService.load(first, pageSize, queryParams);
		// TODO: Consider sort and filter
		// http://stackoverflow.com/questions/19482628/lazydatamodel-with-pagination-clicking-on-next-or-last-page-has-no-effect

	}
}
