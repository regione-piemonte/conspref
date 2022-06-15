/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import it.csi.conspref.consprefbe.model.ConsDAsr;
import it.csi.conspref.consprefbe.model.ConsDInformativa;
import it.csi.conspref.consprefbe.model.ConsDOperatore;
import it.csi.conspref.consprefbe.model.ConsDSottoTipoCon;
import it.csi.conspref.consprefbe.model.ConsDStato;
import it.csi.conspref.consprefbe.model.ConsDTipoCon;
import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsDInformativaRepository;
import it.csi.conspref.consprefbe.service.ConsDOperatoreRepository;
import it.csi.conspref.consprefbe.service.ConsDSottoTipoConsensoRepository;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository.ConsDStatoEnum;
import it.csi.conspref.consprefbe.service.ConsDTipoASRRepository;
import it.csi.conspref.consprefbe.service.ConsDTipoConsensoRepository;
import it.csi.conspref.consprefbe.service.ConsDTipoConsensoRepository.ConsDTipoConsensoEnum;
import it.csi.conspref.consprefbe.service.ConsDValoreConRepository.ConsDValoreConsEnum;
import it.csi.conspref.consprefbe.service.ConsTConsensoRepository;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.service.mapper.ConsensoConsTConsensoMapper;
import it.csi.conspref.consprefbe.service.mapper.InformativaConsDInformativaMapper;
import it.csi.conspref.consprefbe.service.mapper.OperatoreConsDOperatoreMapper;
import it.csi.conspref.consprefbe.service.mapper.SottoTipoConsensoConsDSottoTipoConsensoMapper;
import it.csi.conspref.consprefbe.service.mapper.StatoConsDStatoMapper;
import it.csi.conspref.consprefbe.service.mapper.TipoASRConsDASRMapper;
import it.csi.conspref.consprefbe.service.mapper.TipoConsensoConsDTipoConsensoMapper;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.PropertiesUtil;
import it.csi.conspref.consprefbe.util.VerificaSatiValoriConsenso;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.Informativa;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.model.SottoTipoConsenso;
import it.csi.conspref.consprefbe.ws.model.TipoASR;
import it.csi.conspref.consprefbe.ws.model.TipoConsenso;
import it.csi.conspref.consprefbe.ws.model.TipoStato;
import it.csi.conspref.consprefbe.ws.model.ValoreConsenso;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBe;

@Stateless
public class ConsensoBeBean {
	
	@Inject
	private PropertiesUtil properties;
	@Inject
	private ConsTConsensoBeRepository consensoBeRepository;
	@Inject
	private ConsTConsensoRepository  consensoRepository;
	@Inject
	private ConsDTipoASRRepository tipoAsrRepository;
	@Inject
	private ConsDInformativaRepository informativaRepository;
	@Inject
	private InformativaConsDInformativaMapper informativaMapper;
	@Inject
	private ConsDStatoRepository statoRepository;
	@Inject
	private ConsDTipoConsensoRepository tipoConsensoRepository;
	@Inject
	private ConsDSottoTipoConsensoRepository sottoTipoConsensoRepository;
	@Inject
	private SottoTipoConsensoConsDSottoTipoConsensoMapper  sottoTipoConsensoConsDSottoTipoConsensoMapper;
	@Inject
	private TipoConsensoConsDTipoConsensoMapper tipoConsensoConsDTipoConsensoMapper;
	@Inject
	private StatoConsDStatoMapper statoConsDStatoMapper;
	@Inject
	private TipoASRConsDASRMapper tipoASRConsDASRMapper;
	@Inject
	private ConsensoConsTConsensoMapper consensoConsTConsensoMapper;
	@Inject
	private ConsensoConsTConsensoMapper consensoMapper;
	@Inject
	private ConsDOperatoreRepository consDOperatoreRepository; 
	@Inject
	private OperatoreConsDOperatoreMapper operatoreConsDOperatoreMapper; 
	@Inject
	protected LogUtil log;
	
	
	public List<SottoTipoConsenso> ricercaSottoTipiConsensoValidi(){
		log.debug("ricercaSottoTipiConsensoValidi", "BEGIN");
		List<ConsDSottoTipoCon> listaSottoTipiConsenso = sottoTipoConsensoRepository.ricercaSottoTipoConsensiValidi();
		
		return  sottoTipoConsensoConsDSottoTipoConsensoMapper.fromList(listaSottoTipiConsenso);
	}
	
	public List<TipoConsenso> ricercaTipiConsensoValidi(){
		log.debug("ricercaTipiConsensoValidi ", "BEGIN");
		List<ConsDTipoCon> listaTipiConsenso =  tipoConsensoRepository.ricercaTipoConsensiValidi();
		
		return tipoConsensoConsDTipoConsensoMapper.fromList(listaTipiConsenso);
	}
	
