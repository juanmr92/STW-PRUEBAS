package com.systemlab.help_desk.gate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemlab.base.gate.impl.BaseGateImpl;
import com.systemlab.base.service.business.BaseService;
import com.systemlab.help_desk.dto.UserDto;
import com.systemlab.help_desk.entity.User;
import com.systemlab.help_desk.gate.UserGate;
import com.systemlab.help_desk.service.business.UserService;

/**
* UserGateImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Component
public class UserGateImpl extends BaseGateImpl<UserDto> implements UserGate{
	
	@Autowired private UserService userService;

	@SuppressWarnings("unchecked")
	protected BaseService<UserDto, User> getSuperService() {
		return userService;
	}

}
