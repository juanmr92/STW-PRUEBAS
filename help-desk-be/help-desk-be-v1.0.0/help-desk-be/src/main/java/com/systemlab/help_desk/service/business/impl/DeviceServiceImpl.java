package com.systemlab.help_desk.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.service.business.impl.BaseServiceImpl;
import com.systemlab.help_desk.dto.DeviceDto;
import com.systemlab.help_desk.entity.Device;
import com.systemlab.help_desk.repository.hibernate.DeviceRepository;
import com.systemlab.help_desk.service.business.DeviceService;

/**
* DeviceServiceImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Service
public class DeviceServiceImpl extends BaseServiceImpl<DeviceDto, Device> implements DeviceService{

	@Autowired DeviceRepository deviceRepository;
	
	protected BaseRepository<Device> getBaseRepository() {
		return deviceRepository;
	}

	protected void validateCreate(Device arg0) throws Exception {
		
	}

	protected void validateUpdate(Device arg0) throws Exception {
		
	}

}
