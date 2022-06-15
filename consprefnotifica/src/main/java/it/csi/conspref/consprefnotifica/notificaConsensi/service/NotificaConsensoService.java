/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.conspref.Asr;
import it.csi.conspref.Errore;
import it.csi.conspref.Fonte;
import it.csi.conspref.Operatore;
import it.csi.conspref.consprefnotifica.notificaConsensi.Consenso;
import it.csi.conspref.consprefnotifica.notificaConsensi.StatoConsensoEnum;
import it.csi.conspref.consprefnotifica.notificaConsensi.dao.ConsTConsensoRepository;
import it.csi.conspref.consprefnotifica.notificaConsensi.dao.ConsTNotificaRepository;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTConsenso;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTEndpoint;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTNotifica;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTNotificaErroreDett;
import it.csi.conspref.consprefnotifica.ws.NotificaAcquisizioneConsensoEstesaRichiesta;
import it.csi.conspref.consprefnotifica.ws.NotificaRevocaConsensoEstesaRichiesta;


@Component
public class NotificaConsensoService{
	
	private static final Logger log = LoggerFactory.getLogger(NotificaConsensoService.class);
	
	@Autowired
	ConsTConsensoRepository consensoRepository;
	
	
	@Autowired
	ConsTNotificaRepository notificaRepository;
	
	public List<ConsTEndpoint> ricercaEndpointPerAsr(String codAsr) throws Exception{
		log.info("Entro in NotificaConsensoService.ricercaEndpointPerAsr ricerco per codAsr"+codAsr);
		List<ConsTEndpoint> endpoints = consensoRepository.ricercaEndpointPerAsr(codAsr);
		if(endpoints==null || endpoints.isEmpty()) {			
			throw new Exception("Errore bloccante: nessun endpoint trovato!");
		}
		log.info("Esco da NotificaConsensoService.ricercaEndpointPerAsr");
		return endpoints;
	}
	
	public List<ConsTNotifica> ricercaNotificheDaInviare(String startDate) throws Exception{
		log.info("Entro in NotificaConsensoService.ricercaNotificheDaInviare ");
		List<ConsTNotifica> notifiche = notificaRepository.ricercaNotificheDaInviare(startDate);
		log.info("Esco da NotificaConsensoService.ricercaNotificheDaInviare");
		return notifiche;
	}


	public List<Consenso> ricercaConsensiDaNotificare(String filtroPerAzienda) throws Exception {
		
		List<ConsTConsenso> tconsensi = consensoRepository.ricercaConsensiDaNotificarePerAzienda(filtroPerAzienda);
		if(tconsensi==null || tconsensi.isEmpty()) {			
			return null;
		}
		
		List<ConsTEndpoint> endpoints = consensoRepository.ricercaAllEndpointurl();
		if(endpoints==null || endpoints.isEmpty()) {			
			throw new Exception("Errore bloccante: nessun endpoint trovato!");
		}
		
		
		List<Consenso> consensi = new ArrayList<Consenso>();
		for (ConsTConsenso tconsenso : tconsensi) {
			
			Consenso consenso = new Consenso();
			
			consenso.setConsId(tconsenso.getConsId());
			consenso.setRequestId(tconsenso.getCsiLogAudit().getIdrequest());
			consenso.setCodiceServizio(tconsenso.getCsiLogAudit().getIdApp());
			consenso.setStato(tconsenso.getConsDStato().getTipoStato());
			consenso.setCfRichiedente(tconsenso.getCfCittadino());
			consenso.setCfDelegato(tconsenso.getCfDelegato());
			consenso.setIdAura(tconsenso.getIdAura());
			//consensosetNome(src.getNome());
			//consensosetCognome(src.getCognome());
			consenso.setDataAcquisizione(tconsenso.getDataAcquisizione());
			consenso.setdInformativaId(tconsenso.getConsDInformativa().getDInformativaId());
						
			
			Operatore operatore = new Operatore();
			// FIXME: attenzione perchè l'operatore da passare in request è null sul db
			if(tconsenso.getConsDOperatore()!=null) {
				operatore.setCodiceOperatore(tconsenso.getConsDOperatore().getCodOperatore());
				operatore.setTipoOperatore(tconsenso.getConsDOperatore().getTipoOperatore());
			}
			consenso.setOperatore(operatore);
			
			Fonte fonte = new Fonte();
			fonte.setCodiceFonte(tconsenso.getConsDFonte().getFonteCod());
			fonte.setCodiceTipoFonte(tconsenso.getConsDFonte().getConsDTipoFonte().getTipofonteCod());
			consenso.setFonte(fonte);
			
			consenso.setCodiceTipoConsenso(tconsenso.getConsDInformativa().getConsDTipoCon().getTipoConsenso());
			consenso.setCodiceSottotipoConsenso(tconsenso.getConsDInformativa().getConsDSottoTipoCon().getSottoTipoConsenso());
			consenso.setDescrizioneSottotipoConsenso(tconsenso.getConsDInformativa().getConsDSottoTipoCon().getDescSottoTipoCons());
			
			if(consenso.getStato().equalsIgnoreCase(StatoConsensoEnum.ATTIVO.getCodice())) {
				consenso.setValoreConsenso(tconsenso.getConsDValoreCon().getValoreConsenso());
			}
			
			Asr asr = new Asr();
			asr.setCodice(tconsenso.getConsDAsr().getCodAsr());
			consenso.setAsr(asr);
			
			for (ConsTEndpoint endpoint : endpoints) {
				
				if(endpoint.getConsDAsr().getCodAsr().equalsIgnoreCase(asr.getCodice())) {
					consenso.setEndpointAsr(endpoint.getEndpUrl());
					break;
				}
			}


			consensi.add(consenso);
			
		}
		
		return consensi;
	}
	
