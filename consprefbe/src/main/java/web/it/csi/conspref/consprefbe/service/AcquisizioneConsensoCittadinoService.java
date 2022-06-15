/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import static it.csi.conspref.consprefbe.util.CheckCittadino.checkCf;
import static it.csi.conspref.consprefbe.util.CheckCittadino.checkCondition;
import static it.csi.conspref.consprefbe.util.CheckCittadino.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFindSoap;
import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagraficiMsg;
import it.csi.aura.auraws.services.central.anagrafefind.FindProfiliAnagraficiRequest;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.service.base.exception.ServiceExceptionCittadino;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.NotificatoreUtil;
import it.csi.conspref.consprefbe.ws.anagrafica.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoCittadino;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoCittadinoResponse;


public class AcquisizioneConsensoCittadinoService extends BaseCittadinoService<AcquisizioneConsensoCittadino, AcquisizioneConsensoCittadinoResponse> {
	
	@Inject
	private ConsensoBean consensoBean;
	@Inject
    private NotificatoreUtil  notificatoreUtil;
	@Inject
	private AnagrafeFindWSClientPool anagrafeFindSoapService;


	
	@Override
	protected void checkServiceParams(AcquisizioneConsensoCittadino req) {
		
		checkNotNull(req.getConsenso().getCfCittadino(),E_ERRORI.CF_CITTADINO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.CF_CITTADINO_MISSING.getErrorCode()));
		checkCondition(checkCf(req.getConsenso().getCfCittadino()), E_ERRORI.CF_CITTADINO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_CITTADINO_WRONG.getErrorCode()));
		checkNotNull(req.getConsenso().getFonte().getCodice(),E_ERRORI.TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.TIPO_FONTE_MISSING.getErrorCode()));
		checkNotNull(req.getConsenso().getFonte().getTipoFonte().getCodice(),E_ERRORI.TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.TIPO_FONTE_MISSING.getErrorCode()));
		checkNotNull(req.getConsenso().getDataAcquisizione(),E_ERRORI.D_ACQUISIZIONE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.D_ACQUISIZIONE_MISSING.getErrorCode()));
		checkNotNull(req.getConsenso().getInformativa().getTipoConsenso().getCodice(),E_ERRORI.TIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.TIPO_CONSENSO_MISSING.getErrorCode()));
		checkNotNull(req.getConsenso().getInformativa().getSottoTipoConsenso().getCodice(),E_ERRORI.SOTTOTIPO_CONSENSO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.SOTTOTIPO_CONSENSO_MISSING.getErrorCode()));
		

		if (req.getConsenso().getCfDelegato()!=null) {
			checkCondition(checkCf(req.getConsenso().getCfDelegato()), E_ERRORI.CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_DELEGATO_WRONG.getErrorCode()));
			
			if(checkCodiciFiscali(req.getConsenso().getCfCittadino(), req.getConsenso().getCfDelegato())) {
				throw new ServiceExceptionCittadino(E_ERRORI.CF_EQUALS.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_EQUALS.getErrorCode()));
			}
		}
		
		
		
	}
	
	
	@Override
	protected AcquisizioneConsensoCittadinoResponse execute(AcquisizioneConsensoCittadino req,CsiLogAudit logaudit) {
		
		AcquisizioneConsensoCittadinoResponse res = new AcquisizioneConsensoCittadinoResponse();
		
		/**
		 * INTEGRAZIONE AURA 
		 */
		AnagrafeFindSoap client = (AnagrafeFindSoap)anagrafeFindSoapService.getClient();
		
		FindProfiliAnagraficiRequest fp=new FindProfiliAnagraficiRequest();

		fp.setCodiceFiscale(req.getConsenso().getCfCittadino());

		fp.setFlagDecesso("0");
		
		DatiAnagraficiMsg response=client.findProfiliAnagrafici(fp);
		if(response.getBody()!=null && response.getBody().getElencoProfili()!=null && response.getBody().getElencoProfili().getDatianagrafici()!=null) {
			if(req.getConsenso().getCognome()==null)
				req.getConsenso().setCognome(response.getBody().getElencoProfili().getDatianagrafici().get(0).getCognome());
			
			if(req.getConsenso().getNome()==null)
				req.getConsenso().setNome(response.getBody().getElencoProfili().getDatianagrafici().get(0).getNome());
			
			if(req.getConsenso().getIdAura()==null)
				req.getConsenso().setIdAura(response.getBody().getElencoProfili().getDatianagrafici().get(0).getIdProfiloAnagrafico().toString());
			
			Consenso consensoInserito= consensoBean.inserisciConsenso(req.getConsenso(), logaudit, req.getRichiedente().getCodiceFiscale());
			

			
			res.setEsito(RisultatoCodice.SUCCESSO);
			res.setConsenso(consensoInserito);
			
		} else {
			res.addErrore(E_ERRORI.CF_CITTADINO_AURA_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_CITTADINO_AURA_MISSING.getErrorCode()));
			res.setEsito(RisultatoCodice.FALLIMENTO);
		}
		
		
		return res;
	}


	private void callNotificatore(Consenso consensoInserito) {

		Map<String,String> replacements = new HashMap<String,String>();
		replacements.put("@CITTADINO@", consensoInserito.getNome() + " " +  consensoInserito.getCognome());
)));
		
		notificatoreUtil.callNotificatore("cittadino","nuovo","delegante", consensoInserito.getCfCittadino(), replacements);
		notificatoreUtil.callNotificatore("cittadino","nuovo","delegato",consensoInserito.getCfDelegato(), replacements);
	}
	
	
	private boolean checkCodiciFiscali(String cf_cittadino, String cf_delegato) {
		return cf_cittadino.equalsIgnoreCase(cf_delegato);
	}
	




}
