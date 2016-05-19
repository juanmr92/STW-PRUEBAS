package com.systemlab.help_desk.repository.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.systemlab.base.repository.hibernate.impl.BaseRepositoryImpl;
import com.systemlab.help_desk.entity.Device;
import com.systemlab.help_desk.repository.hibernate.DeviceRepository;

/**
* DeviceRepositoryImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Repository
public class DeviceRepositoryImpl extends BaseRepositoryImpl<Device> implements DeviceRepository{

}
