/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operatore", propOrder = {
    "tipoOperatore",
    "codiceOperatore"
})
public class Operatore {

	@XmlElement(required = true)
    protected String tipoOperatore;
	@XmlElement(required = true)
    protected String codiceOperatore;
	
    
    /**
	 * @return the tipoOperatore
	 */
	public String getTipoOperatore() {
		return tipoOperatore;
	}
	/**
	 * @param tipoOperatore the tipoOperatore to set
	 */
	public void setTipoOperatore(String tipoOperatore) {
		this.tipoOperatore = tipoOperatore;
	}
	/**
	 * @return the codiceOperatore
	 */
	public String getCodiceOperatore() {
		return codiceOperatore;
	}
	/**
	 * @param codiceOperatore the codiceOperatore to set
	 */
	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

}
