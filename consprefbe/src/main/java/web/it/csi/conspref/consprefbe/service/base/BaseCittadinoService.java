/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.base;

import static it.csi.conspref.consprefbe.util.CheckCittadino.checkNotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.CsiLogAuditRepository;
import it.csi.conspref.consprefbe.service.base.exception.ServiceExceptionCittadino;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.LogUtil.ToLog;
import it.csi.conspref.consprefbe.util.PropertiesUtil;
import it.csi.conspref.consprefbe.ws.model.Richiedente;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.ServiceRequest;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

/**
 * Classe base dell'implementazione della business logic di un generico servizio.
 * 
 * 
 * @author Domenico Lisi
 *
 * @param <REQ> Input del servizio che estende ServiceRequest
 * @param <RES> Output del servizio che estende ServiceResponse
 */
public abstract class BaseCittadinoService<REQ extends ServiceRequest,RES extends ServiceResponse> {
	
	@Inject
	protected LogUtil log;
	@Inject
	protected PropertiesUtil properties;
	@Inject
	private CsiLogAuditRepository csiLogAuditRepository;	
	
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
			CsiLogAudit logaudit=saveLogAudit(req);
			checkServiceParams(req);
			res = execute(req,logaudit);
		} catch (Exception e) {
			res = handleException(req, e);
		} finally {
			log.logXmlTypeObject(res, "Service Response");
			log.debug(METHOD_NAME, "End service: %s. Elapsed: %s ms.", this.getClass().getSimpleName(),
					(ToLog) () -> (System.currentTimeMillis() - time));

		}
		return res;
	}
	
	@SuppressWarnings("unused")
	protected RES handleException(REQ req, Exception e) {
		final String METHOD_NAME = "handleException";
		RES res = instantiateNewRes();
		res.setEsito(RisultatoCodice.FALLIMENTO);
		
		if(e instanceof ServiceExceptionCittadino) {
			ServiceExceptionCittadino se = (ServiceExceptionCittadino)e;
			log.error(METHOD_NAME, "Service exception in service: %s. Errori: %s "+ (se.getCause()!=null?"Cause":""), se.getCause(),
					this.getClass().getSimpleName(), (ToLog) () -> {

						StringBuilder sb = new StringBuilder();
						se.getErrori().forEach(err -> {
							sb.append("\n   ");
							sb.append(err.getCodice());
							sb.append(" - ");
							sb.append(err.getDescrizione());
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
			res.addErrore(be.getCode(), be.getMessage());
		}
		else {
			if(e!=null) {
				log.error(METHOD_NAME, "Generic exception in service: %s", e, this.getClass().getSimpleName());
				String descrizione = e.getMessage() + (e.getCause()!=null?" [" + e.getCause().getMessage() + "]":"");
				res.addErrore("ERRORE", descrizione);
			}else {
				res.addErrore("ERRORE", "");
			}
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
	protected abstract RES execute(REQ req,CsiLogAudit logaudit) throws ParseException;
	
	

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

	protected void checkRichiedente(Richiedente richiedente) {
		checkNotNull(richiedente, E_ERRORI.RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.RICHIEDENTE_MISSING.getErrorCode()));
		checkNotNull(richiedente.getCodiceFiscale(), E_ERRORI.CF_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_RICHIEDENTE_MISSING.getErrorCode()));
		checkNotNull(richiedente.getServizio(), E_ERRORI.SERVIZIO_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.SERVIZIO_RICHIEDENTE_MISSING.getErrorCode()));
		checkNotNull(richiedente.getServizio().getIdRequest(), E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode()));
		checkNotNull(richiedente.getServizio().getCodice(), E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode()));
	}
	

	
	

	
	protected CsiLogAudit saveLogAudit(REQ req) {
		
		checkNotNull(req, E_ERRORI.SERVICE_REQ_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.SERVICE_REQ_MISSING.getErrorCode()));
		checkRichiedente(req.getRichiedente());
		
		CsiLogAudit cisLogAudit = new CsiLogAudit();
		
		cisLogAudit.setDataOra(new Timestamp(new Date().getTime()));
		cisLogAudit.setIdrequest(req.getRichiedente().getServizio().getIdRequest());
		cisLogAudit.setIdApp(req.getRichiedente().getServizio().getIdRequest());
		cisLogAudit.setIpAddress("");
		cisLogAudit.setOperazione("operazione");
		cisLogAudit.setOggOper("oggoper");
		cisLogAudit.setUtente(req.getRichiedente().getCodiceFiscale());
		cisLogAudit.setUuid(UUID.randomUUID());
		
		
		csiLogAuditRepository.inserisciLog(cisLogAudit);
		
		return cisLogAudit;
	}

}
