/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import java.sql.Timestamp;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.ws.model.ConsensoLocal;

public class ConsensoLocalConsTConsensoMapper implements Mapper<ConsensoLocal, ConsTConsenso> {

	
	@Inject
	private FonteConsDFonteMapper fonteConsDFonteMapper;
	
	@Inject
	private OperatoreConsDOperatoreMapper operatoreConsDOperatoreMapper;
	
	@Inject
	private StatoConsDStatoMapper statoConsDStatoMapper;
	
	@Inject
	private InformativaConsDInformativaMapper informativaConsDInformativaMapper;
	
	@Inject
	private ValoreConsDValoreConsMapper valoreConsDValoreConsMapper;
	
	@Inject
	private TipoASRConsDASRMapper tipoASRConsDASRMapper;
	

	
	@Override
	public ConsTConsenso to(ConsensoLocal src) {
		if(src==null) {
			return null;
		}
		ConsTConsenso res = new ConsTConsenso();
		res.setConsId(src.getConsId());
		res.setCfCittadino(src.getCfCittadino());
		res.setCfDelegato(src.getCfDelegato());
		res.setIdAura(src.getIdAura());
		res.setNome(src.getNome());
		res.setCognome(src.getCognome());
		res.setDataAcquisizione(new Timestamp(src.getDataAcquisizione().getTime()));
		
		res.setConsDOperatore(operatoreConsDOperatoreMapper.to(src.getOperatore()));
		res.setConsDFonte(fonteConsDFonteMapper.to(src.getFonte()));
		res.setConsDStato(statoConsDStatoMapper.to(src.getTipoStato()));
		res.setConsDInformativa(informativaConsDInformativaMapper.to(src.getInformativa()));
		res.setConsDValoreCon(valoreConsDValoreConsMapper.to(src.getValoreConsenso()));
		res.setConsDAsr(tipoASRConsDASRMapper.to(src.getTipoASR()));
		res.setUuid(src.getUuid());
		
		return res;
	}

	@Override
	public ConsensoLocal from(ConsTConsenso dest) {
		if(dest==null) {
			return null;
		}
		ConsensoLocal res = new ConsensoLocal();
		res.setConsId(dest.getConsId());
		res.setCfCittadino(dest.getCfCittadino());
		res.setCfDelegato(dest.getCfDelegato());
		res.setCognome(dest.getCognome());
		res.setDataAcquisizione(dest.getDataAcquisizione());
		res.setFonte(fonteConsDFonteMapper.from(dest.getConsDFonte()));
		res.setIdAura(dest.getIdAura());
		res.setInformativa(informativaConsDInformativaMapper.from(dest.getConsDInformativa()));
		res.setNome(dest.getNome());
		res.setOperatore(operatoreConsDOperatoreMapper.from(dest.getConsDOperatore()));
		res.setTipoStato(statoConsDStatoMapper.from(dest.getConsDStato()));
		res.setValoreConsenso(valoreConsDValoreConsMapper.from(dest.getConsDValoreCon()));
		res.setTipoASR(tipoASRConsDASRMapper.from(dest.getConsDAsr()));
		res.setUuid(dest.getUuid());
		
		return res;
	}

}
