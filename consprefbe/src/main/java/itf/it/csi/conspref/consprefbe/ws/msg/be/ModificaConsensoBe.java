/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.msg.be;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Fonte;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.model.TipoASR;
import it.csi.conspref.consprefbe.ws.model.TipoStato;
import it.csi.conspref.consprefbe.ws.model.ValoreConsenso;
import it.csi.conspref.consprefbe.ws.msg.ServiceRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modificaConsensoBe", propOrder = { "cfCittadino", "idAura", "cfDelegato", "operatore", "fonte", "dataAcquisizione",
		"idInformativa", "tipoStato", "valoreConsenso", "asr" })
public class ModificaConsensoBe extends ServiceRequest {

	@XmlElement(required = true)
	protected String cfCittadino;
	@XmlElement(required = true)
	protected String idAura;
	protected String cfDelegato;
	@XmlElement(required = true)
	protected Operatore operatore;
	@XmlElement(required = true)
	protected Fonte fonte;
	@XmlElement(required = true)
	protected Date dataAcquisizione;
	@XmlElement(required = true)
	protected Integer idInformativa;
	@XmlElement(required = true)
	protected TipoStato tipoStato;
	@XmlElement(required = true)
	protected ValoreConsenso valoreConsenso;
	@XmlElement(required = true)
	protected TipoASR asr;

	
	public String getCfCittadino() {
		return cfCittadino;
	}

	public void setCfCittadino(String cfCittadino) {
		this.cfCittadino = cfCittadino;
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

	public Date getDataAcquisizione() {
		return dataAcquisizione;
	}

	public void setDataAcquisizione(Date dataAcquisizione) {
		this.dataAcquisizione = dataAcquisizione;
	}

	public Integer getIdInformativa() {
		return idInformativa;
	}

	public void setIdInformativa(Integer idInformativa) {
		this.idInformativa = idInformativa;
	}

	public TipoASR getAsr() {
		return asr;
	}

	public void setAsr(TipoASR asr) {
		this.asr = asr;
	}

	public TipoStato getTipoStato() {
		return tipoStato;
	}

	public void setTipoStato(TipoStato tipoStato) {
		this.tipoStato = tipoStato;
	}

	public ValoreConsenso getValoreConsenso() {
		return valoreConsenso;
	}

	public void setValoreConsenso(ValoreConsenso valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
	}
	

}
