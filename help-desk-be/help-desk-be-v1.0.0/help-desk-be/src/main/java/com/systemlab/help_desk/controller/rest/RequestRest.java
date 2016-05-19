package com.systemlab.help_desk.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.systemlab.base.controller.rest.BaseRest;
import com.systemlab.base.gate.BaseGate;
import com.systemlab.base.wapper.ResponseBase;
import com.systemlab.help_desk.dto.RequestDto;
import com.systemlab.help_desk.gate.RequestGate;

/**
* RequestRest
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Controller
@RequestMapping(value="/request")
public class RequestRest extends BaseRest<RequestDto>{

	@Autowired RequestGate requestGate;
	
	protected BaseGate<RequestDto> getBaseGate() {
		return requestGate;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add/{provider}", method = RequestMethod.POST, produces = "application/json")
	public ResponseBase add(
			@RequestPart("requestDto") RequestDto requestDto,
			@RequestPart(value="file", required=false) MultipartFile  file,
			@PathVariable("provider") String provider){	
		
		return requestGate.add(requestDto,provider,file);
	}
}
