/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDFonte;
import it.csi.conspref.consprefbe.model.ConsDInformativa;
import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository.ConsDStatoEnum;
import it.csi.conspref.consprefbe.service.ConsDTipoConsensoRepository.ConsDTipoConsensoEnum;
import it.csi.conspref.consprefbe.service.ConsDTipoFonteRepository.ConsDTipoFonteEnum;
import it.csi.conspref.consprefbe.service.ConsDValoreConRepository.ConsDValoreConsEnum;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.service.mapper.ConsensoConsTConsensoMapper;
import it.csi.conspref.consprefbe.service.mapper.ConsensoLocalConsTConsensoMapper;
import it.csi.conspref.consprefbe.service.mapper.InformativaConsDInformativaMapper;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.PropertiesUtil;
import it.csi.conspref.consprefbe.ws.model.ASRRegione;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.ConsensoAsr;
import it.csi.conspref.consprefbe.ws.model.ConsensoAsrRegione;
import it.csi.conspref.consprefbe.ws.model.ConsensoLocal;
import it.csi.conspref.consprefbe.ws.model.Fonte;
import it.csi.conspref.consprefbe.ws.model.Informativa;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.model.SottoTipoConsenso;
import it.csi.conspref.consprefbe.ws.model.TipoASR;
import it.csi.conspref.consprefbe.ws.model.TipoConsenso;
import it.csi.conspref.consprefbe.ws.model.TipoFonte;
import it.csi.conspref.consprefbe.ws.model.TipoStato;
import it.csi.conspref.consprefbe.ws.model.ValoreConsenso;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRichiesta;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoRichiesta;

@Stateless
public class ConsensoBean {
	
	@Inject
	private PropertiesUtil properties;
	@Inject
	private ConsTConsensoRepository consensoRepository;
	@Inject
	private ConsensoConsTConsensoMapper consensoMapper;
	@Inject
	private ConsensoLocalConsTConsensoMapper consensoLocalMapper;
	@Inject
	private InformativaConsDInformativaMapper informativaMapper;
	
	
	
