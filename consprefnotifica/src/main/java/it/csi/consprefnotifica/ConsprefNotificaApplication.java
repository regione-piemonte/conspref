/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.consprefnotifica;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j.support.CryptoFactoryBean;

import it.csi.conspref.Asr;
import it.csi.conspref.Errore;
import it.csi.conspref.Fonte;
import it.csi.conspref.NotificaAcquisizioneConsensoRicevuta;
import it.csi.conspref.NotificaRevocaConsensoRicevuta;
import it.csi.conspref.Operatore;
import it.csi.conspref.VerificaServizioRicevuta;
import it.csi.conspref.consprefnotifica.notificaConsensi.Consenso;
import it.csi.conspref.consprefnotifica.notificaConsensi.EsitoEnum;
import it.csi.conspref.consprefnotifica.notificaConsensi.NotificaStatoEnum;
import it.csi.conspref.consprefnotifica.notificaConsensi.StatoConsensoEnum;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTConsenso;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTEndpoint;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTNotifica;
import it.csi.conspref.consprefnotifica.notificaConsensi.service.NotificaClient;
import it.csi.conspref.consprefnotifica.notificaConsensi.service.NotificaConfiguration;
import it.csi.conspref.consprefnotifica.notificaConsensi.service.NotificaConsensoService;
import it.csi.conspref.consprefnotifica.util.NotificatoreUtil;
import it.csi.conspref.consprefnotifica.util.SendEmailSMTP;
import it.csi.conspref.consprefnotifica.ws.NotificaAcquisizioneConsensoEstesaRichiesta;
import it.csi.conspref.consprefnotifica.ws.NotificaRevocaConsensoEstesaRichiesta;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"it.csi.conspref.consprefnotifica"})
@EntityScan("it.csi.conspref.consprefnotifica.notificaConsensi.entity")
public class ConsprefNotificaApplication {
	private static final Logger log = LoggerFactory.getLogger(ConsprefNotificaApplication.class);
	
	protected Map<String, String> mailProperties;
	protected Map<String, String> notificatoreProperties;
	
	@Value("${conspref.testmode}")
	private String testMode="no";
	
	@Value("${conspref.SecurementUsername}")
	private String internalSecurementUsername;
	
	@Value("${conspref.PrivateKeyPassword}")
	private String privateKeyPassword;
	
	@Value("${conspref.SecurementPassword}")
	private String securementPassword;
	
	@Value("${notificatore.title}")
	private String title;
	
	@Value("${notificatore.cittadino.body}")
	private String cittadinoBody;
	
	@Value("${notificatore.action.weburl}")
	private String weburl;
		
	public static void main(String[] args) {
		log.info("Start ConsprefNotificaApplication");

		SpringApplication.run(ConsprefNotificaApplication.class, args);
	}
	
	@Bean
    public KeyStoreCallbackHandler securityCallbackHandler(){
        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setPrivateKeyPassword(privateKeyPassword);
        return callbackHandler;
    }
	
