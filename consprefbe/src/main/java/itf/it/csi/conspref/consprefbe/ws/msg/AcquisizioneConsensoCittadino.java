/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Consenso;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acquisizioneConsensoCittadino")
public class AcquisizioneConsensoCittadino extends ServiceRequest {

    
    protected Consenso 	consenso;

	/**
	 * @return the consenso
	 */
	public Consenso getConsenso() {
		return consenso;
	}

	/**
	 * @param consenso the consenso to set
	 */
	public void setConsenso(Consenso consenso) {
		this.consenso = consenso;
	}
    

}
