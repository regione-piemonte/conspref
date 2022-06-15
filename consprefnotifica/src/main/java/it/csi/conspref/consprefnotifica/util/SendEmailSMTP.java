/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.util;

import com.sun.mail.smtp.SMTPTransport;

import it.csi.consprefnotifica.ConsprefNotificaApplication;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class SendEmailSMTP {

	private static final Logger log = LoggerFactory.getLogger(SendEmailSMTP.class);

	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private String port;
	
	@Value("${spring.mail.from}")
	private String from;
	
	@Value("${spring.mail.to}")
	private String to;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${notificatore.mail.oggetto}")
	private String oggettoMail;
	
	@Value("${notificatore.mail.to}")
	private String mailTO;
	
	@Value("${notificatore.mail.body}")
	private String bodyMail;

    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT_CALL_VERIFICA = "Errore nel raggiungere end point: ";
    private static final String EMAIL_TEXT_CALL_VERIFICA = "Si è verificato un errore nel raggiungere l'end-point in oggetto \n  callVerificaServizio non risponde";
    private static final String EMAIL_SUBJECT_CALL_ACQ_REVOCA = "Errore nel raggiungere end point: ";
    private static final String EMAIL_TEXT_CALL_ACQ_REVOCA = "Si è verificato un errore mentre si stava inoltrando un consenso verso l'end-point in oggetto: ";
    private static final String EMAIL_SUBJECT_DEFAULT = "Errore non codificato ";
    private static final String EMAIL_TEXT_DEFAULT = "Si è verificato un errore non codificato nella classe SendEmailSMTP";

    public void send(String urlEndPoint, String cons_id, String eccezione, int tipoErrore) {
    	log.info("Entro in SendEmailSMTP metodo send");
    	Properties prop = System.getProperties();
        prop.put("mail.smtp.host", host); 
   
        prop.put("mail.smtp.port", port); 


        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
		

        	String ff=from;
            msg.setFrom(new InternetAddress(ff));

			 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));

			
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			
            switch(tipoErrore) {
            case 1:
            
            	msg.setSubject(EMAIL_SUBJECT_CALL_VERIFICA+urlEndPoint);		
             
            	msg.setText(EMAIL_TEXT_CALL_VERIFICA+"\n"+eccezione);
            	break;
            case 2:

            	msg.setSubject(EMAIL_SUBJECT_CALL_ACQ_REVOCA+urlEndPoint+" cons_id:"+cons_id);		
             
            	msg.setText(EMAIL_TEXT_CALL_ACQ_REVOCA+"\n"+eccezione);
            	break;
            case 3:
            	 msg.setRecipients(Message.RecipientType.TO,
                         InternetAddress.parse(mailTO, false));
      
            	msg.setSubject(oggettoMail);		
            	 
            	msg.setText(bodyMail+urlEndPoint+"\n"+eccezione);
            	break;
            default:
            	
            	msg.setSubject(EMAIL_SUBJECT_DEFAULT);		
            	 
            	msg.setText(EMAIL_TEXT_DEFAULT+"\n"+eccezione);    	
            }
			
            msg.setSentDate(new Date());

			
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			
            t.connect(host, username, password);
			
			
            t.sendMessage(msg, msg.getAllRecipients());



            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        log.info("Esco da SendEmailSMTP metodo send"); 
    }
      
}
