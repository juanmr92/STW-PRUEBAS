package com.systemlab.help_desk.service.quartz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.base.exception.EmptyListException;
import com.systemlab.base.repository.hibernate.impl.BaseRepositoryImpl;
import com.systemlab.base.wapper.RequestList;
import com.systemlab.base.wapper.Restriction;
import com.systemlab.help_desk.entity.Request;
import com.systemlab.help_desk.enums.RequestExpired;
import com.systemlab.help_desk.enums.RequestState;
import com.systemlab.help_desk.repository.hibernate.RequestRepository;
import com.systemlab.help_desk.service.email.EmailService;
import com.systemlab.help_desk.service.quartz.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {
	
	private static final long MILLSECS_PER_HOUR	= 60 * 60 * 1000;
	
	@Autowired RequestRepository requestRepository;
	
	@Autowired EmailService emailService;

	public void requestExpired() throws Exception {
		try {
			RequestList requestRequestList = new RequestList();
			
			Map<String, String> requestAliases = new HashMap<String, String>();
			requestAliases.put("request.user_ope", "user_ope");
			requestRequestList.setAliases(requestAliases);
			
			Map<String, String> requestProjections = new HashMap<String, String>();
			requestProjections.put("id", "id");
			requestProjections.put("code", "code");
			requestProjections.put("description", "description");
			requestProjections.put("start_date", "start_date");
			requestProjections.put("update_date", "update_date");
			requestProjections.put("userRoleId", "userRoleId");
			requestProjections.put("user_ope.id", "user_ope.id");
			requestProjections.put("user_ope.paternal_name", "user_ope.paternal_name");
			requestProjections.put("user_ope.maternal_name", "user_ope.maternal_name");
			requestProjections.put("user_ope.name", "user_ope.name");
			requestProjections.put("user_ope.email", "user_ope.email");
			requestRequestList.setProjections(requestProjections);
			
			List<Restriction> requestRestrictionList = new ArrayList<Restriction>();
			Restriction requestRestriction = new Restriction(
					BaseRepositoryImpl.CONJUNTION, BaseRepositoryImpl.NOT_EQUALS, BaseRepositoryImpl.STRING_TYPE, 
					"expired", null, RequestExpired.EXPIRED.getValue());
			requestRestrictionList.add(requestRestriction);
			requestRestriction = new Restriction(
					BaseRepositoryImpl.CONJUNTION, BaseRepositoryImpl.NOT_EQUALS, BaseRepositoryImpl.STRING_TYPE, 
					"state", null, RequestState.RESOLVED.getValue());
			requestRestrictionList.add(requestRestriction);
			requestRestriction = new Restriction(
					BaseRepositoryImpl.CONJUNTION, BaseRepositoryImpl.NOT_EQUALS, BaseRepositoryImpl.STRING_TYPE, 
					"state", null, RequestState.NOT_RESOLVED.getValue());
			requestRestrictionList.add(requestRestriction);
			requestRequestList.setRestrictions(requestRestrictionList);

			List<Request> requestList = requestRepository.list(requestRequestList);
			
			List<Request> persistRequestList = new ArrayList<Request>();
			Request persistRequest = null;
			for(Request request:requestList){
				if(Math.abs(new Date().getTime() - request.getStart_date().getTime()) / MILLSECS_PER_HOUR > 48){
					persistRequest = new Request();
					persistRequest.setId(request.getId());
					persistRequest.setUpdate_date(request.getUpdate_date());
					persistRequest.setUserRoleId(request.getUserRoleId());
					persistRequest.setExpired(RequestExpired.EXPIRED.getValue());
					persistRequestList.add(persistRequest);
					emailService.sendRequestExpiredAlert(
							request.getUser_ope().getEmail(), 
							request.getCode(),
							request.getUser_ope().getName()+" "+request.getUser_ope().getPaternal_name()+" "+request.getUser_ope().getName(),
							request.getDescription());
				}
			}
			
			requestRepository.editList(persistRequestList);

		} catch (Exception e) {
			if(!(e instanceof EmptyListException)){
				throw e;
			}
		}			
	}

}
