/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.TipoConsenso;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaTipoBeResponse")
public class ConsultaTipoBeResponse extends ServiceResponse {
	
	protected List<TipoConsenso> tipiConsenso;

	public List<TipoConsenso> getTipiConsenso() {
		return tipiConsenso;
	}

	public void setTipiConsenso(List<TipoConsenso> tipiConsenso) {
		this.tipiConsenso = tipiConsenso;
	}
	
	
}
