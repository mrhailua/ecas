package com.ecas.validate;

/**
 * For validate business, Validation working via this interface. Data provided
 * by constructor of implementation
 * 
 * @author LENOVO
 *
 */
public interface Rule<T> {
	Result validate(T obj);
}
