package com.systemlab.help_desk.repository.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.systemlab.base.repository.hibernate.impl.BaseRepositoryImpl;
import com.systemlab.help_desk.entity.Location;
import com.systemlab.help_desk.repository.hibernate.LocationRepository;

/**
* LocationRepositoryImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Repository
public class LocationRepositoryImpl extends BaseRepositoryImpl<Location> implements LocationRepository{

}
