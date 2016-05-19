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
* Hall
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Entity
@DynamicUpdate
@Table(name="HALL")
public class Hall extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_HALL")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PROVIDER")
	private String provider;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LOCATION_ID")
	private Location location;

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

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
