package com.systemlab.help_desk.gate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemlab.base.gate.impl.BaseGateImpl;
import com.systemlab.base.service.business.BaseService;
import com.systemlab.help_desk.dto.LocationDto;
import com.systemlab.help_desk.entity.Location;
import com.systemlab.help_desk.gate.LocationGate;
import com.systemlab.help_desk.service.business.LocationService;

/**
* LocationGateImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Component
public class LocationGateImpl extends BaseGateImpl<LocationDto> implements LocationGate{
	
	@Autowired LocationService locationService ;
	
	@SuppressWarnings("unchecked")
	protected BaseService<LocationDto, Location> getSuperService() {
		return locationService;
	}

}