	public List<NotificaAcquisizioneConsensoEstesaRichiesta> ricercaConsensiAcquisitiDaNotificare() throws Exception {
		
		List<ConsTConsenso> tconsensi = consensoRepository.ricercaConsensiDaNotificare(StatoConsensoEnum.ATTIVO.getCodice());
		if(tconsensi==null || tconsensi.isEmpty()) {			
			return null;
		}
		
		List<ConsTEndpoint> endpoints = consensoRepository.ricercaAllEndpointurl();
		if(endpoints==null || endpoints.isEmpty()) {			
			throw new Exception("Errore bloccante: nessun endpoint trovato!");
		}
		
		
		List<NotificaAcquisizioneConsensoEstesaRichiesta> consensi = new ArrayList<NotificaAcquisizioneConsensoEstesaRichiesta>();
		for (ConsTConsenso tconsenso : tconsensi) {
			
			NotificaAcquisizioneConsensoEstesaRichiesta req = new NotificaAcquisizioneConsensoEstesaRichiesta();
			
		
			req.setRequestId(tconsenso.getCsiLogAudit().getIdrequest());
			req.setCodiceServizio(tconsenso.getCsiLogAudit().getIdApp());
			req.setCfRichiedente(tconsenso.getCfCittadino());
			req.setCfDelegato(tconsenso.getCfDelegato());
			req.setIdAura(tconsenso.getIdAura());

			req.setDataAcquisizione(getDataAcquisizioneFormatted(tconsenso.getDataAcquisizione()));
						
			
			Operatore operatore = new Operatore();
	
			if(tconsenso.getConsDOperatore()!=null) {
				operatore.setCodiceOperatore(tconsenso.getConsDOperatore().getCodOperatore());
				operatore.setTipoOperatore(tconsenso.getConsDOperatore().getTipoOperatore());
			}
			req.setOperatore(operatore);
			
			Fonte fonte = new Fonte();
			fonte.setCodiceFonte(tconsenso.getConsDFonte().getFonteCod());
			fonte.setCodiceTipoFonte(tconsenso.getConsDFonte().getConsDTipoFonte().getTipofonteCod());
			req.setFonte(fonte);
			
			req.setCodiceTipoConsenso(tconsenso.getConsDInformativa().getConsDTipoCon().getTipoConsenso());
			req.setCodiceSottotipoConsenso(tconsenso.getConsDInformativa().getConsDSottoTipoCon().getSottoTipoConsenso());
			req.setDescrizioneSottotipoConsenso(tconsenso.getConsDInformativa().getConsDSottoTipoCon().getDescSottoTipoCons());
			req.setValoreConsenso(tconsenso.getConsDValoreCon().getValoreConsenso());
			
			Asr asr = new Asr();
			asr.setCodice(tconsenso.getConsDAsr().getCodAsr());
			req.setAsr(asr);
			
			for (ConsTEndpoint endpoint : endpoints) {
				
				if(endpoint.getConsDAsr().getCodAsr().equalsIgnoreCase(asr.getCodice())) {
					req.setEndpointAsr(endpoint.getEndpUrl());
					break;
				}
			}


			consensi.add(req);
			
		}
		
		return consensi;
	}
	
	
	
