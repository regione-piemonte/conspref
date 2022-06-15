/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import static it.csi.conspref.consprefbe.util.CheckCittadino.checkNotNull;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.model.Servizio;
import it.csi.conspref.consprefbe.ws.msg.InserisciServizio;
import it.csi.conspref.consprefbe.ws.msg.InserisciServizioResponse;

public class InserisciServizioService extends BaseCittadinoService<InserisciServizio, InserisciServizioResponse> {
	
	@Inject
	private ServizioBean servizioBean;

	
	@Override
	protected void checkServiceParams(InserisciServizio req) {
		checkNotNull(req.getServizio(), "DA.E54", "Servizio non valorizzato.");
		checkNotNull(req.getServizio().getCodice(), "DA.E55", "Codice servizio non valorizzato.");
		checkNotNull(req.getServizio().getDataInizioValidita(), "DA.E56", "Data inzio validità non valorizzata.");
	}
	
	@Override
	protected InserisciServizioResponse execute(InserisciServizio req,CsiLogAudit logaudit) {
		
		Servizio servizioInserito= servizioBean.inserisciServizio(req.getServizio(), req.getRichiedente().getCodiceFiscale());
		InserisciServizioResponse res = new InserisciServizioResponse();
		
		res.setServizio(servizioInserito);
		res.setEsito(RisultatoCodice.SUCCESSO);		
		
		return res;
	}

	



}
