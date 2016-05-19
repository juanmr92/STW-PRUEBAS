package com.systemlab.help_desk.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.service.business.impl.BaseServiceImpl;
import com.systemlab.help_desk.dto.LocationDto;
import com.systemlab.help_desk.entity.Location;
import com.systemlab.help_desk.repository.hibernate.LocationRepository;
import com.systemlab.help_desk.service.business.LocationService;

/**
* LocationServiceImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Service
public class LocationServiceImpl extends BaseServiceImpl<LocationDto, Location> implements LocationService {
	
	@Autowired LocationRepository locationRepository;
	
	protected BaseRepository<Location> getBaseRepository() {
		return locationRepository;
	}

	protected void validateCreate(Location arg0) throws Exception {
		
	}

	protected void validateUpdate(Location arg0) throws Exception {
		
	}

}
