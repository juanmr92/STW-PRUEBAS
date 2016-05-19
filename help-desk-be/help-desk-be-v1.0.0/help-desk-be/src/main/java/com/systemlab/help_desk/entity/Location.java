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
* Location
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Entity
@DynamicUpdate
@Table(name="LOCATION")
public class Location extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_LOCATION")
	private Integer id;
	
	@Column(name="NAME")
	private String name;

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

}
