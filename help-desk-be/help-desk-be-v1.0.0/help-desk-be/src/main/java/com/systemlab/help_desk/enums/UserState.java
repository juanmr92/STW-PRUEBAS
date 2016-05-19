package com.systemlab.help_desk.enums;


/**
* UserState
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum UserState {
	
	DISABLED("0"), 
	ENABLED("1");
	
	private String value;

	private UserState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static UserState getByValue(String value) {
		if (value != null) {
			for (UserState userState : UserState.values()) {
				if (value.equalsIgnoreCase(userState.value)) {
					return userState;
				}
			}
		}
		return null;
	}
	

}
