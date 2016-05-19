package com.systemlab.help_desk.service.email.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.systemlab.help_desk.exception.SendEmailException;
import com.systemlab.help_desk.service.email.EmailService;

/**
 * RequestRepositoryImpl
 * 
 * @author Josue Barrios
 * @version 1.0.0
 * @since 1.0.0
 */

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private MailSender mailSender;

	private void sendEmail(String to, String subject, String body)
			throws Exception {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("help_desk@systemlab.com.pe");
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(body);
//		try {
//			mailSender.send(message);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new SendEmailException();
//		}
	}

	// TODO use string builder to construc subject and body
	public void sendAssignmentOfRequirement(String operatorEmail,
			String claimantEmail, String code,String operatorName, String claimantName,
			String description) throws Exception {
//		String claimantSubject = code+ "-Notificación por solicitud registrada";
//		String claimantBody = "Señor(a): "+ claimantName+ "\n\n"
//				+ "La Mesa de Ayuda le informa que su solicitud de servicio número:"+ code + " referente a:\n\n"
//				+ description+"\nHa sido registrada.\n\n"
//				+ "Será atendida por uno de nuestros analistas a partir de este momento.\n\n"
//				+ "Si desea consultar el estado de la solicitud, por favor ingrese al Sistema de Tickets Web, en la siguiente dirección web:\n"
//				+ "http://systemlab.com.pe/stw\n\n"
//				+ "Por favor no conteste este correo, ha sido enviado desde una cuenta no monitoreada.\n\n"
//				+ "Atentamente,\n"
//				+ "Mesa de Ayuda FIA-USMP";
//				
//		sendEmail(claimantEmail, claimantSubject, claimantBody);
//		
//		String operatorSubject = code+ "-Notificación por la asignación de solicitud de servicio";
//		String operatorBody = "Señor(a): "+ operatorName+ "\n\n"
//				+ "La Mesa de Ayuda le informa que su solicitud de servicio número:"+ code + " referente a:\n\n"
//				+ description+"\n\n"
//				+ "Si desea consultar el estado de la solicitud, por favor ingrese al Sistema de Tickets Web, en la siguiente dirección web:\n"
//				+ "http://systemlab.com.pe/stw\n\n"
//				+ "Atentamente,\n"
//				+ "Mesa de Ayuda FIA-USMP";
//		
//		sendEmail(operatorEmail, operatorSubject, operatorBody);
	}

	public void sendRequestExpiredAlert(String operatorEmail, String code,
			String operatorName, String description) throws Exception {
//		
//		String operatorSubject = code+ "-Notificación por la expiración de solicitud de servicio";
//		String operatorBody = "Señor(a): "+ operatorName+ "\n\n"
//				+ "La Mesa de Ayuda le informa que su solicitud de servicio número:"+ code + " referente a:\n\n"
//				+ description+"\nHa expirado.\n\n"
//				+ "Si desea consultar el estado de la solicitud, por favor ingrese al Sistema de Tickets Web, en la siguiente dirección web:\n"
//				+ "http://systemlab.com.pe/stw\n\n"
//				+ "Atentamente,\n"
//				+ "Mesa de Ayuda FIA-USMP";
//		
//		sendEmail(operatorEmail, operatorSubject, operatorBody);
	}

}
