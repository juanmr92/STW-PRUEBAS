package com.systemlab.help_desk.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.service.business.impl.BaseServiceImpl;
import com.systemlab.help_desk.dto.CategoryDto;
import com.systemlab.help_desk.entity.Category;
import com.systemlab.help_desk.repository.hibernate.CategoryRepository;
import com.systemlab.help_desk.service.business.CategoryService;

/**
* CategoryServiceImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDto, Category> implements CategoryService{

	@Autowired CategoryRepository categoryRepository;
	
	protected BaseRepository<Category> getBaseRepository() {
		return categoryRepository;
	}

	protected void validateCreate(Category arg0) throws Exception {
		
	}

	protected void validateUpdate(Category arg0) throws Exception {
		
	}

}
