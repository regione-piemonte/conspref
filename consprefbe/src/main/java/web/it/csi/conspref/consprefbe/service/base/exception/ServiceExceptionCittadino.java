/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.base.exception;

import java.util.ArrayList;
import java.util.List;

import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.ws.model.Errore;
/**
 * Eccezione nell'esecuzione dei {@link BaseCittadinoService}.
 * 
 * Sollevando questa eccezione all'interno delle implementazioni di {@link BaseCittadinoService} 
 * sarà possibile specificare la lista di errori da restituire nel servizio SOAP.
 * 
 * @author Domenico Lisi
 *
 */
public class ServiceExceptionCittadino extends RuntimeException {

	
	private static final long serialVersionUID = 5560607219525404660L;
	
	private List<Errore> errori;
	
	public ServiceExceptionCittadino() {
		super();
	}

	public ServiceExceptionCittadino(List<Errore> errori) {
		super();
		this.errori = errori;
	}
	
	public ServiceExceptionCittadino(Errore errore) {
		super(errore.getCodice() + " "+ errore.getDescrizione());
		this.getErrori().add(errore);
	}
	
	public ServiceExceptionCittadino(String codice, String descrizione) {
		this(new Errore(codice, descrizione));
	}
	
	public ServiceExceptionCittadino(String codice, String descrizione, Throwable cause) {
		this(new Errore(codice, descrizione), cause);
	}
	
	public ServiceExceptionCittadino(List<Errore> errori, Throwable cause) {
		super(cause);
		this.errori = errori;
	}
	
	public ServiceExceptionCittadino(Errore errore, Throwable cause) {
		super(errore.getCodice() + " "+ errore.getDescrizione(), cause);
		this.getErrori().add(errore);
	}
	

	public List<Errore> getErrori() {
		if (errori == null) {
			errori = new ArrayList<Errore>();
		}
		return errori;
	}

	public void setErrori(List<Errore> errori) {
		this.errori = errori;
	}

	
	
	
	
	
	

}
