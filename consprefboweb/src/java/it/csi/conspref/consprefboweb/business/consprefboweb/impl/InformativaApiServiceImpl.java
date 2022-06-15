/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb.impl;

import it.csi.conspref.consprefboweb.business.consprefboweb.InformativaApi;
import it.csi.conspref.consprefboweb.business.consprefboweb.impl.service.InformativaService;
import it.csi.conspref.consprefboweb.dto.Informativa;
import it.csi.conspref.consprefboweb.util.SpringApplicationContextProvider;

import javax.ws.rs.core.Response;

public class InformativaApiServiceImpl implements InformativaApi {

   private InformativaService informativaService = (InformativaService) SpringApplicationContextProvider.getApplicationContext().getBean("it.csi.conspref.consprefboweb.business.consprefboweb.impl.service.InformativaService");

   @Override
   public Response find(String cf) {
      return informativaService.getInformativaList(cf);
   }

   @Override
   public Response getSottoTipoServices() {
      return informativaService.getSottoTipoList();
   }

   @Override
   public Response find(String cf, Integer informativaId) {
      return informativaService.getInformativaList(cf, informativaId);
   }

   @Override
   public Response getAslList() {
      return informativaService.getAslList();
   }

   @Override
   public Response updateInformativa(Informativa informativa, String cf) {
      return informativaService.saveInformativa(informativa, cf);
   }

}
