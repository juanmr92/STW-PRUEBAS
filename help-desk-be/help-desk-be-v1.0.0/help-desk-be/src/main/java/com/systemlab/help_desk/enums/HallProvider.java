package com.systemlab.help_desk.enums;


/**
* UserState
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum HallProvider {
	
	SERVIDESK("1"), 
	MICROTEC("2");
	
	private String value;

	private HallProvider(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static HallProvider getByValue(String value) {
		if (value != null) {
			for (HallProvider hallProvider : HallProvider.values()) {
				if (value.equalsIgnoreCase(hallProvider.value)) {
					return hallProvider;
				}
			}
		}
		return null;
	}
	

}
