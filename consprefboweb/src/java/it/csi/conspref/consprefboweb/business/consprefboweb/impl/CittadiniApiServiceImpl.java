/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb.impl;

import it.csi.conspref.consprefboweb.business.consprefboweb.CittadiniApi;
import it.csi.conspref.consprefboweb.business.consprefboweb.impl.service.CitizenService;
import it.csi.conspref.consprefboweb.dto.Cittadino;
import it.csi.conspref.consprefboweb.filter.IrideIdAdapterFilter;
import it.csi.conspref.consprefboweb.dto.UserInfo;
import it.csi.conspref.consprefboweb.util.SpringApplicationContextProvider;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.WebServiceException;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


public class CittadiniApiServiceImpl implements CittadiniApi {

   private CitizenService citizenService = (CitizenService) SpringApplicationContextProvider.getApplicationContext().getBean("it.csi.conspref.consprefboweb.business.consprefboweb.impl.service.CitizenService");

   @Override
   public Response find(String cf, HttpServletRequest req) {
      return citizenService.findCitizen(cf, null, null, null);
   }

   @Override
   public Response find(String nome, String cognome, String dataNascita, HttpServletRequest req) {
      return citizenService.findCitizen(null, nome, cognome, dataNascita);
   }

   @Override
   public Response find(String cf, String nome, String cognome, String dataNascita, HttpServletRequest req) {
      return citizenService.findCitizen(cf, nome, cognome, dataNascita);
   }

   @Override
   public Response login(HttpServletRequest req) {
      Map<String, Object> res = new HashMap<String, Object>();
      String cf = "null";
      Response.ResponseBuilder corsedResponse = getCorsedResponse();
      try {
         UserInfo userInfo = (UserInfo) req.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);

         cf = userInfo.getCodFisc();

         List<Cittadino> cittadinos = citizenService.searchCitizens(cf, null, null, null);

         if(cittadinos == null || cittadinos.isEmpty()) {
            return Response.status(403).build();
         }

         cf = cittadinos.get(0).getCodiceFiscale();

         return (citizenService.isOperator(cf)? Response.ok(): Response.status(403)).build();
      } catch (WebServiceException ws) {
         res.put("status", "ko");
         res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
         res.put("reason", ws.getCause());
         res.put("cf", cf);
         res.put("message", ws.getMessage());
         res.put("trace", ws.getStackTrace());
         return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
      } catch (Exception e) {
         res.put("status", "ko2");
         res.put("reason", e.getCause());
         res.put("message", e.getMessage());
         res.put("cf", cf);
         res.put("trace", e.getStackTrace());
         res.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
         return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
      }
   }

   public static Response.ResponseBuilder getCorsedResponse() {

      Response.ResponseBuilder builder = Response.noContent();
      Response.ResponseBuilder header = builder
              .header("Access-Control-Allow-Origin", "*")
              .header("Access-Control-Allow-Headers", "Authorization, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
              .header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, HEAD, OPTIONS")
              .header("Access-Control-Max-Age", "1209600");

      return header;
   }

	@Override
	public Response getTokenInformation2(HttpServletRequest req, String token) {

		// ip
		String ip=getIpAddressClient(req);
		if ("SUCCESSO".equalsIgnoreCase(citizenService.getTokenInformation2(token, ip))) {
			return  Response.ok().build();
		}


		return Response.status(403).build();
	}

	private String getIpAddressClient(HttpServletRequest request) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String remoteAddress=request.getRemoteAddr();




		if (ipAddress == null) {
		ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
		}
}
