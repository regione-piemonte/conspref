/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Informativa;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaInformativaResponse")
public class ConsultaInformativaResponse extends ServiceResponse {

	@XmlElementWrapper
	@XmlElement(name="informativa")
	protected List<Informativa> 	informative;
	
	

	/**
	 * @return the informative
	 */
	public List<Informativa> getInformative() {
		return informative;
	}

	/**
	 * @param informative the informative to set
	 */
	public void setInformative(List<Informativa> informative) {
		this.informative = informative;
	}


	
}
