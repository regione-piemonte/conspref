/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.ws;



import java.sql.Timestamp;

import it.csi.conspref.NotificaRevocaConsensoRichiesta;

public class NotificaRevocaConsensoEstesaRichiesta extends NotificaRevocaConsensoRichiesta {

	
	private String endpointAsr;
	private Timestamp dataAcquisizioneTimestamp;
	
	
	public String getDataAcquisizione() {
		return getDataAcquisizioneTimestamp().toString();
	}
	
	public Timestamp getDataAcquisizioneTimestamp() {
		return dataAcquisizioneTimestamp;
	}

	public void setDataAcquisizioneTimestamp(Timestamp dataAcquisizioneTimestamp) {
		this.dataAcquisizioneTimestamp = dataAcquisizioneTimestamp;
	}

	public String getEndpointAsr() {
		return endpointAsr;
	}

	public void setEndpointAsr(String endpointAsr) {
		this.endpointAsr = endpointAsr;
	}
	
}
