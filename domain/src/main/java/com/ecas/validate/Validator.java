package com.ecas.validate;

import java.io.Serializable;

public class Validator implements Serializable {

	private static final long serialVersionUID = 7216122972285230778L;
	private String type;
	private String beanName;

	public Validator(String type, String beanName) {
		super();
		this.type = type;
		this.beanName = beanName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 *            the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
}
