/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaInformativa")
public class ConsultaInformativa extends ServiceRequest {

    
	@XmlElement(required = true)
    protected String 	codiceTipoConsenso;
	@XmlElement(required = true)
	protected String 	codiceSottotipoConsenso;
	@XmlElement(required = true)
	protected String    dataRiferimento;
	
	

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
	 * @return the dataRiferimento
	 */
	public String getDataRiferimento() {
		return dataRiferimento;
	}

	/**
	 * @param dataRiferimento the dataRiferimento to set
	 */
	public void setDataRiferimento(String dataRiferimento) {
		this.dataRiferimento = dataRiferimento;
	}

    

}
