package com.systemlab.help_desk.dto;

import com.systemlab.base.annotation.DtoField;
import com.systemlab.base.annotation.DtoObject;
import com.systemlab.base.dto.BaseDto;
import com.systemlab.base.util.date.DateUtil;
import com.systemlab.help_desk.enums.RequestExpired;
import com.systemlab.help_desk.enums.RequestState;

/**
* Category
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class RequestDto extends BaseDto{
	
	@DtoField(isString=true,minLength=1,maxLength=10)
	private String code;
	
	@DtoField(isString=true,minLength=0,maxLength=1000000)
	private String description;
	
	@DtoField(isNull=true,isString=true,minLength=0,maxLength=1000000)
	private String observation;
	
	@DtoField(isNull=true,isString=true,minLength=1,maxLength=500)
	private String file;
	
	@DtoField(isDate=true,dateFormat=DateUtil.HOUR)
	private String start_date;
	
	@DtoField(isNull=true,isDate=true,dateFormat=DateUtil.HOUR)
	private String end_date;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=RequestState.class)
	private String state;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=RequestExpired.class)
	private String expired;
	
	@DtoObject
	private UserDto user_req;
	
	@DtoObject(isNull=true)
	private UserDto user_ope;
	
	@DtoObject
	private CategoryDto category;
	
	@DtoObject(isNull=true)
	private DeviceDto device;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public UserDto getUser_req() {
		return user_req;
	}

	public void setUser_req(UserDto user_req) {
		this.user_req = user_req;
	}

	public UserDto getUser_ope() {
		return user_ope;
	}

	public void setUser_ope(UserDto user_ope) {
		this.user_ope = user_ope;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public DeviceDto getDevice() {
		return device;
	}

	public void setDevice(DeviceDto device) {
		this.device = device;
	}
	
}
