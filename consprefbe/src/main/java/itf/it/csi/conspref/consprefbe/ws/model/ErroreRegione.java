/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per errore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errore", namespace="elencoErrori", propOrder = {
	    "codEsito",
	    "esito",
	    "tipoErrore"
	})
public class ErroreRegione {
	
	protected String codEsito;
	protected String esito;
	@XmlSchemaType(name = "string")
	protected RisultatoCodice tipoErrore;
	
	
	public ErroreRegione() {
		
	}

	public ErroreRegione(String codEsito, String esito, RisultatoCodice tipoErrore) {
		this.codEsito = codEsito;
		this.esito = esito;
		this.tipoErrore = tipoErrore;
	}
	
	
	/**
	 * @return the codEsito
	 */
	public String getCodEsito() {
		return codEsito;
	}
	/**
	 * @param codEsito the codEsito to set
	 */
	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}
	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}
	/**
	 * @param esito the esito to set
	 */
	public void setEsito(String esito) {
		this.esito = esito;
	}
	/**
	 * @return the tipoErrore
	 */
	public RisultatoCodice getTipoErrore() {
		return tipoErrore;
	}
	/**
	 * @param tipoErrore the tipoErrore to set
	 */
	public void setTipoErrore(RisultatoCodice tipoErrore) {
		this.tipoErrore = tipoErrore;
	}
	

}
