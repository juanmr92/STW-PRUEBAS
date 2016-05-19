package com.systemlab.help_desk.enums;


/**
* CategoryType
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum CategoryType {
	
	REQUIREMENT("1"), 
	INCIDENT("2");
	
	private String value;

	private CategoryType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static CategoryType getByValue(String value) {
		if (value != null) {
			for (CategoryType categoryType : CategoryType.values()) {
				if (value.equalsIgnoreCase(categoryType.value)) {
					return categoryType;
				}
			}
		}
		return null;
	}
	

}
