/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.TipoASR;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaAsrBeResponse")
public class ConsultaAsrBeResponse extends ServiceResponse {
	@XmlElementWrapper
	@XmlElement(name="asr")
	protected List<TipoASR> asr;

	public List<TipoASR> getAsr() {
		return asr;
	}

	public void setAsr(List<TipoASR> asr) {
		this.asr = asr;
	}

	
	
}
