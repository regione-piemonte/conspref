/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi;

import java.io.Serializable;
import java.sql.Timestamp;

import it.csi.conspref.Asr;
import it.csi.conspref.Fonte;
import it.csi.conspref.Operatore;

public class Consenso implements Serializable {
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Integer consId;

	protected String requestId;
    protected String codiceServizio;
    protected String cfRichiedente;
    protected String idAura;
    protected String cfDelegato;
    protected Operatore operatore;
    protected Fonte fonte;

    protected String codiceTipoConsenso;
    protected String codiceSottotipoConsenso;
    protected String descrizioneSottotipoConsenso;
    protected String valoreConsenso;
    protected Asr asr;
	private String endpointAsr;
	private Timestamp dataAcquisizione;
	private String stato ;
	private Integer dInformativaId;
	
	
	
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getIdAura() {
		return idAura;
	}
	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}
	public String getCfDelegato() {
		return cfDelegato;
	}
	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}
	public Operatore getOperatore() {
		return operatore;
	}
	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}
	public Fonte getFonte() {
		return fonte;
	}
	public void setFonte(Fonte fonte) {
		this.fonte = fonte;
	}
	public Timestamp getDataAcquisizione() {
		return dataAcquisizione;
	}
	public void setDataAcquisizione(Timestamp dataAcquisizione) {
		this.dataAcquisizione = dataAcquisizione;
	}
	public String getCodiceTipoConsenso() {
		return codiceTipoConsenso;
	}
	public void setCodiceTipoConsenso(String codiceTipoConsenso) {
		this.codiceTipoConsenso = codiceTipoConsenso;
	}
	public String getCodiceSottotipoConsenso() {
		return codiceSottotipoConsenso;
	}
	public void setCodiceSottotipoConsenso(String codiceSottotipoConsenso) {
		this.codiceSottotipoConsenso = codiceSottotipoConsenso;
	}
	public String getDescrizioneSottotipoConsenso() {
		return descrizioneSottotipoConsenso;
	}
	public void setDescrizioneSottotipoConsenso(String descrizioneSottotipoConsenso) {
		this.descrizioneSottotipoConsenso = descrizioneSottotipoConsenso;
	}
	public String getValoreConsenso() {
		return valoreConsenso;
	}
	public void setValoreConsenso(String valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
	}
	public Asr getAsr() {
		return asr;
	}
	public void setAsr(Asr asr) {
		this.asr = asr;
	}
	public String getEndpointAsr() {
		return endpointAsr;
	}
	public void setEndpointAsr(String endpointAsr) {
		this.endpointAsr = endpointAsr;
	}

    public Integer getConsId() {
		return consId;
	}
	public void setConsId(Integer consId) {
		this.consId = consId;
	}
	public Integer getdInformativaId() {
		return dInformativaId;
	}
	public void setdInformativaId(Integer dInformativaId) {
		this.dInformativaId = dInformativaId;
	}

}
