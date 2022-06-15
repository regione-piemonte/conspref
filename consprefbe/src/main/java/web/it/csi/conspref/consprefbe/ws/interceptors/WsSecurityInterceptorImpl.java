/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.interceptors;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

public class WsSecurityInterceptorImpl extends WSS4JInInterceptor  {

	static private Map<String,Object> inProps = new HashMap<>();
	static {
		inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE + " " + WSHandlerConstants.TIMESTAMP);
		inProps.put(WSHandlerConstants.SIG_PROP_FILE, "/serverwssec.properties");		
		inProps.put("ws-security.encryption.username", "useReqSigCert");
	}

	public WsSecurityInterceptorImpl(String phase) {
		this();
	}
	
	public WsSecurityInterceptorImpl() {
	super(inProps);
	}

}
