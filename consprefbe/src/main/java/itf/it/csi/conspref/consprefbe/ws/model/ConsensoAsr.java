/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consInformativa", propOrder = {
    "tipoASR",
    "valoreConsenso",
    "uuid"
})
public class ConsensoAsr {

	protected TipoASR tipoASR;
    protected ValoreConsenso valoreConsenso;
    protected UUID uuid;
    
    
	/**
	 * @return the valoreConsenso
	 */
	public ValoreConsenso getValoreConsenso() {
		return valoreConsenso;
	}
	/**
	 * @param valoreConsenso the valoreConsenso to set
	 */
	public void setValoreConsenso(ValoreConsenso valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
	}
	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the tipoASR
	 */
	public TipoASR getTipoASR() {
		return tipoASR;
	}
	/**
	 * @param tipoASR the tipoASR to set
	 */
	public void setTipoASR(TipoASR tipoASR) {
		this.tipoASR = tipoASR;
	}
    
    

}
