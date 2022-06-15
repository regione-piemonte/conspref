/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.DServizio;
import it.csi.conspref.consprefbe.service.mapper.ServizioDServizioMapper;
import it.csi.conspref.consprefbe.ws.model.Servizio;

@Stateless
public class ServizioBean {
	
	@Inject
	private DServizioRepository serviziRepository;
	@Inject
	private ServizioDServizioMapper servizioMapper;
	
	public Servizio inserisciServizio(Servizio servizio, String loginOperazione) {
		
		List<Servizio> servizi = ricercaServizi(servizio);
		
		if(servizi != null && !servizi.isEmpty()) {
			throw new IllegalArgumentException("Servizio già presente.");
		}
		
		DServizio dServizio = servizioMapper.to(servizio);
		
		DServizio dServizioInserito = serviziRepository.inserisciServizio(dServizio, loginOperazione);

		return servizioMapper.from(dServizioInserito);
	}
	

	
	public List<Servizio> ricercaServizi(Servizio servizio) {
		
		DServizio dServizio = servizioMapper.to(servizio);
		
		List<DServizio> listaDServizi = serviziRepository.ricercaServizi(dServizio);

		return servizioMapper.fromList(listaDServizi);
	}
	
	
}
