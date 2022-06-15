/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import it.csi.conspref.NotificaAcquisizioneConsensoRicevuta;
import it.csi.conspref.NotificaAcquisizioneConsensoRichiesta;
import it.csi.conspref.NotificaRevocaConsensoRicevuta;
import it.csi.conspref.NotificaRevocaConsensoRichiesta;
import it.csi.conspref.VerificaServizio;
import it.csi.conspref.VerificaServizioRicevuta;
import it.csi.conspref.consprefnotifica.util.SendEmailSMTP;
import it.csi.conspref.consprefnotifica.ws.NotificaAcquisizioneConsensoEstesaRichiesta;
import it.csi.conspref.consprefnotifica.ws.NotificaRevocaConsensoEstesaRichiesta;

public class NotificaClient extends WebServiceGatewaySupport{
	private static final Logger log = LoggerFactory.getLogger(NotificaClient.class);
	

	public NotificaRevocaConsensoRicevuta callNotificaRevocaConsenso (NotificaRevocaConsensoEstesaRichiesta notificaRevocaConsensoRichiesta) {
		log.info("callNotificaAcquisizioneConsenso codiceServizio " + notificaRevocaConsensoRichiesta.getCodiceServizio());
		log.info("callNotificaRevocaConsenso requestId " + notificaRevocaConsensoRichiesta.getRequestId());
		log.info("callNotificaRevocaConsenso EndpointAsr " + notificaRevocaConsensoRichiesta.getEndpointAsr());
		NotificaRevocaConsensoRicevuta response = (NotificaRevocaConsensoRicevuta) getWebServiceTemplate()
				.marshalSendAndReceive(notificaRevocaConsensoRichiesta.getEndpointAsr(), (NotificaRevocaConsensoRichiesta)notificaRevocaConsensoRichiesta,
						new SoapActionCallback(
								"http://conspref.csi.it/ConsprefNotificaService/notificaRevocaConsensoService"));

		return response;
	}
	
	public NotificaAcquisizioneConsensoRicevuta callNotificaAcquisizioneConsenso (NotificaAcquisizioneConsensoEstesaRichiesta notificaAcquisizioneConsensoRichiesta) {
		log.info("callNotificaAcquisizioneConsenso codiceServizio " + notificaAcquisizioneConsensoRichiesta.getCodiceServizio());
		log.info("callNotificaAcquisizioneConsenso requestId " + notificaAcquisizioneConsensoRichiesta.getRequestId());
		log.info("callNotificaAcquisizioneConsenso EndpointAsr " + notificaAcquisizioneConsensoRichiesta.getEndpointAsr());
		NotificaAcquisizioneConsensoRicevuta response = (NotificaAcquisizioneConsensoRicevuta) getWebServiceTemplate()
				.marshalSendAndReceive(notificaAcquisizioneConsensoRichiesta.getEndpointAsr(), (NotificaAcquisizioneConsensoRichiesta)notificaAcquisizioneConsensoRichiesta,
						new SoapActionCallback(
								"http://conspref.csi.it/ConsprefNotificaService/notificaAcquisizioneConsensoService"));

		return response;
	}
	
	public VerificaServizioRicevuta callVerificaServizio(String codiceServizio, String requestId, String endpoint) {
		log.info("Entro in callVerificaServizio");
		VerificaServizio request = new VerificaServizio();
		request.setCodiceServizio(codiceServizio);
		request.setRequestId(requestId);
		

		log.info("codiceServizio= " + codiceServizio);
		log.info("requestId= " + requestId);

		VerificaServizioRicevuta response=null;
        try {
		  response = (VerificaServizioRicevuta) getWebServiceTemplate()
				.marshalSendAndReceive(endpoint, request,
						new SoapActionCallback(
								"http://conspref.csi.it/ConsprefNotificaService/verificaServizioService"));

		
        } catch (Exception e) {
            log.error("ERROR durante callVerificaServizio: "+e);
            throw(e);
        }
        log.info("Esco in callVerificaServizio");
		return response;
	}
	



}
