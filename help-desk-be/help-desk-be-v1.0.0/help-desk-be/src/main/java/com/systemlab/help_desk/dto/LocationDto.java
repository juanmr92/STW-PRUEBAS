package com.systemlab.help_desk.dto;

import com.systemlab.base.annotation.DtoField;
import com.systemlab.base.dto.BaseDto;

/**
* LocationDto
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class LocationDto extends BaseDto {
	
	@DtoField(isString=true,minLength=1,maxLength=30)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
