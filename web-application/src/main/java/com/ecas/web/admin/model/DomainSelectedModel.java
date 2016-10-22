package com.ecas.web.admin.model;

import com.ecas.base.AbstractDomain;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

public class DomainSelectedModel<T extends AbstractDomain> extends ListDataModel<T> implements SelectableDataModel<T>,
		Serializable {
	private static final long serialVersionUID = 8299678116788904899L;

	public DomainSelectedModel() {
		super();
	}

	public DomainSelectedModel(List<T> list) {
		super(list);
	}

	@Override
	public Object getRowKey(T object) {
		return object.getId();
	}

	@Override
	public T getRowData(String rowKey) {
		Integer id = Integer.valueOf(rowKey);
		for (T obj : getData()) {
			if (obj.getId() == id) {
				return obj;
			}
		}
		return null;
	}

	public List<T> getData() {
		return (List<T>) getWrappedData();
	}
	
	public void addData(T obj){
		getData().add(obj);
	}
}
