package com.ecas.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class ActivateCodeTesting {
	static Logger log = Logger.getLogger(ActivateCodeTesting.class.getName());

	@Test
	public void testGenerateActivationCodeTest() {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		log.info(RandomStringUtils.random(8, AB));
	}

}
