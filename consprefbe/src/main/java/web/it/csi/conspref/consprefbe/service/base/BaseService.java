/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.service.base.exception.ServiceException;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.LogUtil.ToLog;
import it.csi.conspref.consprefbe.util.PropertiesUtil;
import it.csi.conspref.consprefbe.ws.model.EsitoErrore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.RegioneRicevuta;

/**
 * Classe base dell'implementazione della business logic di un generico servizio.
 * 

 *
 * @param <REQ> Input del servizio 
 * @param <RES> Output del servizio 
 */
public abstract class BaseService<REQ,RES extends RegioneRicevuta> {
	
	@Inject
	protected LogUtil log;
	@Inject
	protected PropertiesUtil properties;
		
	
	/**
	 * Esecuzione del servizo.
	 * 
	 * @param serviceRequest Input del servizio che estende ServiceRequest
	 * @return Output del servizio che estende ServiceResponse
	 */
	public RES executeService(REQ req){
		final String METHOD_NAME = "executeService";
		long time = System.currentTimeMillis();
		log.debug(METHOD_NAME, "Start service: %s", this.getClass().getSimpleName());
		log.logXmlTypeObject(req, "Service Request");
		
		RES res = null;
		try {
			checkServiceParams(req);
			res = execute(req);
		} catch (Exception e) {
			res = handleException(req, e);
		} finally {
			log.logXmlTypeObject(res, "Service Response");
			log.debug(METHOD_NAME, "End service: %s. Elapsed: %s ms.", this.getClass().getSimpleName(),
					(ToLog) () -> (System.currentTimeMillis() - time));

		}
		return res;
	}
	
	protected RES handleException(REQ req, Exception e) {
		final String METHOD_NAME = "handleException";
		RES res = instantiateNewRes();
		res.setEsito(EsitoErrore.BLOCCANTE.getValue());
		
		if(e instanceof ServiceException) {
			ServiceException se = (ServiceException)e;
			log.error(METHOD_NAME, "Service exception in service: %s. Errori: %s "+ (se.getCause()!=null?"Cause":""), se.getCause(),
					this.getClass().getSimpleName(), (ToLog) () -> {

						StringBuilder sb = new StringBuilder();
						se.getErrori().forEach(err -> {
							sb.append("\n   ");
							sb.append(err.getErrore().getCodEsito());
							sb.append(" - ");
							sb.append(err.getErrore().getEsito());
							sb.append(" - ");
							sb.append(err.getErrore().getTipoErrore());
						});
						sb.append("\n");
						return sb.toString();

					});
			if(res.getElencoErrori()==null)
				res.setElencoErrori(se.getErrori());
			else
				res.getElencoErrori().addAll(se.getErrori());
		} else if(e.getCause() instanceof BeServiceException) {
			BeServiceException be = (BeServiceException)e.getCause();
			log.error(METHOD_NAME, "Code Error: " + be.getCode() + " Message: " + be.getMessage());
			res.addErrore(be.getCode(), be.getMessage(), RisultatoCodice.BLOCCANTE);
		}
		else {
			log.error(METHOD_NAME, "Generic exception in service: %s", e, this.getClass().getSimpleName());
			String descrizione = e.getMessage() + (e.getCause()!=null?" [" + e.getCause().getMessage() + "]":"");
			res.addErrore("ERRORE", descrizione, RisultatoCodice.BLOCCANTE);
		}
		
		return res;
	}
	
	

	/**
	 * Implementa l'esecuzione della bussiness logic del servizio.
	 * E' demandato all'implementazione di questo metodo 
	 * l'impostazione dell'esito del servizio
	 * Ad esempio in caso di successo bisognarà eseguire res.setEsito(Esito.SUCCESSO);
	 * @throws ParseException 
	 *  
	 */
	protected abstract RES execute(REQ req) throws ParseException;
	
	

	/**
	 * Instanzia l'oggetto service response vuoto.
	 * Questo metodo prevede che l'oggetto serviceResponse abbia il costruttore vuoto.
	 * Per esigenze più complesse è necessario fare override.
	 * 
	 * @return
	 */
	protected RES instantiateNewRes() {
		final String METHOD_NAME = "instantiateNewRes";
		try {
			ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
			Type[] actualTypeArguments = t.getActualTypeArguments();
			Type type  = actualTypeArguments[1];
			@SuppressWarnings("unchecked")
			Class<RES> c = (Class<RES>)type;
			return c.newInstance();
		} catch (InstantiationException e) {
			String msg = "Errore instanziamento automatico serviceResponse. "
					+ "Deve esistere un costruttore vuoto. Per esigenze più complesse "
					+ "sovrascrivere il metodo instantiateNewRes a livello di servizio.";
			log.error(METHOD_NAME, msg, e);
			throw new IllegalArgumentException(msg, e);
		} catch (IllegalAccessException e) {
			String msg = "Errore instanziamento automatico serviceResponse. ";
			log.error(METHOD_NAME, msg, e);
			throw new IllegalArgumentException(msg, e);
		}
		
	}
	

	/**
	 * Check dei parametri di input del servzio.
	 * Check formali, di presenza e obbligatorietà, non check su db.
	 */
	protected abstract void checkServiceParams(REQ req);


}
