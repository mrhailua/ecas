package com.ecas.validate;

import java.io.Serializable;

/**
 * Hold validation result to transfering
 * 
 * @author LENOVO
 *
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -7890667890237430834L;

	private boolean result;
	private String code;

	public Result(boolean result, String code) {
		super();
		this.result = result;
		this.code = code;
	}

	public boolean isValid() {
		return result;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
