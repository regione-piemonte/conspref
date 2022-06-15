/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum TipoCodifica {
	
	TIPO_ASR("TIPO_ASR"),
	STATO_CONSENSO("STATO_CONSENSO"),
	TIPO_CONSENSO("TIPO_CONSENSO"),
	SOTTO_TIPO_CONSENSO("SOTTO_TIPO_CONSENSO");
	
	private String value;

	TipoCodifica(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
    public String value() {
        return name();
    }

    public static TipoCodifica fromValue(String v) {
        return valueOf(v);
    }
}
