package com.systemlab.help_desk.enums;


/**
* UserRole
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum UserRole {
	
	ADMIN("0"), 
	CALIMANT("1"),
	SERVIDESK_OPERATOR("2"),
	MICROTEC_OPERATOR("3"),
	SERVIDESK_ADMIN("4"),
	MICROTEC_ADMIN("5");
	
	private String value;

	private UserRole(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static UserRole getByValue(String value) {
		if (value != null) {
			for (UserRole userRole : UserRole.values()) {
				if (value.equalsIgnoreCase(userRole.value)) {
					return userRole;
				}
			}
		}
		return null;
	}
	

}
