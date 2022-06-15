/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.ASRRegione;
import it.csi.conspref.consprefbe.ws.model.FonteRegione;
import it.csi.conspref.consprefbe.ws.model.Operatore;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "revocaConsensoRichiesta", propOrder = {
		"requestId",
		"codiceServizio",
		"cfRichiedente",
		"idAura",
		"cfDelegato",
		"operatore",
		"fonte",
		"dataAcquisizione",
		"codiceTipoConsenso",
		"codiceSottotipoConsenso",
		"descrizioneSottotipoConsenso",
		"elencoAsr"
	})
public class RevocaConsensoRichiesta {
	
    @XmlElement(required = true)
    protected String requestId;
    @XmlElement(required = true)
    protected String codiceServizio;
    @XmlElement(required = true)
    protected String cfRichiedente;
    @XmlElement(required = true)
    protected String idAura;
    protected String cfDelegato;
    protected Operatore operatore;
    @XmlElement(required = true)
    protected FonteRegione fonte;
    @XmlElement(required = true)
    protected String dataAcquisizione;
    @XmlElement(required = true)
    protected String codiceTipoConsenso;
    @XmlElement(required = true)
    protected String codiceSottotipoConsenso;
    @XmlElement(required = true)
    protected String descrizioneSottotipoConsenso;
    
    @XmlElementWrapper(name="elencoAsr", required = true)
   	@XmlElement(name="asr", required = true)
   	protected List<ASRRegione> elencoAsr;

    
    
	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the codiceServizio
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}

	/**
	 * @param codiceServizio the codiceServizio to set
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	/**
	 * @return the cfRichiedente
	 */
	public String getCfRichiedente() {
		return cfRichiedente;
	}

	/**
	 * @param cfRichiedente the cfRichiedente to set
	 */
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}

	/**
	 * @return the idAura
	 */
	public String getIdAura() {
		return idAura;
	}

	/**
	 * @param idAura the idAura to set
	 */
	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	/**
	 * @return the cfDelegato
	 */
	public String getCfDelegato() {
		return cfDelegato;
	}

	/**
	 * @param cfDelegato the cfDelegato to set
	 */
	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}

	/**
	 * @return the operatore
	 */
	public Operatore getOperatore() {
		return operatore;
	}

	/**
	 * @param operatore the operatore to set
	 */
	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}

	/**
	 * @return the fonte
	 */
	public FonteRegione getFonte() {
		return fonte;
	}

	/**
	 * @param fonte the fonte to set
	 */
	public void setFonte(FonteRegione fonte) {
		this.fonte = fonte;
	}

	/**
	 * @return the dataAcquisizione
	 */
	public String getDataAcquisizione() {
		return dataAcquisizione;
	}

	/**
	 * @param dataAcquisizione the dataAcquisizione to set
	 */
	public void setDataAcquisizione(String dataAcquisizione) {
		this.dataAcquisizione = dataAcquisizione;
	}

	/**
	 * @return the codiceTipoConsenso
	 */
	public String getCodiceTipoConsenso() {
		return codiceTipoConsenso;
	}

	/**
	 * @param codiceTipoConsenso the codiceTipoConsenso to set
	 */
	public void setCodiceTipoConsenso(String codiceTipoConsenso) {
		this.codiceTipoConsenso = codiceTipoConsenso;
	}

	/**
	 * @return the codiceSottotipoConsenso
	 */
	public String getCodiceSottotipoConsenso() {
		return codiceSottotipoConsenso;
	}

	/**
	 * @param codiceSottotipoConsenso the codiceSottotipoConsenso to set
	 */
	public void setCodiceSottotipoConsenso(String codiceSottotipoConsenso) {
		this.codiceSottotipoConsenso = codiceSottotipoConsenso;
	}

	/**
	 * @return the descrizioneSottotipoConsenso
	 */
	public String getDescrizioneSottotipoConsenso() {
		return descrizioneSottotipoConsenso;
	}

	/**
	 * @param descrizioneSottotipoConsenso the descrizioneSottotipoConsenso to set
	 */
	public void setDescrizioneSottotipoConsenso(String descrizioneSottotipoConsenso) {
		this.descrizioneSottotipoConsenso = descrizioneSottotipoConsenso;
	}

	/**
	 * @return the elencoAsr
	 */
	public List<ASRRegione> getElencoAsr() {
		return elencoAsr;
	}

	/**
	 * @param elencoAsr the elencoAsr to set
	 */
	public void setElencoAsr(List<ASRRegione> elencoAsr) {
		this.elencoAsr = elencoAsr;
	}
   
    
}
