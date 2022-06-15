/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Fonte;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.msg.ServiceRequest;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaConsensoBe")
public class ConsultaConsensoBe extends ServiceRequest {

    
	@XmlElement(required = true)
    protected String 	cfCittadino;
	protected String 	cfDelegato;
	protected Operatore operatore;
	@XmlElement(required = true)
    protected Fonte		fonte;
	protected String    codiceTipoConsenso;
	protected String 	codiceSottotipoConsenso;
	protected String  idInformativa;
	protected String codAsr;
	@XmlElement(required = true)	
	protected Boolean verificaAura;
	
	
	
	public Boolean getVerificaAura() {
		return verificaAura;
	}
	public void setVerificaAura(Boolean verificaAura) {
		this.verificaAura = verificaAura;
	}
	/**
	 * @return the cfCittadino
	 */
	public String getCfCittadino() {
		return cfCittadino;
	}
	/**
	 * @param cfCittadino the cfCittadino to set
	 */
	public void setCfCittadino(String cfCittadino) {
		this.cfCittadino = cfCittadino;
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
	public Fonte getFonte() {
		return fonte;
	}
	/**
	 * @param fonte the fonte to set
	 */
	public void setFonte(Fonte fonte) {
		this.fonte = fonte;
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
	public String getIdInformativa() {
		return idInformativa;
	}
	public void setIdInformativa(String idInformativa) {
		this.idInformativa = idInformativa;
	}
	public String getCodAsr() {
		return codAsr;
	}
	public void setCodAsr(String codAsr) {
		this.codAsr = codAsr;
	}
	
    

}
