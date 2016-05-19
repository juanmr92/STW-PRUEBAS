package com.systemlab.help_desk.gate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemlab.base.gate.impl.BaseGateImpl;
import com.systemlab.base.service.business.BaseService;
import com.systemlab.help_desk.dto.DeviceDto;
import com.systemlab.help_desk.entity.Device;
import com.systemlab.help_desk.gate.DeviceGate;
import com.systemlab.help_desk.service.business.DeviceService;

/**
* DeviceGateImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Component
public class DeviceGateImpl extends BaseGateImpl<DeviceDto> implements DeviceGate{
	
	@Autowired DeviceService deviceService;

	
	@SuppressWarnings("unchecked")
	protected BaseService<DeviceDto, Device> getSuperService() {
		return deviceService;
	}
	
	

}
