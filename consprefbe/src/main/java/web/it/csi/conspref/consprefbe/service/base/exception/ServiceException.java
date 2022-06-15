/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.base.exception;

import java.util.ArrayList;
import java.util.List;

import it.csi.conspref.consprefbe.ws.model.ErroreRegione;
import it.csi.conspref.consprefbe.ws.model.Errori;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
/**
 * 
 * Sollevando questa eccezione all'interno delle implementazioni 
 * sarà possibile specificare la lista di errori da restituire nel servizio SOAP.
 * 
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 9089026278432011826L;
	
	private List<Errori> errori;
	
	public ServiceException() {
		super();
	}

	public ServiceException(List<Errori> errori) {
		super();
		this.errori = errori;
	}
	
	public ServiceException(Errori errore) {
		super(errore.getErrore().getCodEsito() + " "+ errore.getErrore().getEsito() + " " + errore.getErrore().getTipoErrore().toString());
		this.getErrori().add(errore);
	}
	
	public ServiceException(String codEsito, String esito, RisultatoCodice tipoErrore) {
		this(new Errori(new ErroreRegione(codEsito, esito, tipoErrore)));
	}
	
	public ServiceException(String codEsito, String esito, RisultatoCodice tipoErrore, Throwable cause) {
		this(new Errori(new ErroreRegione(codEsito, esito, tipoErrore)), cause);
	}
	
	public ServiceException(List<Errori> errori, Throwable cause) {
		super(cause);
		this.errori = errori;
	}
	
	public ServiceException(Errori errore, Throwable cause) {
		super(errore.getErrore().getCodEsito() + " "+ errore.getErrore().getEsito() + " " + errore.getErrore().getTipoErrore().toString(), cause);
		this.getErrori().add(errore);
	}
	

	public List<Errori> getErrori() {
		if (errori == null) {
			errori = new ArrayList<Errori>();
		}
		return errori;
	}

	public void setErrori(List<Errori> errori) {
		this.errori = errori;
	}
	

}
