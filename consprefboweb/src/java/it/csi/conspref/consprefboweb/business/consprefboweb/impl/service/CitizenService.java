/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb.impl.service;

import it.csi.conspref.consprefbe.ws.ConsensoBeService;
import it.csi.conspref.consprefbe.ws.model.ApplicazioneRichiedente;
import it.csi.conspref.consprefbe.ws.model.Operatore;
import it.csi.conspref.consprefbe.ws.model.Richiedente;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBe;
import it.csi.conspref.consprefbe.ws.msg.be.ConsultaProfiloBeResponse;
import it.csi.conspref.consprefboweb.business.aura.AnagrafeFindWSClientPool;
import it.csi.conspref.consprefboweb.business.aura.find.model.AnagrafeFindSoap;
import it.csi.conspref.consprefboweb.business.aura.find.model.ArrayOfdatianagraficiDatiAnagrafici;
import it.csi.conspref.consprefboweb.business.aura.find.model.DatiAnagraficiMsg;
import it.csi.conspref.consprefboweb.business.aura.find.model.FindProfiliAnagraficiRequest;
import it.csi.conspref.consprefboweb.dto.Cittadino;
import it.csi.dmacc.GetTokenInformation2Request;
import it.csi.dmacc.GetTokenInformation2Response;
import it.csi.dmacc.TokenInfoServiceClient;
import it.csi.dmacc.TokenInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static it.csi.conspref.consprefbe.ws.model.RisultatoCodice.SUCCESSO;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CitizenService {

	@Autowired
	private ConsensoBeService consprefService;

	@Autowired
	private AnagrafeFindWSClientPool anagrafeFindSoapService;

	@Autowired
	private TokenInfoServiceClient tokenInfoServiceClient;

	public Response findCitizen(String codiceFiscale, String nome, String cognome, String dataDiNascita) {
		final List<Cittadino> cittadinos = searchCitizens(codiceFiscale, nome, cognome, dataDiNascita);
		if (cittadinos == null || cittadinos.isEmpty()) {
			Response.noContent();
		}

		return Response.ok(cittadinos).build();
	}

	public boolean isOperator(String cf) {
		ConsultaProfiloBe consultaProfiloBe = new ConsultaProfiloBe();
		consultaProfiloBe.setCfOperatore(cf);
		Richiedente value = new Richiedente();
		ApplicazioneRichiedente value1 = new ApplicazioneRichiedente();
		value1.setCodice("CONSENSOBO");
		value1.setIdRequest(UUID.randomUUID().toString());
		value.setServizio(value1);
		value.setCodiceFiscale(cf);
		consultaProfiloBe.setRichiedente(value);

		Operatore operatore = new Operatore();
		operatore.setTipoOperatore("OPERATORE_BKO");
		operatore.setCodiceOperatore("OPERATOREBKO");
		consultaProfiloBe.setOperatore(operatore);

		ConsultaProfiloBeResponse consultaProfiloBeResponse = consprefService
				.consultaProfiloBeService(consultaProfiloBe);
		return consultaProfiloBeResponse.getEsito() == SUCCESSO;
	}

	public List<Cittadino> searchCitizens(String codiceFiscale, String nome, String cognome, String dataDiNascita) {
		dataDiNascita = dataDiNascita == null ? null : dataDiNascita.replace("-", "/");

		FindProfiliAnagraficiRequest findProfiliAnagraficiRequest = prepareRequest(codiceFiscale, nome, cognome,
				dataDiNascita);
		AnagrafeFindSoap client = anagrafeFindSoapService.getClient();
		DatiAnagraficiMsg responseAura = client.findProfiliAnagrafici(findProfiliAnagraficiRequest);

		return extractCitizens(responseAura);
	}

	private FindProfiliAnagraficiRequest prepareRequest(String codiceFiscale, String nome, String cognome,
			String dataDiNascita) {
		FindProfiliAnagraficiRequest findProfiliAnagraficiRequest = new FindProfiliAnagraficiRequest();
		findProfiliAnagraficiRequest.setCodiceFiscale(codiceFiscale);
		findProfiliAnagraficiRequest.setCognome(cognome);
		findProfiliAnagraficiRequest.setNome(nome);
		findProfiliAnagraficiRequest.setDataNascita(dataDiNascita);
		findProfiliAnagraficiRequest.setFlagDecesso("0");

		return findProfiliAnagraficiRequest;
	}

	private List<Cittadino> extractCitizens(DatiAnagraficiMsg responseAura) {
		ArrayOfdatianagraficiDatiAnagrafici elencoProfili = responseAura.getBody().getElencoProfili();
		List<Cittadino> citizens = new ArrayList<>();

		if (elencoProfili == null)
			return citizens;

		elencoProfili.getDatianagrafici().forEach(foundCitizen -> {
			Cittadino citizen1 = new Cittadino();
			citizen1.setNome(foundCitizen.getNome());
			citizen1.setCodiceFiscale(foundCitizen.getCodiceFiscale());
			citizen1.setCognome(foundCitizen.getCognome());
			citizen1.setSesso(foundCitizen.getSesso());
			citizen1.setComuneNascita(foundCitizen.getComuneNascita());
			citizen1.setDataNascita(foundCitizen.getDataNascita().toGregorianCalendar().getTime());
			citizen1.setIdAura(foundCitizen.getIdProfiloAnagrafico().longValue());
			citizens.add(citizen1);
		});

		return citizens;
	}

	public String getTokenInformation2( String token, String ip) {
		String ret = "";
		GetTokenInformation2Request getTokenInformation2Request = new GetTokenInformation2Request();

		getTokenInformation2Request.setApplicazione("CONSPREF");

		getTokenInformation2Request.setIpBrowser(ip);
		getTokenInformation2Request.setToken(token);



		TokenInformationService client = tokenInfoServiceClient.getClient();

		GetTokenInformation2Response responseTokenInformation2 = client
				.getTokenInformation2(getTokenInformation2Request);

		if (responseTokenInformation2 != null) {
			String esito = responseTokenInformation2.getEsito().toString();

			if (esito == "FALLIMENTO") {

				ret = "FALLIMENTO";
			} else {
;
				ret = "SUCCESSO";
			}
		}
		return ret;
	}
}
