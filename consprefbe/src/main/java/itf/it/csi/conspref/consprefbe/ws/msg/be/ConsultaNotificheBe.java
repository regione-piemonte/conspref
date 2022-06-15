/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Errore;
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
@XmlType(name = "ConsultaNotificheBe")
public class ConsultaNotificheBe extends ServiceRequest{
	@XmlElement(required = true)
    protected String 	cfCittadino;
	protected String 	cfDelegato;
	protected Operatore operatore;
	@XmlElement(required = true)
    protected Fonte		fonte;
	protected String    codiceTipoConsenso;
	protected String 	codiceSottotipoConsenso;
	@XmlElement(required = true)
	protected List<String>  uuid;
	
	public List<String> getUuid() {
		return uuid;
	}
	public void setUuid(List<String> uuid) {
		this.uuid = uuid;
	}
	
	public String getCfCittadino() {
		return cfCittadino;
	}
	public void setCfCittadino(String cfCittadino) {
		this.cfCittadino = cfCittadino;
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
}
