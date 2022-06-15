/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDTipoCon;
import it.csi.conspref.consprefbe.service.ConsDTipoConsensoRepository;
import it.csi.conspref.consprefbe.ws.model.TipoConsenso;

public class TipoConsensoConsDTipoConsensoMapper implements Mapper<TipoConsenso, ConsDTipoCon> {


	
	@Inject
	private ConsDTipoConsensoRepository consDTipoConsensoRepository;
	
	@Override
	public ConsDTipoCon to(TipoConsenso src) {
		if(src==null  || src.getCodice()==null || src.getCodice().isEmpty()) {
			return null;
		}
		
		ConsDTipoCon consDTipoConsenso = consDTipoConsensoRepository.ricercaTipoConsensoByCodice(src.getCodice());
		
		ConsDTipoCon res = new ConsDTipoCon();
		if(consDTipoConsenso !=null) {
		  res = consDTipoConsenso;
		}
		
		return res;
	}

	@Override
	public TipoConsenso from(ConsDTipoCon dest) {
		if(dest==null) {
			return null;
		}
		TipoConsenso res = new TipoConsenso();
		
		res.setCodice(dest.getTipoConsenso());
		res.setDescrizione(dest.getDescTipoCons());
		
		return res;
	}

}
