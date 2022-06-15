/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.integration.facade;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;



public class ClientPasswordCallback implements CallbackHandler {
	private String userAura;
	private String passAura;
	private String userOpessan;
	private String passOpessan;
	private String userComuni;
	private String passComuni;
	
	
	
	public String getUserAura() {
		return userAura;
	}

	public void setUserAura(String userAura) {
		this.userAura = userAura;
	}


	public String getPassAura() {
		return passAura;
	}

	public void setPassAura(String passAura) {
		this.passAura = passAura;
	}

	public String getUserOpessan() {
		return userOpessan;
	}

	public void setUserOpessan(String userOpessan) {
		this.userOpessan = userOpessan;
	}

	public String getPassOpessan() {
		return passOpessan;
	}


	public void setPassOpessan(String passOpessan) {
		this.passOpessan = passOpessan;
	}

	public String getUserComuni() {
		return userComuni;
	}

	public void setUserComuni(String userComuni) {
		this.userComuni = userComuni;
	}

	public String getPassComuni() {
		return passComuni;
	}

	public void setPassComuni(String passComuni) {
		this.passComuni = passComuni;
	}
	


	public void handle(Callback[] callbacks)
			throws IOException, UnsupportedCallbackException
	{
		for (int i = 0; i < callbacks.length; i++)
		{
			WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
			
			if(pc.getIdentifier().equals(userAura)) {
				pc.setPassword(passAura);
			}else if(pc.getIdentifier().equals(userOpessan)) {
				pc.setPassword(passOpessan);
			}else if(pc.getIdentifier().equals(userComuni)) {
				pc.setPassword(passComuni);
			}
			
			/*if(pc.getIdentifier().equals("SANSOL")) {
				pc.setPassword("b8AsQ50V4M");
			}else if(pc.getIdentifier().equals("IOSCELGOLASALUTE")) {
				pc.setPassword("provaScelgo");
			}*/
			
		}
	}

	
	
}