	public List<TipoStato> ricercaStatiValidi(){
		log.debug("ricercaStatiValidi ", "BEGIN");
		List<ConsDStato> listaStati = statoRepository.ricercaStatiValidi();
		return statoConsDStatoMapper.fromList(listaStati);
	}
	
	public List<TipoASR> ricercaAsrValide(){
		log.debug("ricercaAsrValide()", "BEGIN");
		List<ConsDAsr> listaAsr = tipoAsrRepository.ricercaASRValide();
		return tipoASRConsDASRMapper.fromList(listaAsr);
	}
	
	public List<Informativa> ricercaInformativaValide(){
		log.debug("ricercaInformativaValide", "BEGIN");
		
		List<ConsDInformativa> listaTInformativa = consensoBeRepository.ricercaInformativeValide();
		
		return informativaMapper.fromList(listaTInformativa);
	}
	
	
	public List<Consenso> ricercaConsensiPerIdInformativa(String cf, String idInformativa){
		log.debug("ricercaConsensiPerIdInformativa", "BEGIN");
		
		List<ConsTConsenso> listaConsensi = consensoBeRepository.ricercaConsensiPerIdInformativa(cf, idInformativa);
		
		return consensoConsTConsensoMapper.fromList(listaConsensi);
	}
	
	public Consenso inserisciConsenso(Consenso consenso, CsiLogAudit logAudit, String cfRichiedente) {
		log.debug("inserisciConsenso", "BEGIN");
		
		
		ConsDInformativa informativaById = informativaRepository.ricercaInformativaById(consenso.getInformativa().getIdInformativa());
		consenso.setInformativa(informativaMapper.from(informativaById));
		
		
		 
		if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.A.toString()) && 
				(consenso.getTipoAsr()==null || StringUtils.isBlank(consenso.getTipoAsr().getCodice()))) {
			throw new BeServiceException(E_ERRORI.R_CODICE_ASR_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.R_CODICE_ASR_MISSING.getErrorCode()));
		}
				
		
		if(consenso.getInformativa().getTipoConsenso().getCodice().equalsIgnoreCase(ConsDTipoConsensoEnum.R.toString()) ) {
			TipoASR asrRegione = new TipoASR();
			asrRegione.setCodice("999");
			consenso.setTipoAsr(asrRegione);
		}	
		
		consenso.setUuid(logAudit.getUuid());

		
		List<Consenso> consensiLocal = ricercaConsensiLocal(consenso);
		
		
		ConsTConsenso tConsensoRit = null;
		
		if(consensiLocal != null && !consensiLocal.isEmpty()) {
			throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
		
		} else {
		
			ConsTConsenso tConsenso = consensoConsTConsensoMapper.to(consenso);
			tConsensoRit = consensoRepository.inserisciConsenso(tConsenso, logAudit, consenso.getCfCittadino());
		}
		
		return consensoConsTConsensoMapper.from(tConsensoRit);
	}
	
	
	public List<Consenso> ricercaConsensoValidoBe(String cf, String idInformativa, String idAsr) {
		List<ConsTConsenso> listaConsensi = consensoRepository.ricercaConsensoValidoBe(cf, idInformativa, idAsr);
		if(listaConsensi == null) {
			throw new BeServiceException(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode())) ;
		}
		if(listaConsensi.size() > 1) {
			throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
		}
		return consensoConsTConsensoMapper.fromList(listaConsensi);
	}
	
	
	private List<Consenso> ricercaConsensiLocal(Consenso consenso) {
		
		ConsTConsenso tConsenso = consensoConsTConsensoMapper.to(consenso);
		
		List<ConsTConsenso> listaTConsenso = consensoRepository.ricercaConsensoLocalBe(tConsenso);

		return consensoConsTConsensoMapper.fromList(listaTConsenso);
	}
	
	
	public Operatore getOperatoreProfilo(String cf, String codiceOperatore, String tipoOperatore) {
		log.debug("getOperatoreProfilo", "BEGIN");
		
		ConsDOperatore operatore = consDOperatoreRepository.ricercaOperatoreByCodiceFiscale(cf, codiceOperatore, tipoOperatore);
		
		return operatoreConsDOperatoreMapper.from(operatore);
	}
	

	public Consenso modificaConsenso (ModificaConsensoBe consenso, CsiLogAudit logAudit, String cfRichiedente) {
		log.debug("modificaConsenso", "BEGIN");
		List<ConsTConsenso> listaConsensi = consensoRepository.ricercaConsensoValidoBe(consenso.getCfCittadino(), consenso.getIdInformativa().toString(), consenso.getAsr().getCodice());
		if (listaConsensi.isEmpty()) {
			throw new BeServiceException(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode()));		 
		}
		if(listaConsensi.size()>1) {
			throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
		}
		 
		
		Consenso tConsenso = consensoConsTConsensoMapper.fromList(listaConsensi).get(0);
		
		tConsenso.setCfDelegato(consenso.getCfDelegato());
		
		if(tConsenso.getTipoStato().getCodice().equalsIgnoreCase(consenso.getTipoStato().getCodice())
				&&((consenso.getValoreConsenso()!=null)&&tConsenso.getValoreConsenso().getCodice().equalsIgnoreCase(consenso.getValoreConsenso().getCodice()))) {
		
			throw new BeServiceException(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode()));
		}	
		
		
		if (VerificaSatiValoriConsenso.verificaConsensi(tConsenso.getTipoStato().getCodice(), tConsenso.getValoreConsenso().getCodice(), consenso.getTipoStato().getCodice(), consenso.getValoreConsenso().getCodice())) {
			tConsenso.setValoreConsenso(consenso.getValoreConsenso());
			tConsenso.setTipoStato(consenso.getTipoStato());
			
			tConsenso.setFonte(consenso.getFonte());
			tConsenso.setDataAcquisizione(consenso.getDataAcquisizione());
			tConsenso.setOperatore(consenso.getOperatore());
			consensoRepository.aggiornaConsenso(listaConsensi.get(0),ConsDStatoEnum.S, logAudit, cfRichiedente);
			
			Consenso resp=this.inserisciConsenso(tConsenso, logAudit, cfRichiedente);
			log.debug("modificaConsenso", "END");
			return resp;
		}	
		return tConsenso;
	}

	
	public Consenso modificaConsensoOld(ModificaConsensoBe consenso, CsiLogAudit logAudit, String cfRichiedente) {
		log.debug("modificaConsenso", "BEGIN");
		List<ConsTConsenso> listaConsensi = consensoRepository.ricercaConsensoValidoBe(consenso.getCfCittadino(), consenso.getIdInformativa().toString(), consenso.getAsr().getCodice());
		if (listaConsensi.isEmpty()) {
			throw new BeServiceException(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode()));		 
		}
		if(listaConsensi.size()>1) {
			throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT.getErrorCode()));
		}
	 
		
		Consenso tConsenso = consensoConsTConsensoMapper.fromList(listaConsensi).get(0);
		
		if(consenso.getCfDelegato()!=null && !consenso.getCfDelegato().isEmpty()) {
			tConsenso.setCfDelegato(consenso.getCfDelegato());
		}
		
		
		if(tConsenso.getTipoStato().getCodice().equalsIgnoreCase(consenso.getTipoStato().getCodice())
				&&((consenso.getValoreConsenso()!=null)&&tConsenso.getValoreConsenso().getCodice().equalsIgnoreCase(consenso.getValoreConsenso().getCodice()))) {
		
			throw new BeServiceException(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode()));
		}			
		
		if(ConsDStatoEnum.R.toString().equalsIgnoreCase(consenso.getTipoStato().getCodice())) {			
			if(tConsenso.getTipoStato().getCodice().equalsIgnoreCase(consenso.getTipoStato().getCodice())) {
				throw new BeServiceException(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CONSENSO_ALREADY_FOUND.getErrorCode()));
			} else if(ConsDValoreConsEnum.NO.toString().equalsIgnoreCase(consenso.getValoreConsenso().getCodice())){
				tConsenso.setValoreConsenso(consenso.getValoreConsenso());
			} else if(consenso.getValoreConsenso()==null||consenso.getValoreConsenso().getCodice()==null){ 
		
				ValoreConsenso v=new ValoreConsenso();
				v.setCodice(ConsDValoreConsEnum.NO.toString());
				tConsenso.setValoreConsenso(v);
			} else {
				throw new BeServiceException(E_ERRORI.VALORE_NON_PREVISTO.getErrorCode(), (String)properties.getProp().get(E_ERRORI.VALORE_NON_PREVISTO.getErrorCode()));
			}
		}
		tConsenso.setTipoStato(consenso.getTipoStato());
		
		if(ConsDStatoEnum.A.toString().equalsIgnoreCase(consenso.getTipoStato().getCodice())) {
			if(consenso.getValoreConsenso()!=null&&!consenso.getValoreConsenso().getCodice().isEmpty()) {
				tConsenso.setValoreConsenso(consenso.getValoreConsenso());
			} else {
				throw new BeServiceException(E_ERRORI.VALORE_NON_PREVISTO.getErrorCode(), (String)properties.getProp().get(E_ERRORI.VALORE_NON_PREVISTO.getErrorCode()));
			}
		}
		
		tConsenso.setFonte(consenso.getFonte());
		tConsenso.setDataAcquisizione(consenso.getDataAcquisizione());
		tConsenso.setOperatore(consenso.getOperatore());
		
		consensoRepository.aggiornaConsenso(listaConsensi.get(0),ConsDStatoEnum.S, logAudit, cfRichiedente);
		
		
		Consenso resp=this.inserisciConsenso(tConsenso, logAudit, cfRichiedente);
		log.debug("modificaConsenso", "END");
		return resp;
	}

}
