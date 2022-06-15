/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;



@XmlType(name = "eistoErrore")
@XmlEnum
public enum EsitoErrore {

    NESSUN_ERRORE ("0000"),
    BLOCCANTE ("9999"),
    AVVISO ("0001");
	
	private String value;

	EsitoErrore(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
    public String value() {
        return name();
    }

    public static EsitoErrore fromValue(String v) {
        return valueOf(v);
    }

}
