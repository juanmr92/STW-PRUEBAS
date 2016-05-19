package com.systemlab.help_desk.gate;

import org.springframework.web.multipart.MultipartFile;

import com.systemlab.base.gate.BaseGate;
import com.systemlab.base.wapper.ResponseBase;
import com.systemlab.help_desk.dto.RequestDto;

/**
* RequestGate
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

public interface RequestGate extends BaseGate<RequestDto>{
	
	ResponseBase add(RequestDto requestDto, String provider, MultipartFile file);

}
