package com.ecas.web.admin.dto;

import java.io.Serializable;

public class PermissionObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String key;
	private String label;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public PermissionObject(String key, String label) {
		super();
		this.key = key;
		this.label = label;
	}

}
