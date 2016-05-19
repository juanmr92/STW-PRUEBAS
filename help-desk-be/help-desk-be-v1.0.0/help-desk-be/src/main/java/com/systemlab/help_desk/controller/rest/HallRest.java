package com.systemlab.help_desk.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemlab.base.controller.rest.BaseRest;
import com.systemlab.base.gate.BaseGate;
import com.systemlab.help_desk.dto.HallDto;
import com.systemlab.help_desk.gate.HallGate;

/**
* HallRest
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Controller
@RequestMapping(value="/hall")
public class HallRest extends BaseRest<HallDto>{

	@Autowired HallGate hallGate;
	
	protected BaseGate<HallDto> getBaseGate() {
		return hallGate;
	}

}
