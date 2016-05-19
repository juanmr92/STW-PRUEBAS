package com.systemlab.help_desk.gate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemlab.base.gate.impl.BaseGateImpl;
import com.systemlab.base.service.business.BaseService;
import com.systemlab.help_desk.dto.HallDto;
import com.systemlab.help_desk.entity.Hall;
import com.systemlab.help_desk.gate.HallGate;
import com.systemlab.help_desk.service.business.HallService;

/**
* HallGateImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Component
public class HallGateImpl extends BaseGateImpl<HallDto> implements HallGate{

	@Autowired HallService hallService;
	
	
	@SuppressWarnings("unchecked")
	protected BaseService<HallDto, Hall> getSuperService() {
		return hallService;
	}

}
