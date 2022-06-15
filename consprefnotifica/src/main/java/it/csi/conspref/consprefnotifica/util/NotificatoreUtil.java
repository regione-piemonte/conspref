/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

@Component
public class NotificatoreUtil {
	
	private static final Logger log = LoggerFactory.getLogger(NotificatoreUtil.class);
	private static final Charset STD_CHARSET = Charset.forName("UTF-8");
	@Value("${notificatore.enabled}")
	private boolean isEnabled;
	
	@Value("${notificatore.endpoint}")
	private String endpoint;
	
	@Value("${notificatore.token}")
	private String token;
	
	@Value("${notificatore.timeout}")
	private int timeout;
	
	@Value("${notificatore.action.weburl}")
	private String actionWebURL;
	
	public void callNotificatore_test (String cfCittadino,String title, String body, String webURL, String endPointUrl, String token, Map<String,String> replacements) {
		final String METHOD_NAME = "callNotificatore";
		log.info("Entro in "+METHOD_NAME);
		log.debug(METHOD_NAME+ " endpoint notificatore: "+ endPointUrl);
		
		try {
			URL url = new URL(endPointUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-authentication", token);

			String jsonBody = getJsonBody( title, body, cfCittadino, webURL, replacements);
			OutputStream os = conn.getOutputStream();
			os.write(jsonBody.getBytes(STD_CHARSET));
			os.flush();
	
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new IllegalStateException("Errore del servizio di notifica: HTTP error code : " + conn.getResponseCode());
			}
	
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), STD_CHARSET));
	
			String output;
			log.debug(METHOD_NAME+"Output from Server ....");
			while ((output = br.readLine()) != null) {
				log.debug(METHOD_NAME+ output);
			}
	
			conn.disconnect();
	
		} catch (MalformedURLException e) {
			log.error(METHOD_NAME, "Errore url endpoint: %s", e, e.getMessage());
			throw new IllegalStateException("Errore url endpoint: ", e);
		} catch (IOException e) {
			log.error(METHOD_NAME, "Errore richiamo servizio notificatore: %s", e, e.getMessage());
			throw new IllegalStateException("Errore richiamo servizio notificatore: ", e);
		}
		log.info("Esco da "+METHOD_NAME);
	}
	
	public void callNotificatore(String cfCittadino,String title, String body,  Map<String,String> replacements) {
		final String METHOD_NAME = "callNotificatore";

		log.info("Entro in "+METHOD_NAME+" notifichiamo al cf:"+cfCittadino);
		log.debug(METHOD_NAME+ " endpoint notificatore: "+ endpoint);
		
		if(!isEnabled) {
    		log.warn(METHOD_NAME, "Notificatore disabilitato. Per abilitarlo impostare parametro di configurazione notificatore.enabled a true.");
    		return;
    	}
		log.debug(METHOD_NAME+ " token: "+ token);
		try {
			URL url = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-authentication", token);
			conn.setConnectTimeout(timeout);

			String jsonBody = getJsonBody( title, body, cfCittadino, actionWebURL, replacements);
			OutputStream os = conn.getOutputStream();
			os.write(jsonBody.getBytes(STD_CHARSET));
			os.flush();
	
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new IllegalStateException("Errore del servizio di notifica: HTTP error code : " + conn.getResponseCode());
			}
	
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), STD_CHARSET));
	
			String output;
			log.debug(METHOD_NAME+"Output from Server ....");
			while ((output = br.readLine()) != null) {
				log.debug(METHOD_NAME+ output);
			}
	
			conn.disconnect();
	
		} catch (MalformedURLException e) {
			log.error(METHOD_NAME, "Errore url endpoint: "+e.getMessage());
			throw new IllegalStateException("Errore url endpoint: "+ e);
		} catch (IOException e) {
			log.error(METHOD_NAME, "Errore richiamo servizio notificatore:"+e.getMessage());
			throw new IllegalStateException("Errore richiamo servizio notificatore: "+ e);
		}
		log.info("Esco da "+METHOD_NAME);
	}
	
	
	private String getJsonBody (String title, String body, String cf, String webURL,Map<String,String> replacements) {
    	final String METHOD_NAME = "getJsonBody";


    	
    	NotificatoreUtil.JSONObj joRoot = new JSONObj();
		joRoot.put("uuid", UUID.randomUUID());
		
		NotificatoreUtil.JSONObj joPLoad = new JSONObj("payload", joRoot);
		joPLoad.put("id", UUID.randomUUID());
		joPLoad.put("user_id", cf);
		joPLoad.put("tag", "r_piemon,sanita,consensi,comunicazioni");
		
		NotificatoreUtil.JSONObj joEmail = new JSONObj("email", joPLoad);
		joEmail.put("subject", title);
		joEmail.put("body", replace(body, replacements));
		joEmail.put("template_id", "consensi-template.html");
		
		NotificatoreUtil.JSONObj joSms = new JSONObj("sms", joPLoad);
		joSms.put("content", "");
		
		NotificatoreUtil.JSONObj joPush = new JSONObj("push", joPLoad);
		joPush.put("title", title);
		joPush.put("body", replace(body, replacements));
		joPush.put("call_to_action", webURL);
		
		NotificatoreUtil.JSONObj joMex = new JSONObj("mex", joPLoad);
		joMex.put("title", title);
		joMex.put("body", replace(body, replacements));
		joMex.put("call_to_action", webURL);

		String jsonBody = joRoot.toString();
		
		log.debug(METHOD_NAME+ " Body:"+ jsonBody);

		return jsonBody;
	}
	
	private String replace(String text, Map<String,String> replacements) {
		for (Entry<String, String> entry : replacements.entrySet()) {
			text = text.replaceAll(entry.getKey(), entry.getValue());
		}
		return text;
	}
	
	private class JSONObj {
		private int indent = 0;
		private String name;
		private Object value;
		private List<JSONObj> elements = null;
		
		public JSONObj () {
			this.elements = new ArrayList<>();
		}
		public JSONObj (String name, JSONObj parent) {
			this.name = name;
			this.elements = new ArrayList<>();
			this.indent = parent.indent+1;
			parent.put(name, this);
		}
		private JSONObj (String name, Object value, int indent) {
			this.name = name;
			this.value = value;
			this.indent = indent;
		}
		
		public void put (String name, Object value) {
			elements.add(new JSONObj (name, value, indent+1));
		}
		
		public String toString () {
			StringBuilder sb = new StringBuilder();
			if (elements != null) {
				if (!elements.isEmpty()) {
					sb.append("{\n");
					for (Iterator<JSONObj> itr = elements.iterator(); itr.hasNext();) {
						JSONObj jsonObject = itr.next();
						sb.append(jsonObject.toString()).append(itr.hasNext()? ",\n" : "\n");
					}
					sb.append(getIndentation()).append("}");
				}
			} else {
				boolean quotation = !(value instanceof JSONObj) && !value.getClass().isArray();
				sb.append(getIndentation()).append('"').append(name).append('"').append(": ");
				if (quotation) sb.append('"');
				if (value.getClass().isArray())
					sb.append(toString((Object[])value));
				else
					sb.append(value.toString());
				if (quotation) sb.append('"');
			}
			return sb.toString();
		}
		
		private String toString (Object[] array) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < array.length; i++) {
				boolean quotation = !(array[i] instanceof JSONObj) && !array[i].getClass().isArray();
				if (quotation) sb.append('"');
				sb.append(array[i].toString());
				if (quotation) sb.append('"');
				sb.append((i<array.length-1)? "," : "");
			}
			sb.append("]");
			return sb.toString();
		}
		
		private String getIndentation() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < indent; i++) sb.append("\t");
			return sb.toString();
		}
	}
}

