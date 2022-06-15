/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import static it.csi.conspref.consprefbe.util.Check.checkCf;
import static it.csi.conspref.consprefbe.util.Check.checkCondition;
import static it.csi.conspref.consprefbe.util.Check.checkNotBlank;
import static it.csi.conspref.consprefbe.util.Check.checkNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFindSoap;
import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagrafici;
import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagraficiMsg;
import it.csi.aura.auraws.services.central.anagrafefind.FindProfiliAnagraficiRequest;
import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsensoBean;
import it.csi.conspref.consprefbe.service.CsiLogAuditRepository;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.service.base.exception.ServiceException;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.util.NotificatoreUtil;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.ws.anagrafica.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBeResponse;

public class ModificaConsensoBeService extends BaseCittadinoService<ModificaConsensoBe, ModificaConsensoBeResponse> {

	@Inject
	private ConsensoBeBean consensoBeBean;
	@Inject
    private NotificatoreUtil  notificatoreUtil;
	@Inject
	private AnagrafeFindWSClientPool anagrafeFindSoapService;
	@Inject
	private CsiLogAuditRepository csiLogAuditRepository;
	
	@Override
	protected void checkServiceParams(ModificaConsensoBe req) {
		
		checkNotNull(req, E_ERRORI.SERVICE_REQ_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.SERVICE_REQ_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getRichiedente().getCodiceFiscale(), E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getRichiedente().getCodiceFiscale(), E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getRichiedente().getServizio().getIdRequest(), E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getRichiedente().getServizio().getIdRequest(), E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getRichiedente().getServizio().getCodice(), E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getRichiedente().getServizio().getCodice(), E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		checkNotNull(req.getRichiedente().getCodiceFiscale(),E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getRichiedente().getCodiceFiscale(),E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkCondition(checkCf(req.getRichiedente().getCodiceFiscale()), E_ERRORI.R_CF_RICHIEDENTE_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getIdAura(),E_ERRORI.R_ID_AURA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_ID_AURA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getIdAura(),E_ERRORI.R_ID_AURA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_ID_AURA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		
		if (req.getCfDelegato()!=null && !req.getCfDelegato().isEmpty()) {
			log.info("ModificaConsensoBeService.checkServiceParams", "cf richiedente: %s, cd delegato: %s, !cf_cittadino.equalsIgnoreCase(cf_delegato)", req.getRichiedente().getCodiceFiscale(), req.getCfDelegato());
			checkCondition(checkCf(req.getCfDelegato()), E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
			log.info("ModificaConsensoBeService.checkServiceParams", "cf richiedente: %s, cd delegato: %s, !cf_cittadino.equalsIgnoreCase(cf_delegato)", req.getRichiedente().getCodiceFiscale(), req.getCfDelegato());
			if(checkCodiciFiscaliDiversi(req.getRichiedente().getCodiceFiscale(), req.getCfDelegato())) {
				
				throw new ServiceException(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
			}
		}
		
		
		if(req.getOperatore()!=null) {
			checkNotNull(req.getOperatore().getTipoOperatore(), E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotBlank(req.getOperatore().getTipoOperatore(), E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotNull(req.getOperatore().getCodiceOperatore(), E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotBlank(req.getOperatore().getCodiceOperatore(), E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);

		}
				
		
		checkNotNull(req.getDataAcquisizione(),E_ERRORI.R_DATA_ACQUISIZIONE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_DATA_ACQUISIZIONE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		
	}
	
	@Override
	protected ModificaConsensoBeResponse execute(ModificaConsensoBe req, CsiLogAudit logaudit) throws ParseException {
		ModificaConsensoBeResponse res= new ModificaConsensoBeResponse();
		
		AnagrafeFindSoap client = (AnagrafeFindSoap)anagrafeFindSoapService.getClient();
		
		FindProfiliAnagraficiRequest fp=new FindProfiliAnagraficiRequest();

		fp.setCodiceFiscale(req.getCfCittadino());
		fp.setFlagDecesso("0");
		
		DatiAnagraficiMsg response=client.findProfiliAnagrafici(fp);
		if("-1".equals(req.getIdAura())) {
			
			req.setIdAura(response.getBody().getElencoProfili().getDatianagrafici().get(0).getIdProfiloAnagrafico().toString());
		}
		if(response.getBody()!=null && response.getBody().getElencoProfili()!=null 
				&& response.getBody().getElencoProfili().getDatianagrafici()!=null
				&& req.getIdAura().equals(response.getBody().getElencoProfili().getDatianagrafici().get(0).getIdProfiloAnagrafico().toString())) {
			
			
			Consenso consRet =consensoBeBean.modificaConsenso(req, logaudit, req.getRichiedente().getCodiceFiscale());
			res.setConsenso(consRet);
			res.setEsito(RisultatoCodice.SUCCESSO);	
			try {
				callGestNotificatore(response.getBody().getElencoProfili().getDatianagrafici().get(0), req, consRet);
			}catch(Exception e) {
					res.addErrore(E_ERRORI.NOTIFICATORE_ERROR.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOTIFICATORE_ERROR.getErrorCode()) + e.getMessage());
				}
		} else {
			res.addErrore(E_ERRORI.R_ID_AURA_WRONG.getErrorCode(), (String)(properties.getProp().get(E_ERRORI.R_ID_AURA_WRONG.getErrorCode())));
			res.setEsito(RisultatoCodice.BLOCCANTE);
		}
		
		return res;
	}
	
	
	private boolean checkCodiciFiscaliDiversi(String cf_cittadino, String cf_delegato) {
		return !cf_cittadino.equalsIgnoreCase(cf_delegato);
	}
	
	
	protected CsiLogAudit saveLogAudit(ModificaConsensoBe req) {
		
		CsiLogAudit cisLogAudit = new CsiLogAudit();
		
		cisLogAudit.setDataOra(new Timestamp(new Date().getTime()));
		cisLogAudit.setIdrequest(req.getRichiedente().getServizio().getIdRequest());
		cisLogAudit.setIdApp(req.getRichiedente().getServizio().getCodice());
		cisLogAudit.setIpAddress("");
		cisLogAudit.setOperazione("ModificaConsenso");
		cisLogAudit.setOggOper(req.getFonte().getCodice());
		cisLogAudit.setUtente(req.getRichiedente().getCodiceFiscale());
		cisLogAudit.setUuid(UUID.randomUUID());
		
		csiLogAuditRepository.inserisciLog(cisLogAudit);
		
		return cisLogAudit;
	}
	
	private void callGestNotificatore(DatiAnagrafici datiAnagraficiCitt, ModificaConsensoBe consensoReq, Consenso consensoResponse) {
			
	      Map<String,String> replacements = new HashMap<>();
	      		replacements.put("@DELEGANTE@", "n.a.");
	      		replacements.put("@CFDELEGATO@", consensoReq.getRichiedente().getCodiceFiscale());
	      		replacements.put("@CITTADINO@",datiAnagraficiCitt.getCognome()+" "+datiAnagraficiCitt.getNome());
	      		replacements.put("@DESCCONSENSO@",consensoResponse.getInformativa().getSottoTipoConsenso().getDescrizione());
	      		replacements.put("@DTVARIAZIONE@", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			
		try {
			if (consensoReq.getRichiedente().getCodiceFiscale().equalsIgnoreCase(consensoReq.getCfCittadino())) {
				notificatoreUtil.callNotificatore ("consenso", "nuovo", "cittadino", consensoReq.getCfCittadino(), replacements);				
			} else {
				notificatoreUtil.callNotificatore ("consenso", "nuovo", "cittadinoByDelegato", consensoReq.getCfCittadino(), replacements);
			}
		} catch(Exception e) {			
			log.error("ModificaConsensoBeService.callGestNotificatore", "Eccezione durante il richiamo del Notificatore Regionale:", e.getMessage());
			throw e;
		}		
	}

}