	public List<NotificaRevocaConsensoEstesaRichiesta> ricercaConsensiRevocatiDaNotificare() throws Exception {
		
		List<ConsTConsenso> tconsensi = consensoRepository.ricercaConsensiDaNotificare(StatoConsensoEnum.REVOCATO.getCodice());
		if(tconsensi==null || tconsensi.isEmpty()) {			
			return null;
		}
		
		List<ConsTEndpoint> endpoints = consensoRepository.ricercaAllEndpointurl();
		if(endpoints==null || endpoints.isEmpty()) {			
			throw new Exception("Errore bloccante: nessun endpoint trovato!");
		}
		
		
		List<NotificaRevocaConsensoEstesaRichiesta> consensi = new ArrayList<NotificaRevocaConsensoEstesaRichiesta>();
		for (ConsTConsenso tconsenso : tconsensi) {
			
			NotificaRevocaConsensoEstesaRichiesta req = new NotificaRevocaConsensoEstesaRichiesta();
			
			/**
		    "requestId",
		    "codiceServizio",
		    "cfRichiedente",
		    "idAura",
		    "cfDelegato",
		    "operatore",
		    "fonte",
		    "dataAcquisizione",
		    "codiceTipoConsenso",
		    "codiceSottotipoConsenso",
		    "descrizioneSottotipoConsenso",
		    "asr"
			*/
			req.setRequestId(tconsenso.getCsiLogAudit().getIdrequest());
			req.setCodiceServizio(tconsenso.getCsiLogAudit().getIdApp());
			req.setCfRichiedente(tconsenso.getCfCittadino());
			req.setCfDelegato(tconsenso.getCfDelegato());
			req.setIdAura(tconsenso.getIdAura());
			req.setDataAcquisizione(getDataAcquisizioneFormatted(tconsenso.getDataAcquisizione()));
						
			
			Operatore operatore = new Operatore();
			operatore.setCodiceOperatore(tconsenso.getConsDOperatore().getCodOperatore());
			operatore.setTipoOperatore(tconsenso.getConsDOperatore().getTipoOperatore());
			req.setOperatore(operatore);
			
			Fonte fonte = new Fonte();
			fonte.setCodiceFonte(tconsenso.getConsDFonte().getFonteCod());
			fonte.setCodiceTipoFonte(tconsenso.getConsDFonte().getConsDTipoFonte().getTipofonteCod());
			req.setFonte(fonte);
			
			req.setCodiceTipoConsenso(tconsenso.getConsDInformativa().getConsDTipoCon().getTipoConsenso());
			req.setCodiceSottotipoConsenso(tconsenso.getConsDInformativa().getConsDSottoTipoCon().getSottoTipoConsenso());
			req.setDescrizioneSottotipoConsenso(tconsenso.getConsDInformativa().getConsDSottoTipoCon().getDescSottoTipoCons());
			
			Asr asr = new Asr();
			asr.setCodice(tconsenso.getConsDAsr().getCodAsr());
			req.setAsr(asr);
			
			for (ConsTEndpoint endpoint : endpoints) {
				
				if(endpoint.getConsDAsr().getCodAsr().equalsIgnoreCase(asr.getCodice())) {
					req.setEndpointAsr(endpoint.getEndpUrl());
					break;
				}
			}


			consensi.add(req);
			
		}
		
		return consensi;
	}


	@Transactional
	public void inserisciNotifica(ConsTNotifica notifica, List<Errore> errori, String esito) {
		
		notificaRepository.inserisciNotifica(notifica, mappaErrori(errori), esito);
	}
	
	private String getDataAcquisizioneFormatted(Timestamp dataAcquisizione) {

		Date date = new Date();
		date.setTime(dataAcquisizione.getTime());
		String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String formattedTime = new SimpleDateFormat("HHmmss").format(date);
		String dataAcquisizioneConvertita=formattedDate+formattedTime;
		log.info("dataAcquisizioneConvertita:"+dataAcquisizioneConvertita);
		return dataAcquisizioneConvertita;
	}
	
	
	private List<ConsTNotificaErroreDett> mappaErrori(List<Errore> errori) {
		

		List<ConsTNotificaErroreDett> tNotificaErroreDetts = new ArrayList<ConsTNotificaErroreDett>();
		
		if(errori!=null) {

			for (Errore err : errori) {
				
				ConsTNotificaErroreDett tErroreDett= new ConsTNotificaErroreDett();
;
				tErroreDett.setDataCreazione(new java.sql.Timestamp(new Date().getTime()));
				tErroreDett.setDataModifica(new java.sql.Timestamp(new Date().getTime()));
				tErroreDett.setErrdettCod(err.getCodEsito());
				tErroreDett.setErrdettDesc(err.getEsito());

				
				tNotificaErroreDetts.add(tErroreDett);
				
			}
		}
		return tNotificaErroreDetts;
	}

	
}
