package com.systemlab.help_desk.dto;

import com.systemlab.base.annotation.DtoField;
import com.systemlab.base.dto.BaseDto;
import com.systemlab.help_desk.enums.UserDocumentType;
import com.systemlab.help_desk.enums.UserRole;
import com.systemlab.help_desk.enums.UserState;

/**
* UserDto
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public class UserDto extends BaseDto {
	
	@DtoField(isString=true,minLength=1,maxLength=30)
	private String account;
	
	@DtoField(isString=true,minLength=1,maxLength=30)
	private String password;
	
	@DtoField(isString=true,minLength=1,maxLength=100)
	private String paternal_name;
	
	@DtoField(isString=true,minLength=1,maxLength=100)
	private String maternal_name;
	
	@DtoField(isString=true,minLength=1,maxLength=100)
	private String name;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=UserDocumentType.class)
	private String document_type;
	
	@DtoField(isString=true,minLength=1,maxLength=20)
	private String document_number;
	
	@DtoField(isString=true,minLength=1,maxLength=100)
	private String email;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=UserRole.class)
	private String role;
	
	@DtoField(isString=true,maxLength=1,hasDefaultValues=true,
			  defaultValues=UserState.class)
	private String state;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaternal_name() {
		return paternal_name;
	}

	public void setPaternal_name(String paternal_name) {
		this.paternal_name = paternal_name;
	}

	public String getMaternal_name() {
		return maternal_name;
	}

	public void setMaternal_name(String maternal_name) {
		this.maternal_name = maternal_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
