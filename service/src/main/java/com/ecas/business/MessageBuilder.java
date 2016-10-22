package com.ecas.business;

import java.util.Map;

public interface MessageBuilder {

	String getMessage(String key,final Map<String, String> context) throws Exception;
}
