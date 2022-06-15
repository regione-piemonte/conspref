/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.TipoStato;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaStatoBeResponse")
public class ConsultaStatoBeResponse extends ServiceResponse {
	
	protected List<TipoStato> stati;

	public List<TipoStato> getStati() {
		return stati;
	}

	public void setStati(List<TipoStato> stati) {
		this.stati = stati;
	}
	
	
}
