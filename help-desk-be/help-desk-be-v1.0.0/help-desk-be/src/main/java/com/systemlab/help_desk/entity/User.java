package com.systemlab.help_desk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.systemlab.base.entity.BaseEntity;

/**
* User
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Entity
@DynamicUpdate
@Table(name="USER")
public class User extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USER")
	private Integer id;
	
	@Column(name="ACCOUNT")
	private String account;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="PATERNAL_NAME")
	private String paternal_name;
	
	@Column(name="MATERNAL_NAME")
	private String maternal_name;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DOCUMENT_TYPE")
	private String document_type;
	
	@Column(name="DOCUMENT_NUMBER")
	private String document_number;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="STATE")
	private String state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
