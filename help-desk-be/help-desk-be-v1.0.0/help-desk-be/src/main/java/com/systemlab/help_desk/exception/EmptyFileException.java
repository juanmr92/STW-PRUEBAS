package com.systemlab.help_desk.exception;

import com.systemlab.base.exception.CustomException;

/**
* EmptyFileException
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class EmptyFileException extends CustomException{

	private static final long serialVersionUID = 167029406629058721L;

	public static final String MESSAGE = "Empty file";
	
	public static final int STATUS = 306;
	
    public EmptyFileException() {
        super(MESSAGE);
    }
    
	public int getStatus() {
		return STATUS;
	}

}
