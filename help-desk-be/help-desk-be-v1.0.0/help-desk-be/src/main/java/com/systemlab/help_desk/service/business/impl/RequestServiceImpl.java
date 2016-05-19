package com.systemlab.help_desk.service.business.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.systemlab.base.exception.EmptyListException;
import com.systemlab.base.repository.hibernate.BaseRepository;
import com.systemlab.base.service.business.impl.BaseServiceImpl;
import com.systemlab.base.util.bean.BeanUtil;
import com.systemlab.base.util.date.DateUtil;
import com.systemlab.base.wapper.RequestList;
import com.systemlab.base.wapper.RequestObject;
import com.systemlab.help_desk.dto.RequestDto;
import com.systemlab.help_desk.dto.UserDto;
import com.systemlab.help_desk.entity.Request;
import com.systemlab.help_desk.enums.RequestState;
import com.systemlab.help_desk.repository.hibernate.RequestRepository;
import com.systemlab.help_desk.service.business.RequestService;
import com.systemlab.help_desk.service.business.UserService;
import com.systemlab.help_desk.service.email.EmailService;
import com.systemlab.help_desk.service.file.FileService;

/**
 * RequestServiceImpl
 * 
 * @author Josue Barrios
 * @version 1.0.0
 * @since 1.0.0
 */

@Service
public class RequestServiceImpl extends BaseServiceImpl<RequestDto, Request>
		implements RequestService {

	// private static org.apache.log4j.Logger log =
	// Logger.getLogger(RequestServiceImpl.class);

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@Autowired
	FileService fileService;

	protected BaseRepository<Request> getBaseRepository() {
		return requestRepository;
	}

	protected void validateCreate(Request request) throws Exception {

	}

	protected void validateUpdate(Request request) throws Exception {

	}

	private String getGeneratedCode() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString().substring(0, 10)
				.toUpperCase();
	}

	private UserDto getUserById(Integer UserId) throws Exception {
		RequestObject userRequestObject = new RequestObject();

		Map<String, String> userProjections = new HashMap<String, String>();
		userProjections.put("paternal_name", "paternal_name");
		userProjections.put("maternal_name", "maternal_name");
		userProjections.put("name", "name");
		userProjections.put("email", "email");
		userRequestObject.setProjections(userProjections);

		UserDto userDto = userService.get(UserId, userRequestObject);

		return userDto;
	}

	@Transactional
	public Integer add(RequestDto requestDto, String provider,
			MultipartFile file) throws Exception {
		String code = getGeneratedCode();
		requestDto.setCode(code);
		Integer user_opeId = requestRepository.getUserOperator(provider);
		UserDto userOpeDto = new UserDto();
		userOpeDto.setId(user_opeId);
		requestDto.setUser_ope(userOpeDto);

		UserDto userReqDto = new UserDto();
		userReqDto.setId(requestDto.getUserRoleId());
		requestDto.setUser_req(userReqDto);

		userOpeDto = getUserById(requestDto.getUser_ope().getId());
		userReqDto = getUserById(requestDto.getUser_req().getId());

		Integer returnInt = null;

		// TODO improve if else
		if (file != null) {
			if (!file.isEmpty()) {
				String fileExtension = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."),
						file.getOriginalFilename().length());
				String fileNameInServer = code + fileExtension;
				requestDto.setFile(fileNameInServer);
				returnInt = super.add(requestDto);
				fileService.upload(file, fileNameInServer);
//				emailService.sendAssignmentOfRequirement(
//						userOpeDto.getEmail(),
//						userReqDto.getEmail(),
//						code,
//						userOpeDto.getName() + " "
//								+ userOpeDto.getPaternal_name() + " "
//								+ userOpeDto.getMaternal_name(),
//						userReqDto.getName() + " "
//								+ userReqDto.getPaternal_name() + " "
//								+ userReqDto.getMaternal_name(),
//						requestDto.getDescription());
			} else {
				returnInt = super.add(requestDto);
//				emailService.sendAssignmentOfRequirement(
//						userOpeDto.getEmail(),
//						userReqDto.getEmail(),
//						code,
//						userOpeDto.getName() + " "
//								+ userOpeDto.getPaternal_name() + " "
//								+ userOpeDto.getMaternal_name(),
//						userReqDto.getName() + " "
//								+ userReqDto.getPaternal_name() + " "
//								+ userReqDto.getMaternal_name(),
//						requestDto.getDescription());
			}
		} else {
			returnInt = super.add(requestDto);
//			emailService.sendAssignmentOfRequirement(userOpeDto.getEmail(),
//					userReqDto.getEmail(), code,
//					userOpeDto.getName() + " " + userOpeDto.getPaternal_name()
//							+ " " + userOpeDto.getMaternal_name(),
//					userReqDto.getName() + " " + userReqDto.getPaternal_name()
//							+ " " + userReqDto.getMaternal_name(),
//					requestDto.getDescription());
		}

		return returnInt;
	}

	@Override
	public Integer edit(RequestDto requestDto) throws Exception {
		if (requestDto.getState().equals(RequestState.RESOLVED.getValue())
			|| requestDto.getState().equals(RequestState.NOT_RESOLVED.getValue())){
			requestDto.setEnd_date(DateUtil.parseDateToString(new Date(), DateUtil.HOUR));
		}
		return super.edit(requestDto);
	}
	
	private List<RequestDto> getlistWhitNoDevice(RequestList requestList) throws Exception{
		List<RequestDto> addRequestDtoList = BeanUtil.entityListToDtoList(requestRepository.getlistWhitNoDevice(requestList),RequestDto.class);
		if(addRequestDtoList==null){
			throw new EmptyListException();
		}
		return addRequestDtoList;
	}
	
	@Override
	public List<RequestDto> list(RequestList requestList) throws Exception {
		boolean getListWithNoDevice = false;
		List<RequestDto> returnRequestDtoList = null;
		try {
			returnRequestDtoList = super.list(requestList);
			getListWithNoDevice = true;
			List<RequestDto> addRequestDtoList = BeanUtil.entityListToDtoList(requestRepository.getlistWhitNoDevice(requestList),RequestDto.class);
			if(addRequestDtoList!=null){
				for(RequestDto addRequest:addRequestDtoList){
					returnRequestDtoList.add(addRequest);
				}
				RequestDto auxRequestDto = null;
				for(int i=0;i<returnRequestDtoList.size()-1;i++){
					for(int j=i+1;j<returnRequestDtoList.size();j++){
						if(returnRequestDtoList.get(i).getStart_date().compareTo(returnRequestDtoList.get(j).getStart_date())<0){
							auxRequestDto = returnRequestDtoList.get(i);
							returnRequestDtoList.set(i, returnRequestDtoList.get(j));
							returnRequestDtoList.set(j, auxRequestDto);
						}
					}
				}
			}
			return returnRequestDtoList;
		} catch (Exception e) {
			if(e instanceof EmptyListException){
				if(!getListWithNoDevice){
					return getlistWhitNoDevice(requestList);
				}else{
					return returnRequestDtoList;
				}
			}else{
				throw e;
			}
		}
	}
	
}
