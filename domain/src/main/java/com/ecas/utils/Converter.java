package com.ecas.utils;

/**
 * Converter interface
 * 
 * @author LENOVO
 *
 * @param <T>
 * @param <K>
 */
public interface Converter<T, K> {
	T convert(K k);
}
