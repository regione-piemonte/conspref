/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.csi.conspref.consprefbe.model.ConsDInformativa;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.PropertiesUtil;

@Stateless
public class ConsDInformativaRepository {
	    
		@Inject
	    private LogUtil log;

	    @Inject
	    private EntityManager em;
	    
	    @Inject
		protected PropertiesUtil properties;
	   
	    
	    public ConsDInformativa ricercaInformativaByTipoSottoTipo(String tipo, String sottoTipo)  {
	    	final String METHOD_NAME = "ricercaTipoConsensoByCodice";
			log.debug(METHOD_NAME, "get service for: tipo=%s sottotipo=%s", tipo, sottoTipo);

	    	StringBuilder sb = new StringBuilder();
	    	sb.append("SELECT c FROM ConsDInformativa c ");	    	
	    	sb.append("WHERE (c.dataScadenza IS NULL OR c.dataScadenza >= '" + new Timestamp(new Date().getTime()) + "') ");
	    	
	    	Map<String, Object> params = new HashMap<>();
	    	populateFilter(sb, params, tipo, sottoTipo);
	    	
	    	String jpql = sb.toString();
	    	log.debug(METHOD_NAME, "jpql: %s", jpql);
	    	TypedQuery<ConsDInformativa> query = em.createQuery(jpql, ConsDInformativa.class);
	    	params.entrySet().stream().forEach(e -> {
	    		String key = e.getKey();
	    		Object value = e.getValue();
	    		query.setParameter(key, value);
	    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
	    	});
	    	ConsDInformativa result;
	    	try {
	    		result = query.getSingleResult();
	    	} catch(NonUniqueResultException nure) {
	    		String msg = (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT_CODE.getErrorCode()) + " " + tipo;
	    		log.error(METHOD_NAME, msg, nure);
	    		throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT_CODE.getErrorCode(), msg);
	    	} catch(NoResultException nre) {
	    		String msg = (String)properties.getProp().get(E_ERRORI.NO_RESULT_CODE.getErrorCode()) + " " + tipo;
	    		log.error(METHOD_NAME, msg, nre);
	    		throw new BeServiceException(E_ERRORI.NO_RESULT_CODE.getErrorCode(), msg);
	    	}
	    	log.debug(METHOD_NAME, "result delestatoId: %s", result.getDInformativaId());
	    	return result;
	    	
	    }
	    
		private void populateFilter(StringBuilder sb, Map<String, Object> params, String tipo, String sottoTipo) {
			if(StringUtils.isNotBlank(tipo)) {
				sb.append("AND c.consDTipoCon.tipoConsenso = :tipo ");
				params.put("tipo", tipo);
			}
			
			if(StringUtils.isNotBlank(sottoTipo)) {
				sb.append("AND c.consDSottoTipoCon.sottoTipoConsenso = :sottoTipo ");
				params.put("sottoTipo", sottoTipo);
			}
			

		}
}
