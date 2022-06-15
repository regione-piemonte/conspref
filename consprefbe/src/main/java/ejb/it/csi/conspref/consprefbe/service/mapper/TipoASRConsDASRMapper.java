/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDAsr;
import it.csi.conspref.consprefbe.service.ConsDTipoASRRepository;
import it.csi.conspref.consprefbe.ws.model.TipoASR;

public class TipoASRConsDASRMapper implements Mapper<TipoASR, ConsDAsr> {


	
	@Inject
	private ConsDTipoASRRepository consDTipoASRRepository;
	
	@Override
	public ConsDAsr to(TipoASR src) {
		if(src==null  || src.getCodice()==null || src.getCodice().isEmpty()) {
			return null;
		}
		
		ConsDAsr consDTipoASR = consDTipoASRRepository.ricercaASRConsByCodice(src.getCodice());
		
		ConsDAsr res = new ConsDAsr();
		if(consDTipoASR !=null) {
		  res = consDTipoASR;
		}
		
		return res;
	}

	@Override
	public TipoASR from(ConsDAsr dest) {
		if(dest==null) {
			return null;
		}
		TipoASR res = new TipoASR();
		
		res.setCodice(dest.getCodAsr());
		res.setDescrizione(dest.getDescAsr());
		
		return res;
	}

}
