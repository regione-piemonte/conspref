/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDTipoFonte;
import it.csi.conspref.consprefbe.service.ConsDTipoFonteRepository;
import it.csi.conspref.consprefbe.ws.model.TipoFonte;

public class TipoFonteConsDTipoFonteMapper implements Mapper<TipoFonte, ConsDTipoFonte> {

	
	@Inject
	private ConsDTipoFonteRepository consDTipoFonteRepository;
	
	@Override
	public ConsDTipoFonte to(TipoFonte src) {
		if(src==null  || src.getCodice()==null || src.getCodice().isEmpty()) {
			return null;
		}
		
		ConsDTipoFonte consDTipoFonte = consDTipoFonteRepository.ricercaTipoFonteByCodice(src.getCodice());
		
		ConsDTipoFonte res = new ConsDTipoFonte();
		if(consDTipoFonte !=null) {
		  res = consDTipoFonte;
		}
		
		return res;
	}

	@Override
	public TipoFonte from(ConsDTipoFonte dest) {
		if(dest==null) {
			return null;
		}
		TipoFonte res = new TipoFonte();
		
		res.setCodice(dest.getTipofonteCod());
		res.setDescrizione(dest.getTipofonteDesc());
		
		return res;
	}

}
