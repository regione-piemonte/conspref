/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.ConsDOperatore;
import it.csi.conspref.consprefbe.service.ConsDOperatoreRepository;
import it.csi.conspref.consprefbe.ws.model.Operatore;

public class OperatoreConsDOperatoreMapper implements Mapper<Operatore, ConsDOperatore> {



	@Inject
	private ConsDOperatoreRepository consDOperatoreRepository;
	
	@Override
	public ConsDOperatore to(Operatore src) {
		if(src==null  || src.getCodiceOperatore()==null || src.getCodiceOperatore().isEmpty()) {
			return null;
		}
		
		ConsDOperatore consDOper = consDOperatoreRepository.ricercaOperatoreByCodice(src.getCodiceOperatore());
		
		ConsDOperatore res = new ConsDOperatore();
		if(consDOper !=null) {
		  res=consDOper;
		}
		
		return res;
	}

	@Override
	public Operatore from(ConsDOperatore dest) {
		if(dest==null) {
			return null;
		}
		Operatore res = new Operatore();
		
		res.setCodiceOperatore(dest.getCodOperatore());
		res.setTipoOperatore(dest.getTipoOperatore());
		
		return res;
	}

}
