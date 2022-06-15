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
import it.csi.conspref.consprefbe.service.base.BaseCittadinoService;
import it.csi.conspref.consprefbe.service.base.exception.ServiceExceptionCittadino;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.ws.anagrafica.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefbe.ws.model.Consenso;
import it.csi.conspref.consprefbe.ws.model.Errore;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaConsensoBeResponse;

public class ConsultaConsensoBeService extends BaseCittadinoService<ConsultaConsensoBe, ConsultaConsensoBeResponse> {
	
	@Inject
	private ConsensoBeBean consensoBean;
	@Inject
	private AnagrafeFindWSClientPool anagrafeFindSoapService;
	

	
	@Override
	protected void checkServiceParams(ConsultaConsensoBe req) {
		
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
	protected ConsultaConsensoBeResponse execute(ConsultaConsensoBe req,CsiLogAudit logaudit) {
		
		ConsultaConsensoBeResponse res = new ConsultaConsensoBeResponse();
		

		AnagrafeFindSoap client = (AnagrafeFindSoap)anagrafeFindSoapService.getClient();
		FindProfiliAnagraficiRequest fp=new FindProfiliAnagraficiRequest();

		fp.setCodiceFiscale(req.getCfCittadino());
		fp.setFlagDecesso("0");
		
		try {

			DatiAnagraficiMsg response=client.findProfiliAnagrafici(fp);
			if(response.getBody()!=null && response.getBody().getElencoProfili()!=null && response.getBody().getElencoProfili().getDatianagrafici()!=null) {
				
				List<Consenso> consensi= null;
				if(!StringUtils.isEmpty(req.getCodAsr())) {
					consensi= consensoBean.ricercaConsensoValidoBe(req.getCfCittadino(), req.getIdInformativa(), req.getCodAsr());
				}else {
					consensi= consensoBean.ricercaConsensiPerIdInformativa(req.getCfCittadino(), req.getIdInformativa());
				}
				

				
				if(!CollectionUtils .isEmpty(consensi)) {
					res.setConsensi(consensi);
				}
				else {
					List<Errore> errori=new ArrayList<>();
					errori.add(new Errore(E_ERRORI.NO_OCCURRENCE.getErrorCode(),(String)properties.getProp().get(E_ERRORI.NO_OCCURRENCE.getErrorCode())));
					res.setElencoErrori(errori);
				}
				res.setEsito(RisultatoCodice.SUCCESSO);
				
				
			} else {
				res.addErrore(E_ERRORI.CF_CITTADINO_AURA_MISSING.getErrorCode(), (String)properties.getProp().get(E_ERRORI.CF_CITTADINO_AURA_MISSING.getErrorCode()));
				res.setEsito(RisultatoCodice.FALLIMENTO);
			}
		} catch(Exception e){
			res.addErrore(E_ERRORI.AURA_NOT_AVAILABLE.getErrorCode(), (String)properties.getProp().get(E_ERRORI.AURA_NOT_AVAILABLE.getErrorCode()));
			res.setEsito(RisultatoCodice.FALLIMENTO);
		}
		
		return res;
	}
	
	
	private boolean checkCodiciFiscali(String cf_cittadino, String cf_delegato) {
		return cf_cittadino.equalsIgnoreCase(cf_delegato);
	}


}
