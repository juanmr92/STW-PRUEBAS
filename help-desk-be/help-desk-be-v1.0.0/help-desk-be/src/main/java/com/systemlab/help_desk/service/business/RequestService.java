package com.systemlab.help_desk.service.business;

import org.springframework.web.multipart.MultipartFile;

import com.systemlab.base.service.business.BaseService;
import com.systemlab.help_desk.dto.RequestDto;
import com.systemlab.help_desk.entity.Request;

/**
* RequestService
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public interface RequestService extends BaseService<RequestDto, Request>{
	
	Integer add(RequestDto requestDto, String provider, MultipartFile file) throws Exception;

}
