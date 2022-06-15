/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class NotificaConfiguration implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(NotificaConfiguration.class);

	private String defaultUri="https://10.66.18.125:8443/fse/1b";


	@Value("${javax.net.ssl.trustStore}")
	private String ArgTrustStore;
	
	@Value("${conspref.cert-alias:cert}")
	private String certAlias;
	
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("it.csi.conspref");

		return jaxb2Marshaller;
	}

	@Bean
	public NotificaClient notificaClient(Jaxb2Marshaller marshaller) {
		NotificaClient client = new NotificaClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;

	}

	@Bean
	public WebServiceTemplate webServiceTemplate() throws Exception {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(defaultUri);
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());

		return webServiceTemplate;
	}

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() throws Exception {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		httpComponentsMessageSender.setHttpClient(httpClient());

		return httpComponentsMessageSender;
	}

	@Bean
	public HttpClient httpClient() throws Exception {
		
	
		HttpClient clientBuilder= HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory()).build();
	
		return clientBuilder;
	}

	@Bean
	public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {
		log.info("Sono in NotificaConfiguration::sslConnectionSocketFactory");
		SSLConnectionSocketFactory socketFactory=null;
		log.info("Creo SSL context con il certificato:"+certAlias);
		

		String CERT_ALIAS = certAlias, CERT_PASSWORD = "changeit";
		try {
			  KeyStore identityKeyStore = KeyStore.getInstance("jks");
		      ClassPathResource classPathResource = new ClassPathResource("keystore/ssl_keystore.jks");
			  InputStream inputStream = classPathResource.getInputStream();
			  identityKeyStore.load(inputStream, CERT_PASSWORD.toCharArray());

		      KeyStore trustKeyStore = KeyStore.getInstance("jks");
		      FileInputStream trustKeyStoreFile = new FileInputStream(new File(ArgTrustStore));
		      trustKeyStore.load(trustKeyStoreFile, CERT_PASSWORD.toCharArray());
		      
		            
		      SSLContext sslContext = SSLContexts.custom()
		     
		              .loadKeyMaterial(identityKeyStore, CERT_PASSWORD.toCharArray(), new PrivateKeyStrategy() {
		                  @Override
		                  public String chooseAlias(Map<String, PrivateKeyDetails> aliases, Socket socket) {
		                      return CERT_ALIAS;
		                  }
		              })
		     
		              .loadTrustMaterial(trustKeyStore, null)
		              .build();
		      socketFactory= new SSLConnectionSocketFactory(sslContext,
		              new String[]{"TLSv1.2", "TLSv1.1"},
		              null,
		              SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
		
		return socketFactory;
	}
}
