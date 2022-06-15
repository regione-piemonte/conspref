/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import java.text.ParseException;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBeResponse;

public class ConsultaProfiloBeService extends BaseCittadinoService<it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBe, ConsultaProfiloBeResponse> {
	
	@Inject
	private ConsensoBeBean consensoBean;
	
	@Override
	protected ConsultaProfiloBeResponse execute(ConsultaProfiloBe req, CsiLogAudit logaudit) throws ParseException {
		// TODO Auto-generated method stub
		
		Operatore operatore = consensoBean.getOperatoreProfilo(req.getCfOperatore(), req.getOperatore().getCodiceOperatore(), req.getOperatore().getTipoOperatore());
		
		ConsultaProfiloBeResponse res = new ConsultaProfiloBeResponse();
		
		res.setOperatore(operatore);
		res.setEsito(RisultatoCodice.SUCCESSO);
		return res;
	}

	@Override
	protected void checkServiceParams(ConsultaProfiloBe req) {
		// TODO Auto-generated method stub
		
	}

}
