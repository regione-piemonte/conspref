/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb.impl.service;

import it.csi.conspref.consprefbe.ws.ConsensoBeService;
import it.csi.conspref.consprefbe.ws.model.*;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativa;
import it.csi.conspref.consprefbe.ws.msg.ConsultaInformativaResponse;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;
import it.csi.conspref.consprefbe.ws.msg.be.*;
import it.csi.conspref.consprefboweb.business.mapper.CodificaMapper;
import it.csi.conspref.consprefboweb.business.mapper.ConsensoMapper;
import it.csi.conspref.consprefboweb.business.mapper.InformativaMapper;
import it.csi.conspref.consprefboweb.dto.Cittadino;
import it.csi.conspref.consprefboweb.util.SpringApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InformativaService {

   @Autowired
   private ConsensoBeService consprefService;

   private CitizenService citizenService = (CitizenService) SpringApplicationContextProvider.getApplicationContext().getBean("it.csi.conspref.consprefboweb.business.consprefboweb.impl.service.CitizenService");

   public Response getInformativaList(String cf) {
      return getInformativaList(cf, null);
   }

   public Response getSottoTipoList() {
      ConsultaCodificaBe consultaCodificaBe = getConsultaCodificaBe();
      ConsultaSottoTipoBeResponse consultaSottoTipoBeResponse = consprefService.consultaSottoTipoBeService(consultaCodificaBe);

      List<SottoTipoConsenso> sottotipiConsenso = consultaSottoTipoBeResponse.getSottotipiConsenso();

      if (isEmptyList(sottotipiConsenso)) {
         return Response.noContent().build();
      }

      List<Codifica> collect = sottotipiConsenso.stream().map(sottotipiConsensoItem -> (Codifica) sottotipiConsensoItem).collect(Collectors.toList());

      return Response.ok(new CodificaMapper().fromList(collect)).build();
   }

   public Response getInformativaList(String cf, Integer informativaId) {
      List<it.csi.conspref.consprefboweb.dto.Informativa> informativaList = findInformativaList(cf);

      if (isEmptyList(informativaList)) {
         return Response.noContent().build();
      } else {
         if (informativaId != null) {
            informativaList = informativaList.stream()
                    .filter(informativa -> informativa.getIdInformativa().equals(informativaId))
                    .collect(Collectors.toList());
         }
         loadHtmlTemplate(informativaList);
         if (informativaId == null) {
            loadDelegato(informativaList);
         }
         return Response.ok(informativaList).build();
      }
   }

   public Response saveInformativa(it.csi.conspref.consprefboweb.dto.Informativa informativa, String cf) {

      List<it.csi.conspref.consprefboweb.dto.Consenso> consensoList = informativa.getConsensoList();
      List<Errore> elencoErrori = new ArrayList<>();

      consensoList.forEach(consenso -> {
         Consenso consenso1 = null;
         ServiceResponse response = null;
         if (consenso.getUpdated() != null && consenso.getUpdated()) {
            if (consenso.getUuid() != null) {
               ModificaConsensoBeResponse modificaConsensoBeResponse = modifyConsensus(informativa, cf, consenso);
               consenso1 = modificaConsensoBeResponse.getConsenso();
               response = modificaConsensoBeResponse;

            } else {
               boolean modifyToRevoked = false;

               if (consenso.getValoreConsenso().getCodice().equals("NO")) {
                  modifyToRevoked = true;
                  consenso.getValoreConsenso().setCodice("SI");
                  consenso.getTipoStato().setCodice("A");
               }
               InserisciConsensoBeResponse inserisciConsensoBeResponse = createConsensus(cf, consenso);
               consenso1 = inserisciConsensoBeResponse.getConsenso();
               response = inserisciConsensoBeResponse;

               if (modifyToRevoked && consenso1 != null) {
                  consenso = new ConsensoMapper().from(consenso1);
                  consenso.getValoreConsenso().setCodice("NO");
                  consenso.getTipoStato().setCodice("R");

                  ModificaConsensoBeResponse modificaConsensoBeResponse = modifyConsensus(informativa, cf, consenso);
                  consenso1 = modificaConsensoBeResponse.getConsenso();
                  response = modificaConsensoBeResponse;
               }
            }

            if (consenso1 == null && response != null) {
               final List<Errore> responseElencoErrori = response.getElencoErrori();
               elencoErrori.addAll(responseElencoErrori.stream().
                       filter(errore -> !errore.getCodice().equals("CONS.E01"))
                       .collect(Collectors.toList()));
            }
         }
      });

      if (elencoErrori.isEmpty()) {
         return Response.ok().build();

      } else {
         return Response.ok(elencoErrori).build();

      }
   }

   public Response getAslList() {
      ConsultaCodificaBe consultaCodificaBe = getConsultaCodificaBe();
      ConsultaAsrBeResponse consultaAsrBeResponse = consprefService.consultaAsrBeService(consultaCodificaBe);
      List<TipoASR> asr = consultaAsrBeResponse.getAsr();

      if (isEmptyList(asr)) {
         return Response.noContent().build();
      }

      List<Codifica> collect = asr.stream().map(asrItem -> (Codifica) asrItem).collect(Collectors.toList());

      return Response.ok(new CodificaMapper().fromList(collect)).build();
   }

   private ConsultaCodificaBe getConsultaCodificaBe() {
      ConsultaCodificaBe consultaCodificaBe = new ConsultaCodificaBe();
      Richiedente value = new Richiedente();
      ApplicazioneRichiedente value1 = new ApplicazioneRichiedente();
      value1.setCodice("CONSPREFBO");
      value1.setIdRequest(UUID.randomUUID().toString());
      value.setServizio(value1);
      value.setCodiceFiscale("xyz");
      consultaCodificaBe.setRichiedente(value);
      return consultaCodificaBe;
   }

   private InserisciConsensoBeResponse createConsensus(String cf, it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      InserisciConsensoBe inserisciConsensoBe = new InserisciConsensoBe();
      inserisciConsensoBe.setRichiedente(prepareRichiedente(cf));

      inserisciConsensoBe.setConsenso(new ConsensoMapper().to(consenso));
      return consprefService.inserisciConsensoBeService(inserisciConsensoBe);
   }

   private ModificaConsensoBeResponse modifyConsensus(it.csi.conspref.consprefboweb.dto.Informativa informativa, String cf, it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      ModificaConsensoBe modificaConsensoBe = new ModificaConsensoBe();
      modificaConsensoBe.setRichiedente(prepareRichiedente(cf));

      prepareModifyRequest(informativa, modificaConsensoBe, consenso);
      return consprefService.modificaConsensoBeService(modificaConsensoBe);
   }

   private void prepareModifyRequest(it.csi.conspref.consprefboweb.dto.Informativa informativa, ModificaConsensoBe modificaConsensoBe, it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      final TipoASR asr = getTipoASR(consenso);
      final TipoStato tipoStato = getTipoStato(consenso);
      final ValoreConsenso valoreConsenso = getValoreConsenso(consenso);
      final Fonte fonte = getFonte(consenso);

      modificaConsensoBe.setAsr(asr);
      modificaConsensoBe.setTipoStato(tipoStato);
      modificaConsensoBe.setValoreConsenso(valoreConsenso);
      modificaConsensoBe.setIdInformativa(informativa.getIdInformativa());
      modificaConsensoBe.setFonte(fonte);
      modificaConsensoBe.setIdAura(consenso.getIdAura());
      modificaConsensoBe.setCfCittadino(consenso.getCfCittadino());
      modificaConsensoBe.setDataAcquisizione(new Date());
   }

   private Fonte getFonte(it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      final Fonte fonte = new Fonte();
      final it.csi.conspref.consprefboweb.dto.Fonte fonte1 = consenso.getFonte();
      fonte.setCodice(fonte1.getCodice());
      fonte.setDescrizione(fonte1.getDescrizione());

      final TipoFonte tipoFonte = new TipoFonte();
      final it.csi.conspref.consprefboweb.dto.Codifica tipoFonte1 = fonte1.getTipoFonte();
      tipoFonte.setCodice(tipoFonte1.getCodice());
      tipoFonte.setDescrizione(tipoFonte1.getDescrizione());
      fonte.setTipoFonte(tipoFonte);
      return fonte;
   }

   private ValoreConsenso getValoreConsenso(it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      final ValoreConsenso valoreConsenso = new ValoreConsenso();
      final it.csi.conspref.consprefboweb.dto.Codifica valoreConsenso1 = consenso.getValoreConsenso();
      valoreConsenso.setCodice(valoreConsenso1.getCodice());
      valoreConsenso.setDescrizione(valoreConsenso1.getDescrizione());
      return valoreConsenso;
   }

   private TipoStato getTipoStato(it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      final TipoStato tipoStato = new TipoStato();
      final it.csi.conspref.consprefboweb.dto.Codifica tipoStato1 = consenso.getTipoStato();
      tipoStato.setCodice(tipoStato1.getCodice());
      tipoStato.setDescrizione(tipoStato1.getDescrizione());
      return tipoStato;
   }

   private TipoASR getTipoASR(it.csi.conspref.consprefboweb.dto.Consenso consenso) {
      final TipoASR asr = new TipoASR();
      final it.csi.conspref.consprefboweb.dto.Codifica tipoAsr = consenso.getTipoAsr();
      asr.setCodice(tipoAsr.getCodice());
      asr.setDescrizione(tipoAsr.getDescrizione());
      return asr;
   }

   private boolean isEmptyList(List list) {
      return list == null || list.isEmpty();
   }

   private List<it.csi.conspref.consprefboweb.dto.Informativa> findInformativaList(String cf) {
      final ConsultaInformativa consultaInformativa = prepareRequest(cf);
      ConsultaInformativaResponse consultaInformativaResponse = consprefService.consultaInformativaBeService(consultaInformativa);

      final List<Informativa> informative = consultaInformativaResponse.getInformative();

      final List<it.csi.conspref.consprefboweb.dto.Informativa> resultInformativas = new InformativaMapper().fromList(informative);

      if (resultInformativas != null) {
         resultInformativas.forEach(informativa -> {
            ConsultaConsensoBe consultaConsensoBe = prepareConsultaConsensoBe(consultaInformativa, informativa);
            ConsultaConsensoBeResponse consultaConsensoBeResponse = consprefService.consultaConsensoBeService(consultaConsensoBe);

            final List<Consenso> consensi = consultaConsensoBeResponse.getConsensi();

            informativa.setConsensoList(new ConsensoMapper().fromList(consensi));
         });
      }

      return resultInformativas;
   }

   private ConsultaConsensoBe prepareConsultaConsensoBe(ConsultaInformativa consultaInformativa, it.csi.conspref.consprefboweb.dto.Informativa informativa) {
      ConsultaConsensoBe consultaConsensoBe = new ConsultaConsensoBe();
      final Richiedente richiedente = consultaInformativa.getRichiedente();
      consultaConsensoBe.setRichiedente(richiedente);
      consultaConsensoBe.setCfCittadino(richiedente.getCodiceFiscale());

      final Fonte fonte = new Fonte();
      fonte.setCodice("fonte");
      final TipoFonte tipoFonte = new TipoFonte();
      tipoFonte.setCodice("tipoFonte");
      fonte.setTipoFonte(tipoFonte);
      consultaConsensoBe.setFonte(fonte);

      consultaConsensoBe.setIdInformativa(informativa.getIdInformativa() + "");
      consultaConsensoBe.setVerificaAura(false);

      return consultaConsensoBe;
   }

   private ConsultaInformativa prepareRequest(String cf) {
      final ConsultaInformativa consultaInformativa = new ConsultaInformativa();
      final Richiedente richiedente = prepareRichiedente(cf);
      consultaInformativa.setRichiedente(richiedente);
      return consultaInformativa;
   }

   private Richiedente prepareRichiedente(String cf) {
      final Richiedente richiedente = new Richiedente();
      richiedente.setCodiceFiscale(cf);
      final ApplicazioneRichiedente applicazioneRichiedente = new ApplicazioneRichiedente();
      applicazioneRichiedente.setCodice("CONSPREFBO");
      applicazioneRichiedente.setIdRequest(UUID.randomUUID().toString());
      richiedente.setServizio(applicazioneRichiedente);
      return richiedente;
   }

   private void loadHtmlTemplate(List<it.csi.conspref.consprefboweb.dto.Informativa> informativaList) {
      for (it.csi.conspref.consprefboweb.dto.Informativa informativa : informativaList) {
         URL url = null;
         String htmlInformativa = informativa.getHtmlInformativa();
         if (htmlInformativa != null) {
            try {
               url = new URL(htmlInformativa);
               URLConnection con = url.openConnection();
               Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
               Matcher m = p.matcher(con.getContentType());
               /* If Content-Type doesn't match this pre-conception, choose default and
                * hope for the best. */
               String charset = m.matches() ? m.group(1) : "ISO-8859-1";
               Reader r = new InputStreamReader(con.getInputStream(), charset);
               StringBuilder buf = new StringBuilder();
               while (true) {
                  int ch = r.read();
                  if (ch < 0)
                     break;
                  buf.append((char) ch);
               }
               informativa.setHtmlInformativa(buf.toString());
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   private void loadDelegato(List<it.csi.conspref.consprefboweb.dto.Informativa> informativaList) {
      List<String> proceededFiscalCodes = new ArrayList<>();

      for (it.csi.conspref.consprefboweb.dto.Informativa informativa : informativaList) {
         List<it.csi.conspref.consprefboweb.dto.Consenso> consensoList = informativa.getConsensoList();
         if (consensoList != null) {
            for (it.csi.conspref.consprefboweb.dto.Consenso consenso : consensoList) {
               final String cfDelegato = consenso.getCfDelegato();

               if (cfDelegato != null && cfDelegato != "" && !proceededFiscalCodes.contains(cfDelegato)) {
                  final List<Cittadino> cittadinos = citizenService.searchCitizens(cfDelegato, null, null, null);
                  if (cittadinos != null && !cittadinos.isEmpty()) {
                     consenso.setDelegatoNome(cittadinos.get(0).getNome());
                     consenso.setDelegatoCognome(cittadinos.get(0).getCognome());
                     proceededFiscalCodes.add(cfDelegato);
                  }
               }
            }
         }
      }
   }
}
