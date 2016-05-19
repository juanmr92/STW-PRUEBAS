package com.systemlab.help_desk.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.service.business.impl.BaseServiceImpl;
import com.systemlab.help_desk.dto.UserDto;
import com.systemlab.help_desk.entity.User;
import com.systemlab.help_desk.repository.hibernate.UserRepository;
import com.systemlab.help_desk.service.business.UserService;

/**
* UserServiceImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto, User> implements UserService{

	@Autowired UserRepository userRepository;

	protected BaseRepository<User> getBaseRepository() {
		return userRepository;
	}

	protected void validateCreate(User user) throws Exception {
		
	}

	protected void validateUpdate(User user) throws Exception {
		
	}
	
	
	
	
}
