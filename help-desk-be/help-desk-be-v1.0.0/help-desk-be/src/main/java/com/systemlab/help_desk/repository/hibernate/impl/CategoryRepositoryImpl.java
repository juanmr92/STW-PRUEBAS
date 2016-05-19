package com.systemlab.help_desk.repository.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.systemlab.base.repository.hibernate.impl.BaseRepositoryImpl;
import com.systemlab.help_desk.entity.Category;
import com.systemlab.help_desk.repository.hibernate.CategoryRepository;

/**
* CategoryRepositoryImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Repository
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category> implements CategoryRepository{

}
