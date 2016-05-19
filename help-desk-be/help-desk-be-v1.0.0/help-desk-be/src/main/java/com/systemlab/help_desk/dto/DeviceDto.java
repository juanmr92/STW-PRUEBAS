package com.systemlab.help_desk.dto;

import com.systemlab.base.annotation.DtoField;
import com.systemlab.base.annotation.DtoObject;
import com.systemlab.base.dto.BaseDto;

/**
* DeviceDto
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class DeviceDto extends BaseDto {
	
	@DtoField(isString=true,minLength=1,maxLength=10)
	private String code;
	
	@DtoField(isString=true,minLength=1,maxLength=30)
	private String name;
	
	@DtoField(isNull=true,isString=true,minLength=0,maxLength=1000000)
	private String description;
	
	@DtoObject
	private HallDto hall;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HallDto getHall() {
		return hall;
	}

	public void setHall(HallDto hall) {
		this.hall = hall;
	}

}
