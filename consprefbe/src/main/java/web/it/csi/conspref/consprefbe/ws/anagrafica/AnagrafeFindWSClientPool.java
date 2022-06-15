/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.ws.anagrafica;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

import it.csi.aura.auraws.services.central.anagrafefind.AnagrafeFindSoap;
import it.csi.conspref.consprefbe.integration.facade.ClientPasswordCallback;
import it.csi.conspref.consprefbe.util.LogUtil;

public class AnagrafeFindWSClientPool{
	
	@Inject
    LogUtil log;
	
	private static final Charset STD_CHARSET = Charset.forName("UTF-8");
	Properties prop;
	
	public AnagrafeFindWSClientPool () {
		final String METHOD_NAME = "AnagrafeFindWSClientPool";
		try (InputStream input = getClass().getResourceAsStream("ws_service.properties")) {

			this.prop = new Properties();

			prop.load(new InputStreamReader(input, STD_CHARSET));

		} catch (IOException ex) {
			log.error(METHOD_NAME, "Errore lettura file ws_service.properties: %s", ex, ex.getMessage());
			throw new IllegalArgumentException(ex);
		}
		
	}
	
	
	public AnagrafeFindSoap getClient() {

 
		ClientPasswordCallback callback=new ClientPasswordCallback();
		callback.setUserAura(prop.getProperty("userAura"));
		callback.setPassAura(prop.getProperty("passAura"));
		
		Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION, "UsernameToken");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
        outProps.put(WSHandlerConstants.USER, prop.getProperty("userAura"));
        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, callback);
		
        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
		factory.setServiceClass(AnagrafeFindSoap.class); 
		factory.setAddress(prop.getProperty("serviceAnagrafeFindUrl"));
		factory.getOutInterceptors().add(wssOut);
		
		return (AnagrafeFindSoap) factory.create();
	} 
 
	
}