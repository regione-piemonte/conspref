/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.util.List;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.model.Servizio;
import it.csi.conspref.consprefbe.ws.msg.RicercaServizi;
import it.csi.conspref.consprefbe.ws.msg.RicercaServiziResponse;

public class RicercaServiziService extends BaseCittadinoService<RicercaServizi, RicercaServiziResponse> {
	
	@Inject
	private ServizioBean servizioBean;

	
	@Override
	protected void checkServiceParams(RicercaServizi req) {
	}
	
	@Override
	protected RicercaServiziResponse execute(RicercaServizi req,CsiLogAudit logaudit) {
		
		List<Servizio> serviziRicercati = servizioBean.ricercaServizi(req.getServizio());
		
		RicercaServiziResponse res = new RicercaServiziResponse();
		
		res.setEsito(RisultatoCodice.SUCCESSO);
		res.setServizi(serviziRicercati);
		
		return res;
	}

}
