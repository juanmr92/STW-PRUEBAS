package com.systemlab.help_desk.enums;


/**
* DocumentType
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public enum UserDocumentType {
	
	DNI("1"), 
	INMIGRATION_CARD("2");
	
	private String value;

	private UserDocumentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static UserDocumentType getByValue(String value) {
		if (value != null) {
			for (UserDocumentType userDocumentType : UserDocumentType.values()) {
				if (value.equalsIgnoreCase(userDocumentType.value)) {
					return userDocumentType;
				}
			}
		}
		return null;
	}
	

}
