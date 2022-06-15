/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per errore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errore")
public class Errori {
	
	public Errori() {
		
	}

	public Errori(ErroreRegione err) {
		this.errore = err;
	}
	
	
	 protected ErroreRegione errore;
	 

	/**
	 * @return the errore
	 */
	public ErroreRegione getErrore() {
		return errore;
	}

	/**
	 * @param errore the errore to set
	 */
	public void setErrore(ErroreRegione errore) {
		this.errore = errore;
	}

}
