package com.systemlab.help_desk.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemlab.base.controller.rest.BaseRest;
import com.systemlab.base.gate.BaseGate;
import com.systemlab.help_desk.dto.DeviceDto;
import com.systemlab.help_desk.gate.DeviceGate;

/**
* DeviceRest
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Controller
@RequestMapping(value="/device")
public class DeviceRest extends BaseRest<DeviceDto>{

	@Autowired DeviceGate deviceGate;
	
	protected BaseGate<DeviceDto> getBaseGate() {
		return deviceGate;
	}

}
