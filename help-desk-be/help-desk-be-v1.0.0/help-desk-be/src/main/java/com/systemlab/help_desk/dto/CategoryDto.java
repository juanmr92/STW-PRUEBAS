package com.systemlab.help_desk.dto;

import com.systemlab.base.annotation.DtoField;
import com.systemlab.base.dto.BaseDto;
import com.systemlab.help_desk.enums.CategoryDeviceRequired;
import com.systemlab.help_desk.enums.CategoryType;

/**
* CategoryDto
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class CategoryDto extends BaseDto{
	
	@DtoField(isString=true,minLength=1,maxLength=30)
	private String name;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=CategoryType.class)
	private String type;
	
	@DtoField(isNull=true,isString=true,minLength=0,maxLength=1000000)
	private String description;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=CategoryDeviceRequired.class)
	private String device_required;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDevice_required() {
		return device_required;
	}

	public void setDevice_required(String device_required) {
		this.device_required = device_required;
	}

}
