package com.systemlab.help_desk.enums;


/**
* RequestExpired
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum RequestExpired {
	
	UNEXPIRED("0"), 
	EXPIRED("1");
	
	private String value;

	private RequestExpired(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static RequestExpired getByValue(String value) {
		if (value != null) {
			for (RequestExpired requestExpired : RequestExpired.values()) {
				if (value.equalsIgnoreCase(requestExpired.value)) {
					return requestExpired;
				}
			}
		}
		return null;
	}
	

}
