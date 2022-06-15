/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.csi.conspref.consprefbe.model.ConsDValoreCon;
import it.csi.conspref.consprefbe.service.exception.BeServiceException;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.PropertiesUtil;

@Stateless
public class ConsDValoreConRepository {
	    
		@Inject
	    private LogUtil log;

	    @Inject
	    private EntityManager em;
	    
	    @Inject
		protected PropertiesUtil properties;
	    
	    
	    public enum ConsDValoreConsEnum {
	    	NO
	    }


	    
	    public ConsDValoreCon ricercaValoreConsByCodice(String codiceEnum)  {
	    	final String METHOD_NAME = "ricercaValoreConsByCodice";
			log.debug(METHOD_NAME, "get service for: codiceEnum=%s", codiceEnum);

	    	StringBuilder sb = new StringBuilder();
	    	sb.append("SELECT c FROM ConsDValoreCon c ");
	    	
	    	Map<String, Object> params = new HashMap<>();
	    	populateWhere(sb, params, codiceEnum);
	    			
	    	
	    	String jpql = sb.toString();
	    	log.debug(METHOD_NAME, "jpql: %s", jpql);
	    	TypedQuery<ConsDValoreCon> query = em.createQuery(jpql, ConsDValoreCon.class);
	    	params.entrySet().stream().forEach(e -> {
	    		String key = e.getKey();
	    		Object value = e.getValue();
	    		query.setParameter(key, value);
	    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
	    	});
	    	ConsDValoreCon result;
	    	try {
	    		result = query.getSingleResult();
	    	} catch(NonUniqueResultException nure) {
	    		String msg = (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT_CODE.getErrorCode()) + " - " + codiceEnum;
	    		log.error(METHOD_NAME, msg, nure);
	    		throw new BeServiceException(E_ERRORI.NOT_UNIQUE_RESULT_CODE.getErrorCode(), msg);
	    	} catch(NoResultException nre) {
	    		String msg = (String)properties.getProp().get(E_ERRORI.R_VALORE_CONSENSO_WRONG.getErrorCode()) + " - " + codiceEnum;
	    		log.error(METHOD_NAME, msg, nre);
	    		throw new BeServiceException(E_ERRORI.R_VALORE_CONSENSO_WRONG.getErrorCode(), msg);
	    	}
	    	log.debug(METHOD_NAME, "result delestatoId: %s", result.getValoreConsenso());
	    	return result;
	    	
	    }
	    
	    private void populateWhere(StringBuilder sb, Map<String, Object> params, String codice) {
			if(StringUtils.isNotBlank(codice)) {
				sb.append("WHERE c.valoreConsenso = :codice ");
				params.put("codice", codice);
			}
		}
		

}
