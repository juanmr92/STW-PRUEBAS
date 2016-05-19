package com.systemlab.help_desk.exception;

import com.systemlab.base.exception.CustomException;

/**
* InvalidDtoException
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class NoOperatorsAvaible extends CustomException{

	private static final long serialVersionUID = 167029406629058721L;

	public static final String MESSAGE = "No operators avaible";
	
	public static final int STATUS = 304;
	
    public NoOperatorsAvaible() {
        super(MESSAGE);
    }
    
	public int getStatus() {
		return STATUS;
	}

}
