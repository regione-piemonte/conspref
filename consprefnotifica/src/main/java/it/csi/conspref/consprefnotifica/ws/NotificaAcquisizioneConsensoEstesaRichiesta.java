/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.ws;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.csi.conspref.NotificaAcquisizioneConsensoRichiesta;

public class NotificaAcquisizioneConsensoEstesaRichiesta extends NotificaAcquisizioneConsensoRichiesta {

	private static final Logger log = LoggerFactory.getLogger(NotificaAcquisizioneConsensoEstesaRichiesta.class);
	private String endpointAsr;	

	public String getEndpointAsr() {
		return endpointAsr;
	}

	public void setEndpointAsr(String endpointAsr) {
		this.endpointAsr = endpointAsr;
	}			
}
