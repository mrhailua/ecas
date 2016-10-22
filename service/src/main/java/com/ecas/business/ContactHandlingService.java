package com.ecas.business;

import com.ecas.dto.ContactInput;

/**
 * Created by LENOVO on 5/28/2016.
 */
public interface ContactHandlingService {

    void process(ContactInput contactInput);
}
