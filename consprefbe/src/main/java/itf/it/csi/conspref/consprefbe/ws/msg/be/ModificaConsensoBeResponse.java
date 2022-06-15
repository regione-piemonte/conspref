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
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modificaConsensoBeResponse")
public class ModificaConsensoBeResponse extends ServiceResponse {
	
	@XmlElement(name="consenso")
	protected Consenso 	consenso;

	public Consenso getConsenso() {
		return consenso;
	}

	public void setConsenso(Consenso consenso) {
		this.consenso = consenso;
	}

}
