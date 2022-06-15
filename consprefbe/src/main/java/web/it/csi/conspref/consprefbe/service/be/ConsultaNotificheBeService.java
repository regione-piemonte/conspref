/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import static it.csi.conspref.consprefbe.util.CheckCittadino.checkCf;
import static it.csi.conspref.consprefbe.util.CheckCittadino.checkCondition;
import static it.csi.conspref.consprefbe.util.CheckCittadino.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFindSoap;
import it.csi.aura.auraws.services.central.anagrafefind.DatiAnagraficiMsg;
import it.csi.aura.auraws.services.central.anagrafefind.FindProfiliAnagraficiRequest;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsTNotificaRepository;
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.service.base.exception.ServiceExceptionCittadino;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.StatoNotificaBean;
import it.csi.conspref.consprefbe.ws.anagrafica.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.Errore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.model.StatoNotifica;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaNotificheBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaNotificheBeResponse;

public class ConsultaNotificheBeService extends BaseCittadinoService<ConsultaNotificheBe, ConsultaNotificheBeResponse> {
	
	@Inject
	private ConsTNotificaRepository consTNotificaRepository;
	
	@Inject
	private AnagrafeFindWSClientPool anagrafeFindSoapService;
	

	
	@Override
	protected void checkServiceParams(ConsultaNotificheBe req)  {
		
		checkNotNull(req.getCfCittadino(),E_ERRORI.CF_CITTADINO_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.CF_CITTADINO_MISSING.getErrorCode()));
		checkCondition(checkCf(req.getCfCittadino()), E_ERRORI.CF_CITTADINO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_CITTADINO_WRONG.getErrorCode()));
		checkNotNull(req.getFonte().getCodice(),E_ERRORI.TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.TIPO_FONTE_MISSING.getErrorCode()));
		checkNotNull(req.getFonte().getTipoFonte().getCodice(),E_ERRORI.TIPO_FONTE_MISSING.getErrorCode(),(String)properties.getProp().get(E_ERRORI.TIPO_FONTE_MISSING.getErrorCode()));
		
		
		
		if (req.getCfDelegato()!=null) {
			checkCondition(checkCf(req.getCfDelegato()), E_ERRORI.CF_DELEGATO_WRONG.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_DELEGATO_WRONG.getErrorCode()));
			
			if(checkCodiciFiscali(req.getCfCittadino(), req.getCfDelegato())) {
				throw new ServiceExceptionCittadino(E_ERRORI.CF_EQUALS.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_EQUALS.getErrorCode()));
			}
		}
		
		
	}
	
	
	@Override
	protected ConsultaNotificheBeResponse execute(ConsultaNotificheBe req,CsiLogAudit logaudit) {
		
		ConsultaNotificheBeResponse res = new ConsultaNotificheBeResponse();
		

		
		try {
			
			if(!req.getCfCittadino().isEmpty() && req.getUuid()!=null && !req.getUuid().isEmpty()) {	
				List<Object[]> listaN=consTNotificaRepository.getStatoNotifiche(req.getCfCittadino(), req.getUuid());
				res.setCfCittadino(req.getCfCittadino());
			    res.setStatoNotifica(from(listaN));
			    if (res.getStatoNotifica().isEmpty()) {
			
			    	res.addErrore(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode()));
					res.setEsito(RisultatoCodice.FALLIMENTO);
			    } else {
			    	res.setEsito(RisultatoCodice.SUCCESSO);
			    }
								
			} else {
				res.addErrore(E_ERRORI.OPERAZIONE_NON_AMMESSA.getErrorCode(), (String)properties.getProp().get(E_ERRORI.OPERAZIONE_NON_AMMESSA.getErrorCode()));
				res.setEsito(RisultatoCodice.FALLIMENTO);
			}
		} catch(Exception e){
			res.addErrore(E_ERRORI.NO_OCCURRENCE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode()));
			res.setEsito(RisultatoCodice.FALLIMENTO);
		}
		
		return res;
	}
	
	
	private boolean checkCodiciFiscali(String cf_cittadino, String cf_delegato) {
		return cf_cittadino.equalsIgnoreCase(cf_delegato);
	}


    private List<StatoNotifica> from(List<Object[]> listStatoNotificaBean) {
    	List<StatoNotifica> listStatoNotifica=new ArrayList<StatoNotifica>();
    	for (Object[] cursor: listStatoNotificaBean) {
    		StatoNotifica statoNotifica=new StatoNotifica();
    		statoNotifica.setConsId(Integer.parseInt(cursor[0].toString()));
    		statoNotifica.setUuid(cursor[1].toString());
    		statoNotifica.setCodASR(cursor[2].toString());
    		statoNotifica.setDipartimentale(cursor[3].toString());
    		statoNotifica.setStatoConsenso(cursor[4].toString());
    		statoNotifica.setStatoNotifica(cursor[5]==null?"A":cursor[5].toString());
    		statoNotifica.setStatoDecodificato(cursor[6]==null?"":cursor[6].toString());
    		listStatoNotifica.add(statoNotifica);
    	}
    	return listStatoNotifica;
    	
    }
}
