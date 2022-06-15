/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.SottoTipoConsenso;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaSottoTipoBeResponse")
public class ConsultaSottoTipoBeResponse extends ServiceResponse {
	
	protected List<SottoTipoConsenso> sottotipiConsenso;

	public List<SottoTipoConsenso> getSottotipiConsenso() {
		return sottotipiConsenso;
	}

	public void setSottotipiConsenso(List<SottoTipoConsenso> sottotipiConsenso) {
		this.sottotipiConsenso = sottotipiConsenso;
	}
	
	
}
