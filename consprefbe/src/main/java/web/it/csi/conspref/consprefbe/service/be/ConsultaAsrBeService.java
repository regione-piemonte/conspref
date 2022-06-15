/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import java.text.ParseException;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.service.be.ConsensoBeBean;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaCodificaBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaAsrBeResponse;

public class ConsultaAsrBeService extends BaseCittadinoService<ConsultaCodificaBe, ConsultaAsrBeResponse> {
	
	@Inject
	private ConsensoBeBean consensoBean;

	@Override
	protected ConsultaAsrBeResponse execute(ConsultaCodificaBe req, CsiLogAudit logaudit) throws ParseException {
		ConsultaAsrBeResponse asrResponse = new ConsultaAsrBeResponse();
		 
		asrResponse.setAsr(consensoBean.ricercaAsrValide());
		asrResponse.setEsito(RisultatoCodice.SUCCESSO);
		return asrResponse;
	}

	@Override
	protected void checkServiceParams(ConsultaCodificaBe req) {
		// TODO Auto-generated method stub
		
	}

}
