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
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaConsensoBeResponse")
public class ConsultaConsensoBeResponse extends ServiceResponse {

	@XmlElementWrapper
	@XmlElement(name="consenso")
	protected List<Consenso> 	consensi;

	/**
	 * @return the consensi
	 */
	public List<Consenso> getConsensi() {
		return consensi;
	}

	/**
	 * @param consensi the consensi to set
	 */
	public void setConsensi(List<Consenso> consensi) {
		this.consensi = consensi;
	}

	
}
