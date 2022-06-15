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
@XmlType(name = "fonte")
public class FonteRegione {

	@XmlElement(required = true)
	protected String codiceTipoFonte;
	@XmlElement(required = true)
	protected String codiceFonte;
	
	
	/**
	 * @return the codiceFonte
	 */
	public String getCodiceFonte() {
		return codiceFonte;
	}
	/**
	 * @param codiceFonte the codiceFonte to set
	 */
	public void setCodiceFonte(String codiceFonte) {
		this.codiceFonte = codiceFonte;
	}
	/**
	 * @return the codiceTipoFonte
	 */
	public String getCodiceTipoFonte() {
		return codiceTipoFonte;
	}
	/**
	 * @param codiceTipoFonte the codiceTipoFonte to set
	 */
	public void setCodiceTipoFonte(String codiceTipoFonte) {
		this.codiceTipoFonte = codiceTipoFonte;
	}

}
