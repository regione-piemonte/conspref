/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.StatoNotifica;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaNotificheBeResponse")
public class ConsultaNotificheBeResponse  extends ServiceResponse  {
	private String cfCittadino;
	@XmlElementWrapper
	@XmlElement(name="statoNotifica")
	protected List<StatoNotifica> statoNotifiche;
	
	public List<StatoNotifica> getStatoNotifica() {
		return statoNotifiche;
	}
	public void setStatoNotifica(List<StatoNotifica> statoNotifiche) {
		this.statoNotifiche = statoNotifiche;
	}
	public String getCfCittadino() {
		return cfCittadino;
	}
	public void setCfCittadino(String cfCittadino) {
		this.cfCittadino = cfCittadino;
	}
	
	
}
