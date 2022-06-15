/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDInformativa;
import it.csi.conspref.consprefbe.service.ConsDInformativaRepository;
import it.csi.conspref.consprefbe.ws.model.Informativa;
import it.csi.conspref.consprefbe.ws.model.SottoTipoConsenso;
import it.csi.conspref.consprefbe.ws.model.TipoConsenso;

public class InformativaConsDInformativaMapper implements Mapper<Informativa, ConsDInformativa> {


	@Inject
	private ConsDInformativaRepository consDInformativaRepository;
	
	@Override
	public ConsDInformativa to(Informativa src) {
		if(src==null  || src.getTipoConsenso()==null || src.getSottoTipoConsenso()==null) {
			return null;
		}
		
		ConsDInformativa consDInformativa = consDInformativaRepository.ricercaInformativaByTipoSottoTipo(src.getTipoConsenso().getCodice(), src.getSottoTipoConsenso().getCodice());
		
		ConsDInformativa res = new ConsDInformativa();
		if(consDInformativa !=null) {
		  res=consDInformativa;
		}
		
		return res;
	}

	
	@Override
	public Informativa from(ConsDInformativa dest) {
		if(dest==null) {
			return null;
		}
		Informativa res = new Informativa();
		
		TipoConsenso tipoConsenso = new TipoConsenso();
		tipoConsenso.setCodice(dest.getConsDTipoCon().getTipoConsenso());
		tipoConsenso.setDescrizione(dest.getConsDTipoCon().getDescTipoCons());
		res.setTipoConsenso(tipoConsenso);
		
		SottoTipoConsenso sottoTipoConsenso = new SottoTipoConsenso();
		sottoTipoConsenso.setCodice(dest.getConsDSottoTipoCon().getSottoTipoConsenso());
		sottoTipoConsenso.setDescrizione(dest.getConsDSottoTipoCon().getDescSottoTipoCons());
		res.setSottoTipoConsenso(sottoTipoConsenso);
		
		res.setSottoTipoConsenso(sottoTipoConsenso);
		res.setPdfInformativa(dest.getPdfInformativa());
		res.setDataDecorrenza(dest.getDataDecorrenza());
		res.setDataScadenza(dest.getDataScadenza());
		
		return res;
	}

}
