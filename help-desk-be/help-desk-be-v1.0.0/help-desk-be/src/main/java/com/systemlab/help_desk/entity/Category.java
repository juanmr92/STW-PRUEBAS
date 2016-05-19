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
* Category
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Entity
@DynamicUpdate
@Table(name="CATEGORY")
public class Category extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CATEGORY")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DEVICE_REQUIRED")
	private String device_required;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
