package com.systemlab.help_desk.gate.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.systemlab.base.gate.impl.BaseGateImpl;
import com.systemlab.base.service.business.BaseService;
import com.systemlab.base.util.bean.BeanUtil;
import com.systemlab.base.util.date.DateUtil;
import com.systemlab.base.wapper.ResponseBase;
import com.systemlab.help_desk.dto.RequestDto;
import com.systemlab.help_desk.dto.UserDto;
import com.systemlab.help_desk.entity.Request;
import com.systemlab.help_desk.enums.RequestExpired;
import com.systemlab.help_desk.enums.RequestState;
import com.systemlab.help_desk.gate.RequestGate;
import com.systemlab.help_desk.service.business.RequestService;

/**
* RequestGateImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Component
public class RequestGateImpl extends BaseGateImpl<RequestDto> implements RequestGate{
	
	@Autowired RequestService requestService;

	@SuppressWarnings("unchecked")
	protected BaseService<RequestDto, Request> getSuperService() {
		return requestService;
	}
	
	public ResponseBase add(RequestDto requestDto, String provider, MultipartFile file) {
		ResponseBase responseBase = null;
		try {
			//Only for purpose of validation
			requestDto.setCode("0");
			UserDto userDto = new UserDto();
			userDto.setId(0);
			requestDto.setUser_ope(userDto);
			requestDto.setUser_req(userDto);
			//
			//Setting defaults values
			requestDto.setStart_date(DateUtil.parseDateToString(new Date(), DateUtil.HOUR));
			requestDto.setState(RequestState.ASSIGNED.getValue());
			requestDto.setExpired(RequestExpired.UNEXPIRED.getValue());
			//
			BeanUtil.validateCreateDto(requestDto);
			requestService.add(requestDto,provider,file);
			responseBase = getResponseBase();
		} catch (Exception e) {
			e.printStackTrace();
			responseBase = getResponseBase(e);
		}
		return responseBase;
	}

}
