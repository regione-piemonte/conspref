/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.inject.Inject;

public class PropertiesUtil {
	
	private static final Charset STD_CHARSET = Charset.forName("UTF-8");

	@Inject
    LogUtil log;
	
	private Properties prop;

	public PropertiesUtil () {
		final String METHOD_NAME = "PropertiesUtil";
		try (InputStream input = getClass().getResourceAsStream("gestioneErrori.properties")) {

			this.prop = new Properties();
			prop.load(new InputStreamReader(input, STD_CHARSET));

		} catch (IOException ex) {
			log.error(METHOD_NAME, "Errore lettura file notificatore.properties: %s", ex, ex.getMessage());
			throw new IllegalArgumentException(ex);
		}
		
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	



}