	public Consenso inserisciConsenso(Consenso consenso, CsiLogAudit logaudit, String loginOperazione) {
		
		for (ConsensoAsr asr : consenso.getConsensiAsr()) {
			
			 
			if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) && 
					asr.getTipoASR()==null) {
				throw new BeServiceException(E_ERRORI.COD_ASR_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_ASR_MISSING.getErrorCode()));
			}
			
			 
			if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) &&
					consenso.getFonte().getTipoFonte().getCodice().equalsIgnoreCase(ConsDTipoFonteEnum.ASR.toString()) &&
					!consenso.getFonte().getCodice().equalsIgnoreCase(asr.getTipoASR().getCodice())) { 
				throw new BeServiceException(E_ERRORI.CONSENSO_RIFIUTATO.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_RIFIUTATO.getErrorCode()));
			}
			
			
			if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.R.toString()) ) {
				TipoASR tipoAsr = new TipoASR();
				tipoAsr.setCodice("999");
				asr.setTipoASR(tipoAsr);
			}	
			
			
			ConsensoLocal consensoLocal=loadConsensoLocal(consenso,asr);
			
			List<ConsensoLocal> consensiLocal = ricercaConsensiLocal(consensoLocal);
			
			if(consensiLocal != null && !consensiLocal.isEmpty()) {
				if(consensiLocal.size()>1) {
					throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
				} else {
					ConsensoLocal consensoLocalDB=consensiLocal.get(0);
					
					if(!consensoLocalDB.getValoreConsenso().getCodice().equals(asr.getValoreConsenso().getCodice())){
					
						storicizzaConsenso(consensoLocalDB,ConsDStatoEnum.S,logaudit, loginOperazione);
					
						ConsTConsenso tConsenso = consensoLocalMapper.to(consensoLocal);
						consensoRepository.inserisciConsenso(tConsenso, logaudit, loginOperazione);
					} else {
						throw new BeServiceException(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode()));
					}
					
				}
			} else {
			
				ConsTConsenso tConsenso = consensoLocalMapper.to(consensoLocal);
				consensoRepository.inserisciConsenso(tConsenso, logaudit, loginOperazione);
			}
		
		}
		
		return consenso;
	}
	
	
	
	public void inserisciConsensoRegione(AcquisizioneConsensoRichiesta consenso, CsiLogAudit logaudit) throws ParseException {
		
		for (ConsensoAsrRegione asr : consenso.getElencoConsensi()) {
			
			 
			if(consenso.getCodiceTipoConsenso().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) && 
					(asr.getAsr()==null || asr.getAsr().getCodice()==null)) {
				throw new BeServiceException(E_ERRORI.R_CODICE_ASR_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_ASR_MISSING.getErrorCode()));
			}
			
			 
			if(consenso.getCodiceTipoConsenso().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) &&
					consenso.getFonte().getCodiceTipoFonte().equalsIgnoreCase(ConsDTipoFonteEnum.ASR.toString()) &&
					!consenso.getFonte().getCodiceFonte().equalsIgnoreCase(asr.getAsr().getCodice())) { 
				throw new BeServiceException(E_ERRORI.R_CODICE_ASR_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_ASR_WRONG.getErrorCode()));
			}
			
			
			if(consenso.getCodiceTipoConsenso().equalsIgnoreCase(ConsDTipoConsensoEnum.R.toString()) ) {
				ASRRegione asrRegione = new ASRRegione();
				asrRegione.setCodice("999");
				asr.setAsr(asrRegione);
			}	
			
			
			ConsensoLocal consensoLocal=loadConsensoLocalRegione(consenso,asr);
			consensoLocal.setUuid(logaudit.getUuid());
			
			List<ConsensoLocal> consensiLocal = ricercaConsensiLocal(consensoLocal);
			
			if(consensiLocal != null && !consensiLocal.isEmpty()) {
				if(consensiLocal.size()>1) {
					throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
				} else {
					ConsensoLocal consensoLocalDB=consensiLocal.get(0);
					
					if(!consensoLocalDB.getValoreConsenso().getCodice().equals(asr.getValoreConsenso())){
					
						storicizzaConsenso(consensoLocalDB,ConsDStatoEnum.S,logaudit, consenso.getCfRichiedente());
					
						ConsTConsenso tConsenso = consensoLocalMapper.to(consensoLocal);
						consensoRepository.inserisciConsenso(tConsenso, logaudit, consenso.getCfRichiedente());
					} else {
						throw new BeServiceException(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode()));
					}
					
				}
			} else {
			
				ConsTConsenso tConsenso = consensoLocalMapper.to(consensoLocal);
				consensoRepository.inserisciConsenso(tConsenso, logaudit, consenso.getCfRichiedente());
			}
		
		}
	}
	
	
	
	public Consenso revocaConsenso(Consenso consenso, CsiLogAudit logaudit, String loginOperazione) {
		
		for (ConsensoAsr asr : consenso.getConsensiAsr()) {
			
			 
			if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) && 
					asr.getTipoASR()==null) {
				throw new BeServiceException(E_ERRORI.COD_ASR_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.COD_ASR_MISSING.getErrorCode()));
			}
			
			 
			if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) &&
					consenso.getFonte().getTipoFonte().getCodice().equalsIgnoreCase(ConsDTipoFonteEnum.ASR.toString()) &&
					!consenso.getFonte().getCodice().equalsIgnoreCase(asr.getTipoASR().getCodice())) { 
				throw new BeServiceException(E_ERRORI.CONSENSO_RIFIUTATO.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_RIFIUTATO.getErrorCode()));
			}
			
			
			if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.R.toString()) ) {
				TipoASR tipoAsr = new TipoASR();
				tipoAsr.setCodice("999");
				asr.setTipoASR(tipoAsr);
			}	
			
			
			ValoreConsenso vc=new ValoreConsenso();
			vc.setCodice(ConsDValoreConsEnum.NO.name());
			asr.setValoreConsenso(vc);
			
			ConsensoLocal consensoLocal=loadConsensoLocal(consenso,asr);
			
			List<ConsensoLocal> consensiLocal = ricercaConsensiLocal(consensoLocal);
			
			if(consensiLocal != null && !consensiLocal.isEmpty()) {
				if(consensiLocal.size()>1) {
					throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
				}
				else {
					
					ConsensoLocal consensoLocalDB=consensiLocal.get(0);
					storicizzaConsenso(consensoLocalDB,ConsDStatoEnum.S,logaudit, loginOperazione);
					
					ConsTConsenso tConsenso = consensoLocalMapper.to(consensoLocal);
					consensoRepository.inserisciConsenso(tConsenso, logaudit, loginOperazione);
				}
			} else {
				throw new BeServiceException(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode()));
			}
		}
	
		return consenso;
	}
	
	
	public void revocaConsensoRegione(RevocaConsensoRichiesta consenso, CsiLogAudit logaudit) throws ParseException {
		
		for (ASRRegione asr : consenso.getElencoAsr()) {
			
			 
			if(asr==null || asr.getCodice()==null) {
				throw new BeServiceException(E_ERRORI.R_CODICE_ASR_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_ASR_MISSING.getErrorCode()));
			}
			
			 
			if(consenso.getCodiceTipoConsenso().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) &&
					consenso.getFonte().getCodiceTipoFonte().equalsIgnoreCase(ConsDTipoFonteEnum.ASR.toString()) &&
					!consenso.getFonte().getCodiceFonte().equalsIgnoreCase(asr.getCodice())) { 
				throw new BeServiceException(E_ERRORI.R_CODICE_ASR_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_ASR_WRONG.getErrorCode()));
			}
			
			
			if(consenso.getCodiceTipoConsenso().equalsIgnoreCase(ConsDTipoConsensoEnum.R.toString()) ) {
				ASRRegione asrRegione = new ASRRegione();
				asrRegione.setCodice("999");
			}	
			
			
			ConsensoLocal consensoLocal = loadConsensoRevocaLocalRegione(consenso,asr);
			consensoLocal.setUuid(logaudit.getUuid());
			
			List<ConsensoLocal> consensiLocal = ricercaConsensiLocal(consensoLocal);
			
			if(consensiLocal != null && !consensiLocal.isEmpty()) {
				if(consensiLocal.size()>1) {
					throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
				}
				else {
					
					ConsensoLocal consensoLocalDB=consensiLocal.get(0);
					storicizzaConsenso(consensoLocalDB,ConsDStatoEnum.S,logaudit, consenso.getCfRichiedente());
					
					ValoreConsenso valCon = new ValoreConsenso();
					valCon.setCodice(ConsDValoreConsEnum.NO.toString());
					consensoLocal.setValoreConsenso(valCon);
					ConsTConsenso tConsenso = consensoLocalMapper.to(consensoLocal);
					consensoRepository.inserisciConsenso(tConsenso, logaudit, consenso.getCfRichiedente());
				}
			} else {
				throw new BeServiceException(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode()));
			}
		}
	
	}
	
	
	public List<Consenso> consultaConsenso(String cfCittadino,String cfDelegato,Operatore operatore,Fonte fonte,Date dataAcquisizione,String codiceTipoConsenso,String codiceSottotipoConsenso,List<String> statiConsenso) {
		
		ConsDFonte consDFonte=consensoRepository.getFonte(fonte);
		ConsDInformativa consDInformativa=consensoRepository.getInformativa(codiceTipoConsenso,codiceSottotipoConsenso);
		List<ConsTConsenso> listaTConsenso=null;
		if(consDFonte != null) {
			listaTConsenso = consensoRepository.ricercaConsenso(cfCittadino,cfDelegato,operatore,consDFonte.getFonteId(),consDInformativa.getDInformativaId(),dataAcquisizione,codiceTipoConsenso,codiceSottotipoConsenso,statiConsenso);
		}

		return consensoMapper.fromList(listaTConsenso);
	}
	
	public List<Informativa> consultaInformativa(String codiceTipoConsenso,String codiceSottotipoConsenso,String stringaDataRiferimento) throws ParseException {
		
		Date dataRiferimento;
		
		if(stringaDataRiferimento == null) {
			dataRiferimento=new Date();
		} else {
			dataRiferimento = convertFormatDate(stringaDataRiferimento);
		}
		List<ConsDInformativa> listaTInformativa = consensoRepository.ricercaInformativa(codiceTipoConsenso,codiceSottotipoConsenso,dataRiferimento);

		return informativaMapper.fromList(listaTInformativa);
	}
	
	private List<ConsensoLocal> ricercaConsensiLocal(ConsensoLocal consenso) {
		
		ConsTConsenso tConsenso = consensoLocalMapper.to(consenso);
		
		List<ConsTConsenso> listaTConsenso = consensoRepository.ricercaConsensoLocal(tConsenso);

		return consensoLocalMapper.fromList(listaTConsenso);
	}
	
	private ConsensoLocal loadConsensoLocal(Consenso consenso,ConsensoAsr asr) {
		ConsensoLocal consensoLocal=new ConsensoLocal();
			
		consensoLocal.setCfCittadino(consenso.getCfCittadino());
		consensoLocal.setCfDelegato(consenso.getCfDelegato());
		consensoLocal.setNome(consenso.getNome());
		consensoLocal.setCognome(consenso.getCognome());
		consensoLocal.setDataAcquisizione(consenso.getDataAcquisizione());
		consensoLocal.setFonte(consenso.getFonte());
		consensoLocal.setIdAura(consenso.getIdAura());
		consensoLocal.setInformativa(consenso.getInformativa());
		consensoLocal.setOperatore(consenso.getOperatore());
		consensoLocal.setTipoASR(asr.getTipoASR());
		consensoLocal.setTipoStato(consenso.getTipoStato());
		consensoLocal.setValoreConsenso(asr.getValoreConsenso());
		consensoLocal.setUuid(consenso.getUuid());
		
		return consensoLocal;
		
	}
	
	
	private ConsensoLocal loadConsensoLocalRegione(AcquisizioneConsensoRichiesta consenso,ConsensoAsrRegione asr) throws ParseException {
		ConsensoLocal consensoLocal=new ConsensoLocal();
			
		consensoLocal.setCfCittadino(consenso.getCfRichiedente());
		consensoLocal.setCfDelegato(consenso.getCfDelegato());

		
		consensoLocal.setDataAcquisizione(convertFormatDate(consenso.getDataAcquisizione()));
		
		Fonte fonte = new Fonte();
		fonte.setCodice(consenso.getFonte().getCodiceFonte());
		TipoFonte tipoFonte = new TipoFonte();
		tipoFonte.setCodice(consenso.getFonte().getCodiceTipoFonte());
		fonte.setTipoFonte(tipoFonte);
		consensoLocal.setFonte(fonte);
		
		consensoLocal.setIdAura(consenso.getIdAura());
		
		Informativa info = new Informativa();
		TipoConsenso tipoCon = new TipoConsenso();
		tipoCon.setCodice(consenso.getCodiceTipoConsenso());
		info.setTipoConsenso(tipoCon);
		SottoTipoConsenso sottoTipoCon = new SottoTipoConsenso();
		sottoTipoCon.setCodice(consenso.getCodiceSottotipoConsenso());
		sottoTipoCon.setDescrizione(consenso.getDescrizioneSottotipoConsenso());
		info.setSottoTipoConsenso(sottoTipoCon);
		consensoLocal.setInformativa(info);
		
		consensoLocal.setOperatore(consenso.getOperatore());
		
		TipoASR tipoAsr = new TipoASR();
		tipoAsr.setCodice(asr.getAsr().getCodice());
		consensoLocal.setTipoASR(tipoAsr);

		TipoStato tipoStato = new TipoStato();
		tipoStato.setCodice(ConsDStatoEnum.A.toString());
		consensoLocal.setTipoStato(tipoStato);
		
		ValoreConsenso valCon = new ValoreConsenso();
		valCon.setCodice(asr.getValoreConsenso());
		consensoLocal.setValoreConsenso(valCon);

		return consensoLocal;
		
	}
	
	
	private ConsensoLocal loadConsensoRevocaLocalRegione(RevocaConsensoRichiesta consenso,ASRRegione asr) throws ParseException {
		ConsensoLocal consensoLocal=new ConsensoLocal();
			
		consensoLocal.setCfCittadino(consenso.getCfRichiedente());
		consensoLocal.setCfDelegato(consenso.getCfDelegato());

		
		consensoLocal.setDataAcquisizione(convertFormatDate(consenso.getDataAcquisizione()));
		
		Fonte fonte = new Fonte();
		fonte.setCodice(consenso.getFonte().getCodiceFonte());
		TipoFonte tipoFonte = new TipoFonte();
		tipoFonte.setCodice(consenso.getFonte().getCodiceTipoFonte());
		fonte.setTipoFonte(tipoFonte);
		consensoLocal.setFonte(fonte);
		
		consensoLocal.setIdAura(consenso.getIdAura());
		
		Informativa info = new Informativa();
		TipoConsenso tipoCon = new TipoConsenso();
		tipoCon.setCodice(consenso.getCodiceTipoConsenso());
		info.setTipoConsenso(tipoCon);
		SottoTipoConsenso sottoTipoCon = new SottoTipoConsenso();
		sottoTipoCon.setCodice(consenso.getCodiceSottotipoConsenso());
		sottoTipoCon.setDescrizione(consenso.getDescrizioneSottotipoConsenso());
		info.setSottoTipoConsenso(sottoTipoCon);
		consensoLocal.setInformativa(info);
		
		consensoLocal.setOperatore(consenso.getOperatore());
		
		TipoASR tipoAsr = new TipoASR();
		tipoAsr.setCodice(asr.getCodice());
		consensoLocal.setTipoASR(tipoAsr);

		TipoStato tipoStato = new TipoStato();
		tipoStato.setCodice(ConsDStatoEnum.R.toString());
		consensoLocal.setTipoStato(tipoStato);
		
		ValoreConsenso valCon = new ValoreConsenso();
		consensoLocal.setValoreConsenso(valCon);

		return consensoLocal;
		
	}
	
	
	private void storicizzaConsenso(ConsensoLocal consensoLocal,ConsDStatoEnum stato,CsiLogAudit logaudit, String loginOperazione) {
		ConsTConsenso tConsensoStor = consensoLocalMapper.to(consensoLocal);
		consensoRepository.aggiornaConsenso(tConsensoStor,stato, logaudit, loginOperazione);
	}
	
	
	private Timestamp convertFormatDate(String data) throws ParseException {
		SimpleDateFormat formatActionDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		String dd=data.substring(6, 8);
		String mm=data.substring(4, 6);
		String yyyy=data.substring(0, 4);
		String HH=data.substring(8, 10);
		String min=data.substring(10,12);
		String ss=data.substring(12,data.length());
		
		Calendar dataAcq = new GregorianCalendar(Integer.parseInt(yyyy),Integer.parseInt(mm),Integer.parseInt(dd),Integer.parseInt(HH),Integer.parseInt(min),Integer.parseInt(ss));
		return new Timestamp(formatActionDate.parse(formatActionDate.format(dataAcq.getTime())).getTime());
	}
	
}
