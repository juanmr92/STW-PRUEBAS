package com.systemlab.help_desk.enums;


/**
* UserState
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum RequestState {
	
	ASSIGNED("1"), 
	IN_PROCESS("2"),
	WAITING("3"),
	RESOLVED("4"),
	NOT_RESOLVED("5");
	
	private String value;

	private RequestState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static RequestState getByValue(String value) {
		if (value != null) {
			for (RequestState requestState : RequestState.values()) {
				if (value.equalsIgnoreCase(requestState.value)) {
					return requestState;
				}
			}
		}
		return null;
	}
	

}
