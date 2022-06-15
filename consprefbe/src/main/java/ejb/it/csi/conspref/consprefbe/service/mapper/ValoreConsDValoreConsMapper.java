/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDValoreCon;
import it.csi.conspref.consprefbe.service.ConsDValoreConRepository;
import it.csi.conspref.consprefbe.ws.model.ValoreConsenso;

public class ValoreConsDValoreConsMapper implements Mapper<ValoreConsenso, ConsDValoreCon> {


	
	@Inject
	private ConsDValoreConRepository consDValoreConRepository;
	
	@Override
	public ConsDValoreCon to(ValoreConsenso src) {
		if(src==null  || src.getCodice()==null || src.getCodice().isEmpty()) {
			return null;
		}
		
		ConsDValoreCon consDValoreCon = consDValoreConRepository.ricercaValoreConsByCodice(src.getCodice());
		
		ConsDValoreCon res = new ConsDValoreCon();
		if(consDValoreCon !=null) {
		  res = consDValoreCon;
		}
		
		return res;
	}

	@Override
	public ValoreConsenso from(ConsDValoreCon dest) {
		if(dest==null) {
			return null;
		}
		ValoreConsenso res = new ValoreConsenso();
		
		res.setCodice(dest.getValoreConsenso());
		res.setDescrizione(dest.getDescConsenso());
		
		return res;
	}

}
