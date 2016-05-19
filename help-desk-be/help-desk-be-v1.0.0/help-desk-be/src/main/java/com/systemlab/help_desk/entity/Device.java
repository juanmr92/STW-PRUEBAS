package com.systemlab.help_desk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.systemlab.base.entity.BaseEntity;

/**
* Device
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Entity
@DynamicUpdate
@Table(name="DEVICE")
public class Device extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DEVICE")
	private Integer id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "HALL_ID")
	private Hall hall;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}


}
