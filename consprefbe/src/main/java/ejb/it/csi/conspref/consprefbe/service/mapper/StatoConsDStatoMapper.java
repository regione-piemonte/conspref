/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDStato;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository;
import it.csi.conspref.consprefbe.ws.model.TipoStato;

public class StatoConsDStatoMapper implements Mapper<TipoStato, ConsDStato> {



	@Inject
	private ConsDStatoRepository consDStatoRepository;
	
	@Override
	public ConsDStato to(TipoStato src) {
		if(src==null  || src.getCodice()==null || src.getCodice().isEmpty()) {
			return null;
		}
		
		ConsDStato consDStato = consDStatoRepository.ricercaStatoByCodice(src.getCodice());
		
		ConsDStato res = new ConsDStato();
		if(consDStato !=null) {
		  res=consDStato;
		}
		
		return res;
	}

	
	@Override
	public TipoStato from(ConsDStato dest) {
		if(dest==null) {
			return null;
		}
		TipoStato res = new TipoStato();
		
		res.setCodice(dest.getTipoStato());
		res.setDescrizione(dest.getDescStato());
		
		return res;
	}

}
