package com.systemlab.help_desk.gate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.systemlab.base.gate.impl.BaseGateImpl;
import com.systemlab.base.service.business.BaseService;
import com.systemlab.help_desk.dto.CategoryDto;
import com.systemlab.help_desk.entity.Category;
import com.systemlab.help_desk.gate.CategoryGate;
import com.systemlab.help_desk.service.business.CategoryService;

/**
* CategoryGateImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Component
public class CategoryGateImpl extends BaseGateImpl<CategoryDto> implements CategoryGate{

	@Autowired CategoryService categoryService;
	
	@SuppressWarnings("unchecked")
	protected  BaseService<CategoryDto, Category> getSuperService() {
		return categoryService;
	}

}
