package com.systemlab.help_desk.repository.hibernate;

import java.util.List;

import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.wapper.RequestList;
import com.systemlab.help_desk.entity.Request;

/**
* RequestRepository
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public interface RequestRepository extends BaseRepository<Request>{
	
	Integer getUserOperator(String provider) throws Exception;
	
	List<Request> getlistWhitNoDevice(RequestList requestList) throws Exception;
}
