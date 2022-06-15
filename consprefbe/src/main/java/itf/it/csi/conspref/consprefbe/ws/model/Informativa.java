/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informativa", propOrder = {
    "tipoConsenso",
    "sottoTipoConsenso",
    "pdfInformativa",
    "dataDecorrenza",
    "dataScadenza"
})
public class Informativa {

    protected TipoConsenso tipoConsenso;
    protected SottoTipoConsenso sottoTipoConsenso;
    protected String pdfInformativa;
    protected Date dataDecorrenza;
    protected Date dataScadenza;
    
    
	
	/**
	 * @return the tipoConsenso
	 */
	public TipoConsenso getTipoConsenso() {
		return tipoConsenso;
	}
	/**
	 * @param tipoConsenso the tipoConsenso to set
	 */
	public void setTipoConsenso(TipoConsenso tipoConsenso) {
		this.tipoConsenso = tipoConsenso;
	}
	/**
	 * @return the sottoTipoConsenso
	 */
	public SottoTipoConsenso getSottoTipoConsenso() {
		return sottoTipoConsenso;
	}
	/**
	 * @param sottoTipoConsenso the sottoTipoConsenso to set
	 */
	public void setSottoTipoConsenso(SottoTipoConsenso sottoTipoConsenso) {
		this.sottoTipoConsenso = sottoTipoConsenso;
	}
	/**
	 * @return the pdfInformativa
	 */
	public String getPdfInformativa() {
		return pdfInformativa;
	}
	/**
	 * @param pdfInformativa the pdfInformativa to set
	 */
	public void setPdfInformativa(String pdfInformativa) {
		this.pdfInformativa = pdfInformativa;
	}
	/**
	 * @return the dataDecorrenza
	 */
	public Date getDataDecorrenza() {
		return dataDecorrenza;
	}
	/**
	 * @param dataDecorrenza the dataDecorrenza to set
	 */
	public void setDataDecorrenza(Date dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}
	/**
	 * @return the dataScadenza
	 */
	public Date getDataScadenza() {
		return dataScadenza;
	}
	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
    

	

}
