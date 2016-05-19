package com.systemlab.help_desk.entity;

import java.util.Date;

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
* Request
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Entity
@DynamicUpdate
@Table(name="REQUEST")
public class Request extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_REQUEST")
	private Integer id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="OBSERVATION")
	private String observation;
	
	@Column(name="FILE")
	private String file;
	
	@Column(name="START_DATE")
	private Date start_date;
	
	@Column(name="END_DATE")
	private Date end_date;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="EXPIRED")
	private String expired;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_REQ_ID")
	private User user_req;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_OPE_ID")
	private User user_ope;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "DEVICE_ID")
	private Device device;

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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
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

	public User getUser_req() {
		return user_req;
	}

	public void setUser_req(User user_req) {
		this.user_req = user_req;
	}

	public User getUser_ope() {
		return user_ope;
	}

	public void setUser_ope(User user_ope) {
		this.user_ope = user_ope;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
