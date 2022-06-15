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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.csi.conspref.consprefbe.ws.model.Errore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;


/**
 * <p>Classe Java per serviceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="serviceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errori" type="{http://consprefbe.csi.it/}errore" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://consprefbe.csi.it/}risultatoCodice" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceResponse", namespace = "http://consprefbe.csi.it/", propOrder = {
		"esito",
		"elencoErrori"    
})
public class ServiceResponse {

    @XmlElement(namespace = "", nillable = true)
    protected List<Errore> elencoErrori;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "string")
    protected RisultatoCodice esito;

    

    /**
     * Recupera il valore della propriet� esito.
     * 
     * @return
     *     possible object is
     *     {@link RisultatoCodice }
     *     
     */
    public RisultatoCodice getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della propriet� esito.
     * 
     * @param value
     *     allowed object is
     *     {@link RisultatoCodice }
     *     
     */
    public void setEsito(RisultatoCodice value) {
        this.esito = value;
    }
    
    
    public void addErrore(Errore errore) {
    	if(getElencoErrori()==null) {
    		elencoErrori = new ArrayList<Errore>();
    		elencoErrori.add(errore);
    	}else	
    		getElencoErrori().add(errore);
    }
    
    public void addErrore(String codice, String descrizione) {
    	Errore errore = new Errore();
		errore.setCodice(codice);
		errore.setDescrizione(descrizione);
		addErrore(errore);
    }

	/**
	 * @return the elencoErrori
	 */
	public List<Errore> getElencoErrori() {
		return elencoErrori;
	}

	/**
	 * @param elencoErrori the elencoErrori to set
	 */
	public void setElencoErrori(List<Errore> elencoErrori) {
		this.elencoErrori = elencoErrori;
	}

	
    
}
