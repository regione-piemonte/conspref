/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import java.text.ParseException;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaCodificaBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaTipoBeResponse;

public class ConsultaTipoBeService extends BaseCittadinoService<ConsultaCodificaBe, ConsultaTipoBeResponse> {
	
	@Inject
	ConsensoBeBean consenso;
	
	@Override
	protected ConsultaTipoBeResponse execute(ConsultaCodificaBe req, CsiLogAudit logaudit) throws ParseException {

		
		ConsultaTipoBeResponse response = new ConsultaTipoBeResponse();
		response.setTipiConsenso(consenso.ricercaTipiConsensoValidi());
		response.setEsito(RisultatoCodice.SUCCESSO);
		return response;
	}

	@Override
	protected void checkServiceParams(ConsultaCodificaBe req) {
		// TODO Auto-generated method stub
		
	}

}
