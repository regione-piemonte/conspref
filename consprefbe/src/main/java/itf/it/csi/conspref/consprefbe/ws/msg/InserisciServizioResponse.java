/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg;

import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Servizio;

@XmlType
public class InserisciServizioResponse extends ServiceResponse {
	
	private Servizio servizio;

	public Servizio getServizio() {
		return servizio;
	}

	public void setServizio(Servizio servizio) {
		this.servizio = servizio;
	}
	
}
