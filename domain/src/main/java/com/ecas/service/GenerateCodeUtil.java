package com.ecas.service;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Local rersource to generate unique string, wrapper RandomStringUtil
 * @author LENOVO
 *
 */
public class GenerateCodeUtil {
	private static final String SEEDING_CHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String getCode(Integer length){
		return RandomStringUtils.random(length, SEEDING_CHAR);
		
	}
}