	@Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
		log.info("Sono in CryptoFactoryBean usato per firmare");
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("changeit");
       
       
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("keystore/ssl_keystore.jks"));
        return cryptoFactoryBean;
    }
	
	@Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
		log.info("Sono in securityInterceptor per firma");
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

       
        securityInterceptor.setSecurementActions("Timestamp Signature");
       
       
        securityInterceptor.setSecurementUsername(internalSecurementUsername);

        securityInterceptor.setSecurementPassword(securementPassword);
        securityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        return securityInterceptor;
    }
	

	/**
	 * @param service
	 * @return
	 */
	/**
	 * @param service
	 * @return
	 */
	@Bean
	CommandLineRunner lookup(NotificaConsensoService service, NotificatoreUtil notificatoreUtil, SendEmailSMTP sendEmailSMTP) {
		return args -> {
			log.info("start applicazione ConsprefNotificaApplication");
			String filtroPerAzienda = "";

			if (args.length > 0) {
				filtroPerAzienda = args[0];
				
				log.info("filtroPerAzienda='"+filtroPerAzienda+"'");
			} else {
				throw new Exception("Passare per argomento un azienda o notif");
			}
			if (!("notif".equalsIgnoreCase(filtroPerAzienda))) {
					
				

				
				List<Consenso> consensi = service.ricercaConsensiDaNotificare(filtroPerAzienda);
			try {
				
				List<ConsTEndpoint> consTEndpoints=service.ricercaEndpointPerAsr(filtroPerAzienda);
				
				
				if(consensi!=null && !consensi.isEmpty()) {
					
					
				
					AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NotificaConfiguration.class);
					NotificaClient client = context.getBean(NotificaClient.class);
					ClientInterceptor[] interceptors = new ClientInterceptor[]{this.securityInterceptor()};
					client.setInterceptors(interceptors);
				
					boolean primo=true;	
					for (Consenso consenso : consensi) {
						
						if (primo) {
						
							for (ConsTEndpoint tconsTEndpoint : consTEndpoints) {								
									  log.info("Endpoint per verifica servizio: "+tconsTEndpoint.getEndpUrl());				
									  if ("si".equalsIgnoreCase(testMode)) {
										  log.info("Attenzione verifica servizio disabilitato!!!");
										   
									  } else {
										  try {										
											  VerificaServizioRicevuta response = client.callVerificaServizio("1",
											  "2",tconsTEndpoint.getEndpUrl());
											  log.info("Risposta da verifica servizio:"+response.getCodiceServizio());
											 	
										  } catch (Exception e) {
											    sendEmailSMTP.send(tconsTEndpoint.getEndpUrl(), "",e.getMessage(),1);
											    throw e;
										  }
									  }
							}
							primo=false;
						}
						if(consenso.getStato().equalsIgnoreCase(StatoConsensoEnum.ATTIVO.getCodice())){
								
							NotificaAcquisizioneConsensoEstesaRichiesta req = new NotificaAcquisizioneConsensoEstesaRichiesta();
													
							req.setRequestId(consenso.getRequestId());
							req.setCodiceServizio(consenso.getCodiceServizio());
							req.setCfRichiedente(consenso.getCfRichiedente());
							req.setCfDelegato(consenso.getCfDelegato());
							req.setIdAura(consenso.getIdAura());

																
							Operatore operatore = new Operatore();
							
							if(consenso.getOperatore()!=null) {
								operatore.setCodiceOperatore(consenso.getOperatore().getCodiceOperatore());
								operatore.setTipoOperatore(consenso.getOperatore().getTipoOperatore());
							}
							req.setOperatore(operatore);
							
							Fonte fonte = new Fonte();
							fonte.setCodiceFonte(consenso.getFonte().getCodiceFonte());
							fonte.setCodiceTipoFonte(consenso.getFonte().getCodiceTipoFonte());
							req.setFonte(fonte);
							req.setDataAcquisizione(getDataAcquisizioneFormatted( consenso.getDataAcquisizione()));
							req.setCodiceTipoConsenso(consenso.getCodiceTipoConsenso());
							req.setCodiceSottotipoConsenso(consenso.getCodiceSottotipoConsenso());
							req.setDescrizioneSottotipoConsenso(consenso.getDescrizioneSottotipoConsenso());
							req.setValoreConsenso(consenso.getValoreConsenso());
							
							Asr asr = new Asr();
							asr.setCodice(consenso.getAsr().getCodice());
							req.setAsr(asr);
							
							
							  
							NotificaAcquisizioneConsensoRicevuta ricevuta=null;
							for (ConsTEndpoint tconsTEndpoint : consTEndpoints) {
								log.info("Invoco Endpoint per NotificaAcquisizioneConsensoRicevuta: "+tconsTEndpoint.getEndpUrl());
								req.setEndpointAsr(tconsTEndpoint.getEndpUrl());
								
								 
								ricevuta= new NotificaAcquisizioneConsensoRicevuta();
								if ("si".equalsIgnoreCase(testMode)) {
									log.debug("Forzo risposta NotificaAcquisizioneConsensoRicevuta a 0000 perchè testMode=si");
									ricevuta.setEsito("0000");//Forzata risposta x modalità di test
								} else {
									try {
									ricevuta=client.callNotificaAcquisizioneConsenso(req); 
									} catch (Exception e) {
			
										throw e;
									}
								}						
								log.info("Risposta dal servizio NotificaAcquisizioneConsenso:"+ricevuta.getEsito());
								
								ConsTNotifica notifica = new ConsTNotifica();
								notifica.setNotEndpUrl(tconsTEndpoint.getEndpUrl());
								notifica.setCfCittadino(consenso.getCfRichiedente());
								notifica.setTipoStato(StatoConsensoEnum.ATTIVO.getCodice());
								notifica.setValoreConsenso(consenso.getValoreConsenso());
								notifica.setDataAcquisizione(consenso.getDataAcquisizione());
								notifica.setConsUuid(consenso.getRequestId());
								notifica.setIdAura(consenso.getIdAura());
								notifica.setCodAsr(consenso.getAsr().getCodice());
								notifica.setdInformativaId(consenso.getdInformativaId());
								
								List<Errore> errori = new ArrayList<Errore>();
								
								if(EsitoEnum.SUCCESSO.getCodice().equalsIgnoreCase(ricevuta.getEsito()) || EsitoEnum.WARNING.getCodice().equalsIgnoreCase(ricevuta.getEsito())) {
									log.info("Notifica inviata correttamente:"+NotificaStatoEnum.CONCLUSA.getCodice());
									ConsTConsenso consTConsenso = new ConsTConsenso();
									consTConsenso.setConsId(consenso.getConsId());
									notifica.setConsTConsenso(consTConsenso);
									notifica.setNotStato(NotificaStatoEnum.CONCLUSA.getCodice());
									java.sql.Timestamp curentTimestamp=new java.sql.Timestamp(new Date().getTime());
									notifica.setNotFine(curentTimestamp);
								} else {
									notifica.setNotStato(NotificaStatoEnum.ERRORE.getCodice());
									log.info("Notifica terminata con Errore:"+NotificaStatoEnum.ERRORE.getCodice());
					
									
								}
			
								log.info("Richiamo inserisciNotifica");
								service.inserisciNotifica(notifica, errori, ricevuta.getEsito());
							}
							
						}
						
						if(consenso.getStato().equalsIgnoreCase(StatoConsensoEnum.REVOCATO.getCodice())){
						
							NotificaRevocaConsensoEstesaRichiesta req = new NotificaRevocaConsensoEstesaRichiesta();
							
							
							req.setRequestId(consenso.getRequestId());
							req.setCodiceServizio(consenso.getCodiceServizio());
							req.setCfRichiedente(consenso.getCfRichiedente());
							req.setCfDelegato(consenso.getCfDelegato());
							req.setIdAura(consenso.getIdAura());
										
							
							Operatore operatore = new Operatore();
				
							if(consenso.getOperatore()!=null) {
								operatore.setCodiceOperatore(consenso.getOperatore().getCodiceOperatore());
								operatore.setTipoOperatore(consenso.getOperatore().getTipoOperatore());
							}
							req.setOperatore(operatore);
							
							Fonte fonte = new Fonte();
							fonte.setCodiceFonte(consenso.getFonte().getCodiceFonte());
							fonte.setCodiceTipoFonte(consenso.getFonte().getCodiceTipoFonte());
							req.setFonte(fonte);
							req.setDataAcquisizione(getDataAcquisizioneFormatted( consenso.getDataAcquisizione()));
							req.setCodiceTipoConsenso(consenso.getCodiceTipoConsenso());
							req.setCodiceSottotipoConsenso(consenso.getCodiceSottotipoConsenso());
							req.setDescrizioneSottotipoConsenso(consenso.getDescrizioneSottotipoConsenso());
							
							Asr asr = new Asr();
							asr.setCodice(consenso.getAsr().getCodice());
							req.setAsr(asr);
							 
							
							NotificaRevocaConsensoRicevuta ricevuta=null;
							for (ConsTEndpoint tconsTEndpoint : consTEndpoints) {
								req.setEndpointAsr(tconsTEndpoint.getEndpUrl());
								ricevuta= new NotificaRevocaConsensoRicevuta();
								 if ("si".equalsIgnoreCase(testMode)) {
									 log.debug("Forzo risposta NotificaRevocaConsensoRicevuta a 0000 perchè testMode=si");
									 ricevuta.setEsito("0000");//Forzata risposta x modalità di test
								 } else {
									 try {
										 ricevuta= client.callNotificaRevocaConsenso(req);
									 } catch (Exception e) {
				
										 throw e;
									 }
								 }
								log.info("Risposta dal servizio NotificaRevocaConsenso:"+ricevuta.getEsito());
						
								ConsTNotifica notifica = new ConsTNotifica();
							
								notifica.setNotEndpUrl(tconsTEndpoint.getEndpUrl());
								notifica.setCfCittadino(consenso.getCfRichiedente());
							
								notifica.setTipoStato(StatoConsensoEnum.REVOCATO.getCodice());
								notifica.setValoreConsenso("NO");
								notifica.setDataAcquisizione(consenso.getDataAcquisizione());
								notifica.setConsUuid(consenso.getRequestId());
								notifica.setIdAura(consenso.getIdAura());
								notifica.setCodAsr(consenso.getAsr().getCodice());
								notifica.setdInformativaId(consenso.getdInformativaId());
								List<Errore> errori = new ArrayList<Errore>();
							
								if(EsitoEnum.SUCCESSO.getCodice().equalsIgnoreCase(ricevuta.getEsito()) || EsitoEnum.WARNING.getCodice().equalsIgnoreCase(ricevuta.getEsito())) {
									log.info("Notifica di Revoca inviata correttamente:"+NotificaStatoEnum.CONCLUSA.getCodice());
									ConsTConsenso consTConsenso = new ConsTConsenso();
									consTConsenso.setConsId(consenso.getConsId());
									notifica.setConsTConsenso(consTConsenso);
									notifica.setNotStato(NotificaStatoEnum.CONCLUSA.getCodice());	
									notifica.setNotFine(new java.sql.Timestamp(new Date().getTime()));
								} else {
									notifica.setNotStato(NotificaStatoEnum.ERRORE.getCodice());	
			
								
								}
							
								service.inserisciNotifica(notifica, errori, EsitoEnum.SUCCESSO.getCodice());
	
							}
						}
		
					}
				}
			  } catch(Exception ce) {
				  log.error("Eccezione inaspettata:\n"+ce.getMessage());
			  }
			 
			} else {

				if (args.length > 1) {
					String startDate=args[1];
					log.info("startDate="+startDate);
					this.inviaNotificheMassive(startDate,service, notificatoreUtil,sendEmailSMTP);
				}
			}
			 log.info("Elaborazione Terminata per: "+filtroPerAzienda);	
			 System.exit(0);
			};
		
	}

	private String getDataAcquisizioneFormatted(Timestamp dataAcquisizione) {

		Date date = new Date();
		date.setTime(dataAcquisizione.getTime());
		String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
		String formattedTime = new SimpleDateFormat("HHmmss").format(date);
		String dataAcquisizioneConvertita=formattedDate+formattedTime;
		log.info("dataAcquisizioneConvertita:"+dataAcquisizioneConvertita);
		return dataAcquisizioneConvertita;
	}

	@EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        printActiveProperties((ConfigurableEnvironment) event.getApplicationContext().getEnvironment());
        readMailProperties((ConfigurableEnvironment) event.getApplicationContext().getEnvironment());
    }

    private void printActiveProperties(ConfigurableEnvironment env) {

        
        log.info("************************* ACTIVE APP PROPERTIES ******************************");
        List<MapPropertySource> propertySources = new ArrayList<>();

        env.getPropertySources().forEach(it -> {
            if (it instanceof MapPropertySource && it.getName().contains("applicationConfig")) {
                propertySources.add((MapPropertySource) it);
            }
        });

        propertySources.stream()
                .map(propertySource -> propertySource.getSource().keySet())
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .forEach(key -> {
                    try {

                    } catch (Exception e) {
                        log.warn("{} -> {}", key, e.getMessage());
                    }
                });
        log.info("******************************************************************************");
    }
    

    private void readMailProperties(ConfigurableEnvironment env) {
    	String prefix_mail="spring.mail";
    	String prefix_notificatore="notificatore";
    	mailProperties = new HashMap<>();
    	notificatoreProperties= new HashMap<>();
    	if (env instanceof ConfigurableEnvironment) {
    	    for (PropertySource<?> propertySource : ((ConfigurableEnvironment) env).getPropertySources()) {
    	        if (propertySource instanceof EnumerablePropertySource) {
    	            for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
    	                if (key.startsWith(prefix_mail)) {
    	                	mailProperties.put(key, (String) propertySource.getProperty(key));
    	                } else if (key.startsWith(prefix_notificatore)) {
    	                	notificatoreProperties.put(key, (String) propertySource.getProperty(key));
    	                }
    	            }
    	        }
    	    }
    	}
    }
    
    private void inviaNotificheMassive(String startDate, NotificaConsensoService service, NotificatoreUtil notificatoreUtil,SendEmailSMTP sendEmailSMTP) {
    	
  	     final SimpleDateFormat dtFormatter = new SimpleDateFormat("dd/MM/yyyy");
    	
    	try {
    		log.info("Entro in ConsprefNotificaApplication.inviaNotificheMassive");
    		List<ConsTNotifica> notifiche = service.ricercaNotificheDaInviare(startDate);
    		String tmpCF="";
			StringBuffer ListaCfnonNotificati= new StringBuffer();
    		
    		Map<String,String> replacements = new HashMap<>();
    		
    		for (ConsTNotifica notifica : notifiche) {
    			if(!tmpCF.equalsIgnoreCase(notifica.getCfCittadino())) {
      		      		replacements.put("@CF@",notifica.getCfCittadino());
      		      		replacements.put("@DESCCONSENSO@",notifica.getConsTConsenso().getConsDInformativa().getConsDTipoCon().getDescTipoCons());
      		
      		      		replacements.put("@DTVARIAZIONE@", dtFormatter.format(new Date()));
      		      	    replacements.put("@WEBURL@",weburl);
    			}
    		
    		
    		
    		    try {
    		    	if (!tmpCF.equalsIgnoreCase(notifica.getCfCittadino())) {
    		
    		    		tmpCF=notifica.getCfCittadino();
    		    		notificatoreUtil.callNotificatore(notifica.getCfCittadino(), title, cittadinoBody, replacements);    		    		 
    		    	}
    		    } catch(Exception e) {
    		    	log.error("Errore nel richiamo del notificatore, non inviata notifica: "+e.getMessage());
    		
    		    	ListaCfnonNotificati.append(notifica.getCfCittadino()+";");
    		    }
    		}
    		
    		if(ListaCfnonNotificati.length()>15) {
    			sendEmailSMTP.send( ListaCfnonNotificati.toString(), "", "Errore durante l'invocazione del notificatore tramite batch Consensi", 3);
    		}
    		log.info("Esco in ConsprefNotificaApplication.inviaNotificheMassive");
    	}catch(Exception e) {
    		log.error("Errore in ConsprefNotificaApplication.inviaNotificheMassive eccezione:"+e.getMessage());
    	}
    }
}
