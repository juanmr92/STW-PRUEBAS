package com.systemlab.help_desk.enums;


/**
* CategoryDeviceRequired
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum CategoryDeviceRequired {
	
	NOT_REQUIRED("0"), 
	REQUIRED("1");
	
	private String value;

	private CategoryDeviceRequired(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static CategoryDeviceRequired getByValue(String value) {
		if (value != null) {
			for (CategoryDeviceRequired categoryDeviceRequired : CategoryDeviceRequired.values()) {
				if (value.equalsIgnoreCase(categoryDeviceRequired.value)) {
					return categoryDeviceRequired;
				}
			}
		}
		return null;
	}
	

}
