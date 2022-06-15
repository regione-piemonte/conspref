/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.model;

import java.util.Date;

public class Servizio extends Codifica {
	
	
	private String  descrizioneEstesa;
	private Boolean arruolabile;
	private Boolean delegabile;
	private Boolean notificabile;
	private Boolean obbligatorioArruolamento;
	private Boolean minore;
	private String  url;
	private Integer numeroGiorniDelegabili;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDescrizioneEstesa() {
		return descrizioneEstesa;
	}
	public void setDescrizioneEstesa(String descrizioneEstesa) {
		this.descrizioneEstesa = descrizioneEstesa;
	}
	public Boolean getNotificabile() {
		return notificabile;
	}
	public void setNotificabile(Boolean notificabile) {
		this.notificabile = notificabile;
	}
	public Boolean getDelegabile() {
		return delegabile;
	}
	public void setDelegabile(Boolean delegabile) {
		this.delegabile = delegabile;
	}
	public Boolean getArruolabile() {
		return arruolabile;
	}
	public void setArruolabile(Boolean arruolabile) {
		this.arruolabile = arruolabile;
	}
	public Boolean getObbligatorioArruolamento() {
		return obbligatorioArruolamento;
	}
	public void setObbligatorioArruolamento(Boolean obbligatorioArruolamento) {
		this.obbligatorioArruolamento = obbligatorioArruolamento;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getNumeroGiorniDelegabili() {
		return numeroGiorniDelegabili;
	}
	public void setNumeroGiorniDelegabili(Integer numeroGiorniValidita) {
		this.numeroGiorniDelegabili = numeroGiorniValidita;
	}
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public Date getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public Boolean getMinore() {
		return minore;
	}
	public void setMinore(Boolean minore) {
		this.minore = minore;
	}
	
	
	
	

}
