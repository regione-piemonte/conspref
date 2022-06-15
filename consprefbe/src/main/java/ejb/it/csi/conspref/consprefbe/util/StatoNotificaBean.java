/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.util;

import java.io.Serializable;

import javax.persistence.Column;


public class StatoNotificaBean implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Column(name="cons_id")
	private Integer consId;
	@Column(name="uuid")
	private String uuid;
	@Column(name="cod_asr")
	private String codASR;
	@Column(name="dipartimentale")
	private String dipartimentale;
	@Column(name="stato_consenso")
	private String statoConsenso;
	@Column(name="stato_notifica")
	private String statoNotifica;
	@Column(name="stato_decodificato")
	private String statoDecodificato;
	
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
	
}
