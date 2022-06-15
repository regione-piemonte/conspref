/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fonte", namespace = "fonteCittadino", propOrder = {
    "tipoFonte"
})
public class Fonte extends Codifica{

    protected TipoFonte tipoFonte;
    

	/**
	 * @return the tipoFonte
	 */
	public TipoFonte getTipoFonte() {
		return tipoFonte;
	}

	/**
	 * @param tipoFonte the tipoFonte to set
	 */
	public void setTipoFonte(TipoFonte tipoFonte) {
		this.tipoFonte = tipoFonte;
	}

}
