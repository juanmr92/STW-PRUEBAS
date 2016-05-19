package com.systemlab.help_desk.repository.hibernate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.systemlab.base.exception.EmptyListException;
import com.systemlab.base.repository.hibernate.impl.BaseRepositoryImpl;
import com.systemlab.base.util.bean.BeanUtil;
import com.systemlab.base.wapper.RequestList;
import com.systemlab.base.wapper.Restriction;
import com.systemlab.help_desk.entity.Request;
import com.systemlab.help_desk.entity.User;
import com.systemlab.help_desk.enums.HallProvider;
import com.systemlab.help_desk.enums.UserRole;
import com.systemlab.help_desk.exception.InvalidHallProvider;
import com.systemlab.help_desk.exception.NoOperatorsAvaible;
import com.systemlab.help_desk.repository.hibernate.RequestRepository;

/**
* RequestRepositoryImpl
*
* @author  Josue Barrios
* @version 1.0.0
* @since   1.0.0
*/

@Repository
public class RequestRepositoryImpl extends BaseRepositoryImpl<Request> implements RequestRepository{
	
//	private static org.apache.log4j.Logger log = Logger.getLogger(RequestRepositoryImpl.class);
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Integer getUserOperator(String provider) throws Exception {
		Criteria criteria = getSession().createCriteria(User.class,"user");
		criteria.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id"),"id"));
		if(provider.equals(HallProvider.SERVIDESK.getValue())){
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.eq("role", UserRole.SERVIDESK_OPERATOR.getValue())));
		}else if(provider.equals(HallProvider.MICROTEC.getValue())){
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.eq("role", UserRole.MICROTEC_OPERATOR.getValue())));
		} else{
			throw new InvalidHallProvider();
		}
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> userMapList = (List<Map<String, Object>>) criteria.list();
		List<Integer> userIdList = new ArrayList<Integer>();
		
		for(Map<String,Object> userMap:userMapList){
			for (Entry<String, Object> userEntry: userMap.entrySet()) {
				if(userEntry.getKey().equalsIgnoreCase("id")){
					userIdList.add((Integer)userEntry.getValue());
				}
			}
		}
		if(userIdList==null || userIdList.size()==0){
			throw new NoOperatorsAvaible();
		}else{
			criteria = getSession().createCriteria(Request.class,"request");
			criteria.createAlias("request.user_ope", "user_ope");
			criteria.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("user_ope.id"),"id")
					.add(Projections.rowCount(),"userRoleId"));//use field userRoleId for counting
			criteria.add(Restrictions.in("user_ope.id", userIdList));
			
			criteria.addOrder(Order.asc("userRoleId"));
			criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			userMapList = (List<Map<String, Object>>) criteria.list();
			List<User> userList = new ArrayList<User>();
			User user = null;
			
			
			for(Map<String,Object> userMap:userMapList){
				for (Entry<String, Object> userEntry: userMap.entrySet()) {
					if(userEntry.getKey().equalsIgnoreCase("id")){
						user =  new User();
						user.setId((Integer)userEntry.getValue());
						userList.add(user);
					}
				}
			}
			
			boolean haveRequest = false;
			for(Integer userId:userIdList){
				for(User returnUser:userList){
					if(returnUser.getId()==userId){
						haveRequest = true;
						break;
					}
				}
				if(!haveRequest){
					return userId;
				}else{
					haveRequest = false;
				}
			}
			
			//Used when userList is empty
			if(!haveRequest && (userList ==null || userList.size()==0)){
				return userIdList.get(0);
			}
			
			return userList.get(0).getId();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Request> getlistWhitNoDevice(RequestList requestList) throws Exception {

		Restriction deleteHallProviderRestr = null;
		String hallProvider = null;
		
		for(Restriction restriction : requestList.getRestrictions() ){
			if(restriction.getPropertyName().equals("hall.provider")){
				hallProvider = (String)restriction.getValue();
				deleteHallProviderRestr = restriction;
			}
		}
		
		if(deleteHallProviderRestr!=null){
			requestList.getRestrictions().remove(deleteHallProviderRestr);
		}
		
		if(hallProvider==null || (hallProvider!=null && hallProvider.equals("1"))){
			Criteria criteria = getSession().createCriteria(Request.class,"request");
			criteria.createAlias("request.category", "category");
			criteria.createAlias("request.user_req", "user_req");
			criteria.createAlias("request.user_ope", "user_ope");
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("id"),"id")
					.add(Projections.property("active"),"active")
					.add(Projections.property("code"),"code")
					.add(Projections.property("description"),"description")
					.add(Projections.property("observation"),"observation")
					.add(Projections.property("file"),"file")
					.add(Projections.property("end_date"),"end_date")
					.add(Projections.property("start_date"),"start_date")
					.add(Projections.property("state"),"state")
					.add(Projections.property("category.name"),"category.name")
					.add(Projections.property("category.type"),"category.type")
					.add(Projections.property("user_req.name"),"user_req.name")
					.add(Projections.property("user_req.paternal_name"),"user_req.paternal_name")
					.add(Projections.property("user_req.maternal_name"),"user_req.maternal_name")
					.add(Projections.property("user_ope.name"),"user_ope.name")
					.add(Projections.property("user_ope.paternal_name"),"user_ope.paternal_name")
					.add(Projections.property("user_ope.maternal_name"),"user_ope.maternal_name"));
			if(requestList.getRestrictions()!=null){
				setRestriction(criteria, requestList.getRestrictions());
			}
			
			criteria.add(Restrictions.isNull("device"));
			criteria.addOrder(Order.desc("start_date"));
			criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			return BeanUtil.mapListToEntityList(criteria.list(), Request.class);
		}else{
			throw new EmptyListException();
		}
		
	}

}
