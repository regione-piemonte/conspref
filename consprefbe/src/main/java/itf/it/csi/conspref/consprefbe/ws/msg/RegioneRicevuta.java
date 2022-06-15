/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.ErroreRegione;
import it.csi.conspref.consprefbe.ws.model.Errori;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricevuta", propOrder = {
		"esito",
		"elencoErrori"
	})
public class RegioneRicevuta {
   
	@XmlElement(namespace = "")
	protected String esito;
	@XmlElement(namespace = "", nillable = true)
    protected List<Errori> elencoErrori;
	
	
	
	public void addErrore(Errori errore) {
		if(getElencoErrori()==null) 
			elencoErrori = new ArrayList<Errori>();

		getElencoErrori().add(errore);
    }
    
    public void addErrore(String codice, String esito, RisultatoCodice tipoErrore) {
    	Errori elErrori = new Errori();
    	ErroreRegione errore = new ErroreRegione();
    	errore.setCodEsito(codice);
    	errore.setEsito(esito);
    	errore.setTipoErrore(tipoErrore);
    	elErrori.setErrore(errore);
		addErrore(elErrori);
    }
    
    
	
	
	/**
	 * @return the elencoErrori
	 */
	public List<Errori> getElencoErrori() {
		return elencoErrori;
	}
	/**
	 * @param elencoErrori the elencoErrori to set
	 */
	public void setElencoErrori(List<Errori> elencoErrori) {
		this.elencoErrori = elencoErrori;
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
   
}
