/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import static it.csi.conspref.consprefbe.util.Check.checkNotBlank;
import static it.csi.conspref.consprefbe.util.CheckCittadino.checkNotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsensoBean;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.service.base.exception.ServiceExceptionCittadino;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.ws.model.Errore;
import it.csi.conspref.consprefbe.ws.model.Informativa;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativa;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativaResponse;

public class ConsultaInformativaBeService extends BaseCittadinoService<ConsultaInformativa, ConsultaInformativaResponse> {
	
	@Inject
	private ConsensoBeBean consensoBean;
	

	
	@Override
	protected void checkServiceParams(ConsultaInformativa req) {
		
	

	}
	
	@Override
	protected ConsultaInformativaResponse execute(ConsultaInformativa req,CsiLogAudit logaudit) throws ParseException {
		
		
		List<Informativa> informative= consensoBean.ricercaInformativaValide();
		ConsultaInformativaResponse res = new ConsultaInformativaResponse();
		
		if(!informative.isEmpty()) {
			res.setInformative(informative);
		}
		else {
			List<Errore> errori=new ArrayList<>();
			errori.add(new Errore(E_ERRORI.NO_OCCURRENCE.getErrorCode(),(String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode())));
			res.setElencoErrori(errori);
		}
		res.setEsito(RisultatoCodice.SUCCESSO);
		
		return res;
	}


}
