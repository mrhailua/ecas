package com.ecas.utils;

/**
 * Hold locale for request
 * 
 * @author LENOVO
 *
 */
public class LocaleHolder {

	static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	static ThreadLocal<String> paramLocal = new ThreadLocal<String>();

	public static String getLocale() {
		return threadLocal.get();
	}

	public static void setLocale(String org) {
		threadLocal.set(org);
	}

	public static String getParamLocale() {
		return paramLocal.get();
	}

	public static void setParamLocale(String org) {
		paramLocal.set(org);
	}
	
	public static void clearLocale() {
		threadLocal.remove();
		paramLocal.remove();
	}
}
