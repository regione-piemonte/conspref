/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Informativa;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciInformativaResponse")
public class InserisciInformativaResponse extends ServiceResponse {
	@XmlElement(name="informativa")
	protected Informativa 	informativa;
	
	

	/**
	 * @return the informative
	 */
	public Informativa getInformative() {
		return informativa;
	}

	/**
	 * @param informative the informative to set
	 */
	public void setInformativa(Informativa informativa) {
		this.informativa = informativa;
	}

}
