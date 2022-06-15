/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagrafici;
import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagraficiMsg;
import it.csi.aura.auraws.services.central.anagrafefind.FindProfiliAnagraficiRequest;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.NotificatoreUtil;
import it.csi.conspref.consprefbe.ws.anagrafica.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.EsitoErrore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciConsensoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBe;

import static it.csi.conspref.consprefbe.util.Check.*;

public class InserisciConsensoBeService extends BaseCittadinoService<InserisciConsensoBe, InserisciConsensoBeResponse> {
	@Inject
	ConsensoBeBean consensoBean;
	@Inject
    private NotificatoreUtil  notificatoreUtil;
	@Inject
	private AnagrafeFindWSClientPool anagrafeFindSoapService;	
	
	@Override
	protected InserisciConsensoBeResponse execute(InserisciConsensoBe req, CsiLogAudit logaudit)
			throws ParseException {
		
		InserisciConsensoBeResponse response = new InserisciConsensoBeResponse();
		FindProfiliAnagraficiRequest profiloRequest = new FindProfiliAnagraficiRequest();
		profiloRequest.setCodiceFiscale(req.getConsenso().getCfCittadino());
		profiloRequest.setFlagDecesso("0");
				
		DatiAnagraficiMsg responseAura = anagrafeFindSoapService.getClient().findProfiliAnagrafici(profiloRequest);
		

		if(!verificaAuraResponse(responseAura, req.getConsenso().getIdAura())) {
			response.addErrore(E_ERRORI.R_ID_AURA_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_ID_AURA_WRONG.getErrorCode()));
			response.setEsito(RisultatoCodice.BLOCCANTE);
		}else {
			Consenso consRet = consensoBean.inserisciConsenso(req.getConsenso(), logaudit, req.getRichiedente().getCodiceFiscale());			
			response.setConsenso(consRet);
			response.setEsito(RisultatoCodice.SUCCESSO);
			try {
				callGestNotificatore(responseAura.getBody().getElencoProfili().getDatianagrafici().get(0), req, consRet);
			} catch (Exception e) {
				response.addErrore(E_ERRORI.NOTIFICATORE_ERROR.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOTIFICATORE_ERROR.getErrorCode()) + e.getMessage());
			}
		}
		

		return response;
	}
	
	private boolean verificaAuraResponse(DatiAnagraficiMsg responseAura, String idAuraRichiedente) {
		boolean verificaAura = true;
			
		if(!"1".equals(responseAura.getHeader().getCodiceRitorno()) || responseAura.getBody() == null 
				|| responseAura.getBody().getElencoProfili() == null){
			verificaAura = false;
		}else if(!(responseAura.getBody().getElencoProfili().getDatianagrafici().get(0).getIdProfiloAnagrafico().toString().equals(idAuraRichiedente))) {
			verificaAura = false;
		}
		return verificaAura;
	}
	
	@Override
	protected void checkServiceParams(InserisciConsensoBe req) {
		
		
		
		checkNotNull(req, E_ERRORI.SERVICE_REQ_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.SERVICE_REQ_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkRichiedente(req.getRichiedente());
		
						
		checkCondition(! StringUtils.isEmpty(req.getConsenso().getIdAura()),E_ERRORI.R_ID_AURA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_ID_AURA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		
		checkNotNull(req.getConsenso().getInformativa(), E_ERRORI.INFORMATIVA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.INFORMATIVA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getConsenso().getInformativa().getIdInformativa(),E_ERRORI.INFORMATIVA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.INFORMATIVA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		
		if (! StringUtils.isEmpty(req.getConsenso().getCfDelegato()) ) {
			checkCondition(checkCf(req.getConsenso().getCfDelegato()), E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
			
		}
		
		
		
		if(ConsDStatoRepository.ConsDStatoEnum.R.toString().equalsIgnoreCase(req.getConsenso().getTipoStato().getCodice())) {
			checkCondition(checkCf(req.getConsenso().getTipoStato().getCodice()), E_ERRORI.OPERAZIONE_NON_AMMESSA.getErrorCode(), (String)properties.getProp().get(E_ERRORI.OPERAZIONE_NON_AMMESSA.getErrorCode()), RisultatoCodice.BLOCCANTE);
		}

				
		checkNotNull(req.getConsenso().getFonte().getCodice(),E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getConsenso().getFonte().getCodice(),E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getConsenso().getFonte().getCodice(),E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getConsenso().getFonte().getCodice(),E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);		
		
	}
	
	private void callGestNotificatore(DatiAnagrafici datiAnagraficiCitt, InserisciConsensoBe consensoReq, Consenso consensoResponse) {
		
		
	      Map<String,String> replacements = new HashMap<>();
	      		replacements.put("@DELEGANTE@", "n.a.");
	      		replacements.put("@CFDELEGATO@", consensoReq.getRichiedente().getCodiceFiscale());
	      		replacements.put("@CITTADINO@",datiAnagraficiCitt.getCognome()+" "+datiAnagraficiCitt.getNome());
	      		replacements.put("@DESCCONSENSO@",consensoResponse.getInformativa().getSottoTipoConsenso().getDescrizione());
	      		replacements.put("@DTVARIAZIONE@", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			
		try {
			if (consensoReq.getRichiedente().getCodiceFiscale().equalsIgnoreCase(consensoReq.getConsenso().getCfCittadino())) {
				notificatoreUtil.callNotificatore ("consenso", "nuovo", "cittadino", consensoReq.getConsenso().getCfCittadino(), replacements);
			} else {
				notificatoreUtil.callNotificatore ("consenso", "nuovo", "cittadinoByDelegato", consensoReq.getConsenso().getCfCittadino(), replacements);
			}
			
		} catch(Exception e) {
			log.error("InserisciConsensoBeService.callGestNotificatore", "Eccezione durante il richiamo del Notificatore Regionale:", e.getMessage());
			throw e;
		}		
	}

}
