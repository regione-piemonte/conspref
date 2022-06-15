/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb;

import it.csi.conspref.consprefboweb.dto.Informativa;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/informativa")
public interface InformativaApi {

   @GET
   @Path("/find/{cf}")
   @Produces({"application/json"})
   public Response find(@PathParam("cf") String cf);

   @GET
   @Path("/get-sotto-tipo-services")
   @Produces({"application/json"})
   public Response getSottoTipoServices();

   @GET
   @Path("/get-asl-list")
   @Produces({"application/json"})
   public Response getAslList();

   @GET
   @Path("/find/{cf}/{informativaId}")
   @Produces({"application/json"})
   public Response find(@PathParam("cf") String cf, @PathParam("informativaId") Integer informativaId);

   @PUT
   @Path("/update/{cf}")
   @Produces({"application/json"})
   public Response updateInformativa(Informativa informativa, @PathParam("cf") String cf);
}
