package com.ecas.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by tupham on 10/22/16.
 */
public class TestGeneratePassword {
    static Logger log = Logger.getLogger(TestGeneratePassword.class.getName());

    @Test
    public void generatePasswordTest() {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder(15);
        log.info(encode.encode("k$w6%WTF"));
    }
}
