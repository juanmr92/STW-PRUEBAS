package com.systemlab.help_desk.repository.hibernate.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.systemlab.base.repository.hibernate.impl.BaseRepositoryImpl;
import com.systemlab.help_desk.entity.User;
import com.systemlab.help_desk.repository.hibernate.UserRepository;

/**
* UserRepositoryImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository{
	
	@Override
	@Transactional
	public Integer add(User entity) throws Exception {
		// TODO Auto-generated method stub
		try {
			return super.add(entity);
		} catch (Exception e) {
			return -1;
		}
		
	}

}
