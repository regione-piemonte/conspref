/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.be;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jws.WebService;

import it.csi.conspref.consprefbe.service.be.ConsultaAsrBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaConsensoBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaInformativaBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaNotificheBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaProfiloBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaSottotipiBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaStatoBeService;
import it.csi.conspref.consprefbe.service.be.ConsultaTipoBeService;
import it.csi.conspref.consprefbe.service.be.InserisciConsensoBeService;
import it.csi.conspref.consprefbe.service.be.ModificaConsensoBeService;
import it.csi.conspref.consprefbe.ws.ConsensoBeService;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativa;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativaResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaAsrBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaCodificaBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaConsensoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaNotificheBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaNotificheBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaSottoTipoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaStatoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaTipoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciConsensoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciInformativaRequest;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciInformativaResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBeResponse;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;



@WebService(serviceName = "ConsensoBeService", targetNamespace = "http://consprefbe.csi.it/")
public class ConsensoBeServiceImpl implements ConsensoBeService {
	
	@Resource
	private WebServiceContext wsContext;
	
	@Inject
	private ConsultaConsensoBeService consultaConsensoBeService;
	@Inject
	private ConsultaInformativaBeService consultaInformativaService;
	@Inject
	private ConsultaAsrBeService consultaAsrBeService;
	@Inject
	private ConsultaSottotipiBeService consultaSottotipiBeService;
	@Inject
	private ConsultaTipoBeService consultaTipoBeService;
	@Inject
	private ConsultaStatoBeService consultaStatoBeService; 
	
	@Inject
	private InserisciConsensoBeService inserisciConsensoBeService; 
	
	@Inject
	private ModificaConsensoBeService modificaConsensoBeService; 
	
	@Inject
	private ConsultaProfiloBeService consultaProfiloBeService;
	
	@Inject
	private ConsultaNotificheBeService consultaNotificheBeService;
	
	
	@PostConstruct
	public void init() {
		System.out.println(">>>> ConsultaConsensoBeService inizializzato. (@Inject instead of CDIUtil.resolve)");
	}

	
	public boolean isValidUser() {
		MessageContext mctx = wsContext.getMessageContext();
		
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        
        String username = "";
        String password = "";
        if(userList!=null){
        
        	username = userList.get(0).toString();
        }
		
        if (username.equals("user_consprefbo")) {
        
        	return true;
        }else{
        
        	return false;
        }
        
	}

	@Override
	public ConsultaConsensoBeResponse consultaConsensoBeService(ConsultaConsensoBe req) {
		// TODO Auto-generated method stub
		return consultaConsensoBeService.executeService(req) ;
	}

	@Override
	public ConsultaInformativaResponse consultaInformativaBeService(ConsultaInformativa req) {
		// TODO Auto-generated method stub
		return consultaInformativaService.executeService(req);
	}



	@Override
	public ConsultaAsrBeResponse consultaAsrBeService(ConsultaCodificaBe req) {
		// TODO Auto-generated method stub
		return consultaAsrBeService.executeService(req);
	}



	@Override
	public ConsultaStatoBeResponse consultaStatoBeService(ConsultaCodificaBe req) {
		return consultaStatoBeService.executeService(req);
	}



	@Override
	public ConsultaTipoBeResponse consultaTipoBeService(ConsultaCodificaBe req) {
		return consultaTipoBeService.executeService(req);
	}



	@Override
	public ConsultaSottoTipoBeResponse consultaSottoTipoBeService(ConsultaCodificaBe req) {
		return consultaSottotipiBeService.executeService(req);
	}



	@Override
	public InserisciConsensoBeResponse inserisciConsensoBeService(InserisciConsensoBe req) {
		// TODO Auto-generated method stub
		
		return inserisciConsensoBeService.executeService(req);
	}



	@Override
	public ModificaConsensoBeResponse modificaConsensoBeService(ModificaConsensoBe req) {
		// TODO Auto-generated method stub
		return modificaConsensoBeService.executeService(req);
	}



	@Override
	public ConsultaProfiloBeResponse consultaProfiloBeService(ConsultaProfiloBe req) {
		// TODO Auto-generated method stub
		return consultaProfiloBeService.executeService(req);
	}
	

	@Override
	public ConsultaNotificheBeResponse consultaNotificheBeService(ConsultaNotificheBe req) {
		// TODO Auto-generated method stub
		return consultaNotificheBeService.executeService(req);
	}
	
}
