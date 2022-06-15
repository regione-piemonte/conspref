/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.conspref.consprefbe.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

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


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConsprefService", targetNamespace = "http://consprefbe.csi.it/")
@SOAPBinding(parameterStyle=ParameterStyle.BARE)
public interface ConsprefService {

    
    @WebMethod
    public @WebResult(name="acquisizioneConsensoCittadinoResponse")  
    AcquisizioneConsensoCittadinoResponse acquisizioneConsensoCittadinoService(
    		@WebParam(name = "acquisizioneConsensoCittadino")  AcquisizioneConsensoCittadino req);
	
    @WebMethod
    public @WebResult(name="acquisizioneConsensoRicevuta")  AcquisizioneConsensoRicevuta acquisizioneConsensoService(@WebParam(name = "acquisizioneConsensoRichiesta")  AcquisizioneConsensoRichiesta req);
	
    @WebMethod
    public @WebResult(name="revocaConsensoCittadinoResponse")  RevocaConsensoCittadinoResponse revocaConsensoCittadinoService(@WebParam(name = "revocaConsensoCittadino")  RevocaConsensoCittadino req);
    
    @WebMethod
    public @WebResult(name="revocaConsensoRicevuta")  RevocaConsensoRicevuta revocaConsensoService(@WebParam(name = "revocaConsensoRichiesta")  RevocaConsensoRichiesta req);
    
    @WebMethod
    public @WebResult(name="consultaConsensoCittadinoResponse")  ConsultaConsensoCittadinoResponse consultaConsensoCittadinoService(@WebParam(name = "consultaConsensoCittadino")  ConsultaConsensoCittadino req);
    
    @WebMethod
    public @WebResult(name="consultaInformativaResponse")  ConsultaInformativaResponse consultaInformativaService(@WebParam(name = "consultaInformativa")  ConsultaInformativa req);
}
