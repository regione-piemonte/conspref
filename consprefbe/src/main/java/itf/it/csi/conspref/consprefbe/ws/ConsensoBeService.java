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

import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRicevuta;
import it.csi.conspref.consprefbe.ws.msg.AcquisizioneConsensoRichiesta;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativa;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativaResponse;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoRicevuta;
import it.csi.conspref.consprefbe.ws.msg.RevocaConsensoRichiesta;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaCodificaBe;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciConsensoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.ModificaConsensoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaAsrBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaConsensoBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaConsensoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaNotificheBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaNotificheBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaSottoTipoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaStatoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaTipoBeResponse;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciInformativaRequest;
import it.csi.conspref.consprefbe.ws.msg.be.InserisciInformativaResponse;

@WebService(serviceName = "ConsensoBeService", targetNamespace = "http://consprefbe.csi.it/")
@SOAPBinding(parameterStyle=ParameterStyle.BARE)
public interface ConsensoBeService {
	    

	    @WebMethod
	    public @WebResult(name="consultaConsensoBeResponse")  ConsultaConsensoBeResponse consultaConsensoBeService(@WebParam(name = "consultaConsensoBe")  ConsultaConsensoBe req);
	    
	    @WebMethod
	    public @WebResult(name="consultaInformativaResponse")  ConsultaInformativaResponse consultaInformativaBeService(@WebParam(name = "consultaInformativa")  ConsultaInformativa req);
	    
	    @WebMethod
	    public @WebResult(name="consultaAsrBeResponse")  ConsultaAsrBeResponse consultaAsrBeService(@WebParam(name = "consultaAsrBeRequest")  ConsultaCodificaBe req);
	    
	    @WebMethod
	    public @WebResult(name="consultaStatoBeResponse")  ConsultaStatoBeResponse consultaStatoBeService(@WebParam(name = "consultaStatoBeRequest")  ConsultaCodificaBe req);
	    
	    @WebMethod
	    public @WebResult(name="consultaTipoBeResponse")  ConsultaTipoBeResponse consultaTipoBeService(@WebParam(name = "consultaTipoBeRequest")  ConsultaCodificaBe req);	
	    
	    @WebMethod
	    public @WebResult(name="consultaSottoTipoBeResponse")  ConsultaSottoTipoBeResponse consultaSottoTipoBeService(@WebParam(name = "consultaSottoTipoBeRequest")  ConsultaCodificaBe req);
	    
	    @WebMethod
	    public @WebResult(name="inserisciConsensoBeResponse")  InserisciConsensoBeResponse inserisciConsensoBeService(@WebParam(name = "inserisciConsensoBeRequest")  InserisciConsensoBe req);
	    
	    @WebMethod
	    public @WebResult(name="modificaConsensoBeResponse")  ModificaConsensoBeResponse modificaConsensoBeService(@WebParam(name = "modificaConsensoBeRequest")  ModificaConsensoBe req);
	    
	    @WebMethod
	    public @WebResult(name="consultaProfiloBeResponse") ConsultaProfiloBeResponse consultaProfiloBeService(@WebParam(name = "consultaProfiloBeRequest")  ConsultaProfiloBe req); 
	   
	    @WebMethod
	    public @WebResult(name="consultaNotificheBeResponse")  ConsultaNotificheBeResponse consultaNotificheBeService(@WebParam(name = "consultaNotificheBe")  ConsultaNotificheBe req);
	    

}
