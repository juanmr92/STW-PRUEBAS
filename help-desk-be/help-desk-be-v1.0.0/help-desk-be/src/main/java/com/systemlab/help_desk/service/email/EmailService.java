package com.systemlab.help_desk.service.email;

/**
 * RequestRepositoryImpl
 * 
 * @author Josue Barrios
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EmailService {

	void sendAssignmentOfRequirement(String operatorEmail,
			String claimantEmail, String code, String operatorName,
			String claimantName, String description) throws Exception;

	void sendRequestExpiredAlert(String operatorEmail, String code,
			String operatorName, String description) throws Exception;

}
