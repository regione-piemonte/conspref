/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import static it.csi.conspref.consprefbe.util.Check.checkCf;
import static it.csi.conspref.consprefbe.util.Check.checkCondition;
import static it.csi.conspref.consprefbe.util.Check.checkListNotNull;
import static it.csi.conspref.consprefbe.util.Check.checkNotBlank;
import static it.csi.conspref.consprefbe.util.Check.checkNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFindSoap;
import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagraficiMsg;
import it.csi.aura.auraws.services.central.anagrafefind.FindProfiliAnagraficiRequest;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseService;
import it.csi.conspref.consprefbe.service.base.exception.ServiceException;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.ws.anagrafica.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefbe.ws.model.ConsensoAsrRegione;
import it.csi.conspref.consprefbe.ws.model.EsitoErrore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRicevuta;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRichiesta;


public class AcquisizioneConsensoService extends BaseService<AcquisizioneConsensoRichiesta, AcquisizioneConsensoRicevuta> {
	
	@Inject
	private ConsensoBean consensoBean;
	@Inject
	private AnagrafeFindWSClientPool anagrafeFindSoapService;
	@Inject
	private CsiLogAuditRepository csiLogAuditRepository;
	

	


	
	@Override
	protected void checkServiceParams(AcquisizioneConsensoRichiesta req) {
		
		checkNotNull(req, E_ERRORI.SERVICE_REQ_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.SERVICE_REQ_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getCfRichiedente(), E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getCfRichiedente(), E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getRequestId(), E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getRequestId(), E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.ID_REQUEST_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getCodiceServizio(), E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getCodiceServizio(), E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_SERVIZIO_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		checkNotNull(req.getCfRichiedente(),E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getCfRichiedente(),E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkCondition(checkCf(req.getCfRichiedente()), E_ERRORI.R_CF_RICHIEDENTE_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_RICHIEDENTE_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getIdAura(),E_ERRORI.R_ID_AURA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_ID_AURA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getIdAura(),E_ERRORI.R_ID_AURA_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_ID_AURA_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		

		if (req.getCfDelegato()!=null && !req.getCfDelegato().isEmpty()) {
			checkCondition(checkCf(req.getCfDelegato()), E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
			
			if(checkCodiciFiscali(req.getCfRichiedente(), req.getCfDelegato())) {
				throw new ServiceException(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CF_DELEGATO_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);
			}
		}
		
		
		if(req.getOperatore()!=null) {
			checkNotNull(req.getOperatore().getTipoOperatore(), E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotBlank(req.getOperatore().getTipoOperatore(), E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_TIPO_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotNull(req.getOperatore().getCodiceOperatore(), E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotBlank(req.getOperatore().getCodiceOperatore(), E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_OPERATORE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);

		}
				
		checkNotNull(req.getFonte().getCodiceTipoFonte(),E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getFonte().getCodiceTipoFonte(),E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_TIPO_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getFonte().getCodiceFonte(),E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getFonte().getCodiceFonte(),E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_FONTE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		
		checkNotNull(req.getDataAcquisizione(),E_ERRORI.R_DATA_ACQUISIZIONE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_DATA_ACQUISIZIONE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getDataAcquisizione(),E_ERRORI.R_DATA_ACQUISIZIONE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_DATA_ACQUISIZIONE_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		if(req.getDataAcquisizione().length()!=14) {
			throw new ServiceException(E_ERRORI.R_DATA_ACQUISIZIONE_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_DATA_ACQUISIZIONE_WRONG.getErrorCode()), RisultatoCodice.BLOCCANTE);			
		}
		
		checkNotNull(req.getCodiceTipoConsenso(),E_ERRORI.R_CODICE_TIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_TIPO_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getCodiceTipoConsenso(),E_ERRORI.R_CODICE_TIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_TIPO_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getCodiceSottotipoConsenso(),E_ERRORI.R_CODICE_SOTTOTIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_SOTTOTIPO_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getCodiceSottotipoConsenso(),E_ERRORI.R_CODICE_SOTTOTIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_CODICE_SOTTOTIPO_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotNull(req.getDescrizioneSottotipoConsenso(),E_ERRORI.R_DESC_SOTTOTIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_DESC_SOTTOTIPO_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkNotBlank(req.getDescrizioneSottotipoConsenso(),E_ERRORI.R_DESC_SOTTOTIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_DESC_SOTTOTIPO_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		checkListNotNull(req.getElencoConsensi(),E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		if(req.getElencoConsensi().isEmpty()) {
			throw new ServiceException(E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		}
		
		for(ConsensoAsrRegione car:req.getElencoConsensi()) {
			checkNotNull(car.getValoreConsenso(),E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
			checkNotBlank(car.getValoreConsenso(),E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.R_VALORE_CONSENSO_MISSING.getErrorCode()), RisultatoCodice.BLOCCANTE);
		}
		

		
	}
	
	
	@Override
	protected AcquisizioneConsensoRicevuta execute(AcquisizioneConsensoRichiesta req) throws ParseException {
		
		CsiLogAudit logaudit = saveLogAudit(req);
		
		AcquisizioneConsensoRicevuta res = new AcquisizioneConsensoRicevuta();
		

		AnagrafeFindSoap client = (AnagrafeFindSoap)anagrafeFindSoapService.getClient();
		
		FindProfiliAnagraficiRequest fp=new FindProfiliAnagraficiRequest();

		fp.setCodiceFiscale(req.getCfRichiedente());

		fp.setFlagDecesso("0");
		
		DatiAnagraficiMsg response=client.findProfiliAnagrafici(fp);
		if("-1".equals(req.getIdAura())) {

			req.setIdAura(response.getBody().getElencoProfili().getDatianagrafici().get(0).getIdProfiloAnagrafico().toString());
		}
		if(response.getBody()!=null && response.getBody().getElencoProfili()!=null 
				&& response.getBody().getElencoProfili().getDatianagrafici()!=null
				&& req.getIdAura().equals(response.getBody().getElencoProfili().getDatianagrafici().get(0).getIdProfiloAnagrafico().toString())) {
			
			consensoBean.inserisciConsensoRegione(req, logaudit);

			res.setEsito(EsitoErrore.NESSUN_ERRORE.getValue());
			
		} else {
			res.addErrore(E_ERRORI.R_ID_AURA_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_ID_AURA_WRONG.getErrorCode()),RisultatoCodice.BLOCCANTE);
			res.setEsito(EsitoErrore.BLOCCANTE.getValue());
		}
		
		
		return res;
	}
	
	
	private boolean checkCodiciFiscali(String cf_richiedente, String cf_delegato) {
		return cf_richiedente.equalsIgnoreCase(cf_delegato);
	}
	
	
	private CsiLogAudit saveLogAudit(AcquisizioneConsensoRichiesta req) {
		
		CsiLogAudit cisLogAudit = new CsiLogAudit();
		
		cisLogAudit.setDataOra(new Timestamp(new Date().getTime()));
		cisLogAudit.setIdrequest(req.getRequestId());
		cisLogAudit.setIdApp(req.getRequestId());
		cisLogAudit.setIpAddress("");
		cisLogAudit.setOperazione("operazione");
		cisLogAudit.setOggOper("oggoper");
		cisLogAudit.setUtente(req.getCfRichiedente());
		cisLogAudit.setUuid(UUID.randomUUID());
		
		csiLogAuditRepository.inserisciLog(cisLogAudit);
		
		return cisLogAudit;
	}
	
	
}
