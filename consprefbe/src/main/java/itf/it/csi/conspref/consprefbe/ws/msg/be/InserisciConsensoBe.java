/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.msg.ServiceRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciConsensoBe", propOrder = {
		"operatore",
		"consenso"
	})
public class InserisciConsensoBe extends ServiceRequest{
	
	
	@XmlElement(name="operatore")
	private String operatore;	

	@XmlElement(required = true)
	protected Consenso consenso;




	public String getOperatore() {
		return operatore;
	}
	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	public Consenso getConsenso() {
		return consenso;
	}
	public void setConsenso(Consenso consenso) {
		this.consenso = consenso;
	}


	    
}
