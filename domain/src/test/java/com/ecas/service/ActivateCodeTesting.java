package com.ecas.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class ActivateCodeTesting {
	static Logger log = Logger.getLogger(ActivateCodeTesting.class.getName());

	@Test
	public void configureDbTest() {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		log.info(RandomStringUtils.random(20, AB));
	}

}
