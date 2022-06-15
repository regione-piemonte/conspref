/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.IsAlive;
import it.csi.conspref.consprefbe.ws.msg.IsAliveResponse;

public class IsAliveService extends BaseCittadinoService<IsAlive, IsAliveResponse> {

	@Override
	protected IsAliveResponse execute(IsAlive req,CsiLogAudit logaudit) {

		IsAliveResponse res = new IsAliveResponse();

		res.setEsito(RisultatoCodice.SUCCESSO);

		res.setOut("Servizio vivo e vegetale!!!");
		return res;
	}

	@Override
	protected void checkServiceParams(IsAlive req) {
		
	}

}
