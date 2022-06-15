/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Fonte;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.msg.ServiceRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaProfiloBe")
public class ConsultaProfiloBe extends ServiceRequest {
	
	
	@XmlElement(required = true)
	protected String 	cfOperatore;
	@XmlElement(required = true)
	protected Operatore operatore;
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	public Operatore getOperatore() {
		return operatore;
	}
	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}
	
	
}
