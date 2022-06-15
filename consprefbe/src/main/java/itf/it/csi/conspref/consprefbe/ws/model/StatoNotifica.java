/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statoNotifica", namespace = "statoNotifica", propOrder = {
	    "consId",
	    "uuid",
	    "codASR",
	    "dipartimentale",
	    "statoConsenso",
	    "statoNotifica",
	    "statoDecodificato",
	})
public class StatoNotifica {
	public Integer getConsId() {
		return consId;
	}
	public void setConsId(Integer consId) {
		this.consId = consId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCodASR() {
		return codASR;
	}
	public void setCodASR(String codASR) {
		this.codASR = codASR;
	}
	public String getDipartimentale() {
		return dipartimentale;
	}
	public void setDipartimentale(String dipartimentale) {
		this.dipartimentale = dipartimentale;
	}
	public String getStatoConsenso() {
		return statoConsenso;
	}
	public void setStatoConsenso(String statoConsenso) {
		this.statoConsenso = statoConsenso;
	}
	public String getStatoNotifica() {
		return statoNotifica;
	}
	public void setStatoNotifica(String statoNotifica) {
		this.statoNotifica = statoNotifica;
	}
	public String getStatoDecodificato() {
		return statoDecodificato;
	}
	public void setStatoDecodificato(String statoDecodificato) {
		this.statoDecodificato = statoDecodificato;
	}
	private Integer consId;
	private String uuid;
	private String codASR;
	private String dipartimentale;
	private String statoConsenso;
	private String statoNotifica;
	private String statoDecodificato;

}
