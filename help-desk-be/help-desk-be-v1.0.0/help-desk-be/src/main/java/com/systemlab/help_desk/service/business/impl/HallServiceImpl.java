package com.systemlab.help_desk.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.service.business.impl.BaseServiceImpl;
import com.systemlab.help_desk.dto.HallDto;
import com.systemlab.help_desk.entity.Hall;
import com.systemlab.help_desk.repository.hibernate.HallRepository;
import com.systemlab.help_desk.service.business.HallService;

/**
* HallServiceImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Service
public class HallServiceImpl extends BaseServiceImpl<HallDto, Hall> implements HallService{

	@Autowired HallRepository hallRepository;
	
	protected BaseRepository<Hall> getBaseRepository() {
		return hallRepository;
	}

	protected void validateCreate(Hall arg0) throws Exception {
		
	}

	protected void validateUpdate(Hall arg0) throws Exception {
		
	}

}
