/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestParam;

@Path("/cittadino")
public interface CittadiniApi {

   @GET
   @Path("/login")
   @Produces({"application/json"})
   public Response login(@Context HttpServletRequest req);

   @GET
   @Path("/find/{cf}")
   @Produces({"application/json"})
   public Response find(@PathParam("cf") String cf, @Context HttpServletRequest req);

   @GET
   @Path("/find")
   @Produces({"application/json"})
   public Response find(@HeaderParam("nome") String nome, @HeaderParam("cognome") String cognome, @HeaderParam("dataNascita") String dataNascita, @Context HttpServletRequest req);

   @GET
   @Path("/find/{cf}")
   @Produces({"application/json"})
   public Response find(@PathParam("cf") String cf, @HeaderParam("nome") String nome, @HeaderParam("cognome") String cognome, @HeaderParam("dataNascita") String dataNascita, @Context HttpServletRequest req);

   @GET
   @Path("/token/{token}")
   @Produces({"application/json"})
   public Response getTokenInformation2(@Context HttpServletRequest req, @PathParam("token") String token);
}
