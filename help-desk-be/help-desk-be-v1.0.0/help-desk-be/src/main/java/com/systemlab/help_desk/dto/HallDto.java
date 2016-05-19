package com.systemlab.help_desk.dto;

import com.systemlab.base.annotation.DtoField;
import com.systemlab.base.annotation.DtoObject;
import com.systemlab.base.dto.BaseDto;
import com.systemlab.help_desk.enums.HallProvider;

/**
* HallDto
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class HallDto extends BaseDto{
	
	@DtoField(isString=true,minLength=1,maxLength=30)
	private String name;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=HallProvider.class)
	private String provider;
	
	@DtoObject
	private LocationDto location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	
}
