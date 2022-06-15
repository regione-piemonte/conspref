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
@XmlType(name = "consenso", namespace = "consenso", propOrder = {
    "valoreConsenso",
    "asr"
})
public class ConsensoAsrRegione {

	@XmlElement(required = true)
	protected String valoreConsenso;
	protected ASRRegione asr;
	
	
	/**
	 * @return the valoreConsenso
	 */
	public String getValoreConsenso() {
		return valoreConsenso;
	}
	/**
	 * @param valoreConsenso the valoreConsenso to set
	 */
	public void setValoreConsenso(String valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
	}
	/**
	 * @return the asr
	 */
	public ASRRegione getAsr() {
		return asr;
	}
	/**
	 * @param asr the asr to set
	 */
	public void setAsr(ASRRegione asr) {
		this.asr = asr;
	}
    

}
