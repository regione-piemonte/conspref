/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import it.csi.conspref.consprefbe.service.AcquisizioneConsensoCittadinoService;
import it.csi.conspref.consprefbe.service.AcquisizioneConsensoService;
import it.csi.conspref.consprefbe.service.ConsultaConsensoCittadinoService;
import it.csi.conspref.consprefbe.service.ConsultaInformativaService;
import it.csi.conspref.consprefbe.service.RevocaConsensoCittadinoService;
import it.csi.conspref.consprefbe.service.RevocaConsensoService;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoCittadino;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoCittadinoResponse;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRicevuta;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRichiesta;
import it.csi.conspref.consprefbe.ws.msg.ConsultaConsensoCittadino;
import it.csi.conspref.consprefbe.ws.msg.ConsultaConsensoCittadinoResponse;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativa;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativaResponse;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoCittadino;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoCittadinoResponse;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoRicevuta;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoRichiesta;

@InInterceptors(interceptors=
{"it.csi.conspref.consprefbe.ws.interceptors.WsSecurityInterceptorImpl",
 "org.apache.cxf.interceptor.LoggingInInterceptor"} )
@OutInterceptors(interceptors=
{"org.apache.cxf.interceptor.LoggingOutInterceptor"} )
@WebService(serviceName="ConsprefService", targetNamespace="http://consprefbe.csi.it/")
public class ConsprefServiceImpl implements ConsprefService {
	
	@Inject
	private AcquisizioneConsensoCittadinoService acquisizioneConsensoCittadinoService;
	@Inject
	private AcquisizioneConsensoService acquisizioneConsensoService;
	@Inject
	private RevocaConsensoCittadinoService revocaConsensoCittadinoService;
	@Inject
	private RevocaConsensoService revocaConsensoService;
	@Inject
	private ConsultaConsensoCittadinoService consultaConsensoCittadinoService;
	@Inject
	private ConsultaInformativaService consultaInformativaService;
	
	@PostConstruct
	public void init() {

	}


	@Override
	public AcquisizioneConsensoCittadinoResponse acquisizioneConsensoCittadinoService(AcquisizioneConsensoCittadino req) {
		return acquisizioneConsensoCittadinoService.executeService(req);
	}
	
	
	@Override
	public AcquisizioneConsensoRicevuta acquisizioneConsensoService(AcquisizioneConsensoRichiesta req) {
		return acquisizioneConsensoService.executeService(req);
	}


	@Override
	public RevocaConsensoCittadinoResponse revocaConsensoCittadinoService(RevocaConsensoCittadino req) {
		return revocaConsensoCittadinoService.executeService(req);
	}
	
	
	@Override
	public RevocaConsensoRicevuta revocaConsensoService(RevocaConsensoRichiesta req) {
		return revocaConsensoService.executeService(req);
	}

	
	@Override
	public ConsultaConsensoCittadinoResponse consultaConsensoCittadinoService(ConsultaConsensoCittadino req) {
		return consultaConsensoCittadinoService.executeService(req);
	}


	@Override
	public ConsultaInformativaResponse consultaInformativaService(ConsultaInformativa req) {
		return consultaInformativaService.executeService(req);
	}


	

}
