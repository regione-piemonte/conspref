/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consenso", namespace = "consensoCittadino", propOrder = {
	    "cfCittadino",
	    "cfDelegato",
	    "idAura",
	    "nome",
	    "cognome",
	    "dataAcquisizione",
	    "operatore",
	    "fonte",
	    "tipoStato",
	    "informativa",
	    "uuid",
	    "consensiAsr"
	})
public class Consenso {

	@XmlElement(required = true)
    protected String 	cfCittadino;
    protected String 	cfDelegato;
    protected String 	idAura;
    protected String 	nome;
    protected String 	cognome;
    @XmlElement(required = true)
    protected Date 	    dataAcquisizione;
    protected Operatore operatore;
    @XmlElement(required = true)
    protected Fonte		fonte;
    @XmlElement(required = true)
    protected TipoStato tipoStato;
    protected Informativa informativa;
    protected UUID uuid;
    
    @XmlElementWrapper
	@XmlElement(name="consensiAsr")
	protected List<ConsensoAsr> consensiAsr;
    
    
    
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	 * @return the dataAcquisizione
	 */
	public Date getDataAcquisizione() {
		return dataAcquisizione;
	}
	/**
	 * @param dataAcquisizione the dataAcquisizione to set
	 */
	public void setDataAcquisizione(Date dataAcquisizione) {
		this.dataAcquisizione = dataAcquisizione;
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
	 * @return the tipoStato
	 */
	public TipoStato getTipoStato() {
		return tipoStato;
	}
	/**
	 * @param tipoStato the tipoStato to set
	 */
	public void setTipoStato(TipoStato tipoStato) {
		this.tipoStato = tipoStato;
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
	 * @return the informativa
	 */
	public Informativa getInformativa() {
		return informativa;
	}
	/**
	 * @param informativa the informativa to set
	 */
	public void setInformativa(Informativa informativa) {
		this.informativa = informativa;
	}
	/**
	 * @return the consensiAsr
	 */
	@XmlTransient
	public List<ConsensoAsr> getConsensiAsr() {
		return consensiAsr;
	}
	/**
	 * @param consensiAsr the consensiAsr to set
	 */
	public void setConsensiAsr(List<ConsensoAsr> consensiAsr) {
		this.consensiAsr = consensiAsr;
	}

}
