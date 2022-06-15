/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDFonte;
import it.csi.conspref.consprefbe.service.ConsDFonteRepository;
import it.csi.conspref.consprefbe.ws.model.Fonte;
import it.csi.conspref.consprefbe.ws.model.TipoFonte;

public class FonteConsDFonteMapper implements Mapper<Fonte, ConsDFonte> {


	@Inject
	private ConsDFonteRepository consDFonteRepository;
	
	@Override
	public ConsDFonte to(Fonte src) {
		if(src==null  || src.getCodice()==null || src.getCodice().isEmpty()) {
			return null;
		}
		
		ConsDFonte consDFonte = consDFonteRepository.ricercaFonteByCodice(src.getCodice());
		
		if(!consDFonte.getConsDTipoFonte().getTipofonteCod().equals(src.getTipoFonte().getCodice())) {
			return null;
		}
		
		ConsDFonte res = new ConsDFonte();
		if(consDFonte !=null) {
		  res=consDFonte;
		}
		
		return res;
	}

	@Override
	public Fonte from(ConsDFonte dest) {
		if(dest==null) {
			return null;
		}
		Fonte res = new Fonte();
		
		res.setCodice(dest.getFonteCod());
		res.setDescrizione(dest.getFonteDesc());
		
		TipoFonte tipoFonte = new TipoFonte();
		tipoFonte.setCodice(dest.getConsDTipoFonte().getTipofonteCod());
		tipoFonte.setDescrizione(dest.getConsDTipoFonte().getTipofonteDesc());
		res.setTipoFonte(tipoFonte);
		
		return res;
	}

}
