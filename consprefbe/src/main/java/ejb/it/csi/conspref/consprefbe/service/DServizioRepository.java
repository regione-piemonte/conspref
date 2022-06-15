/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.csi.conspref.consprefbe.model.DServizio;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.LogUtil.ToLog;

@Stateless
public class DServizioRepository extends BaseRepository {

    @Inject
    private LogUtil log;

    @Inject
    private EntityManager em;

    
    public DServizio inserisciServizio(DServizio servizio, String loginOperazione){
    	final String METHOD_NAME = "inserisciServizio";
    	log.debug(METHOD_NAME, "inserisci servizio for: servCod =%s", (ToLog) () -> {
    				return servizio != null ? servizio.getServCod() : "null";
		});
    	servizio.setServId(null);
    	setCreazione(servizio, loginOperazione);
    	
    	em.persist(servizio);
    	return servizio;
    }
    
    
    public List<DServizio> ricercaServizi(DServizio dServizio)  {
    	final String METHOD_NAME = "getServizi";
		log.debug(METHOD_NAME, "get service for: servCod=%s", (ToLog) () -> {
			return dServizio != null ? dServizio.getServCod() : "null";
		});

    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT d FROM DServizio d ");
    	sb.append("WHERE d.dataCancellazione IS NULL ");
    			
    			
    	Map<String, Object> params = new HashMap<>();
    	populateFilter(sb, params, dServizio);
    	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	TypedQuery<DServizio> query = em.createQuery(jpql, DServizio.class);
    	params.entrySet().stream().forEach(e -> {
    		String key = e.getKey();
    		Object value = e.getValue();
    		query.setParameter(key, value);
    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
    	});
    	
    	List<DServizio> result = query.getResultList();
    	log.debug(METHOD_NAME, "result size: %s", result.size());
    	return result;
    	
    }
    
	
	private void populateFilter(StringBuilder sb, Map<String, Object> params, DServizio dServizio) {
		if(dServizio!=null) {
    		if(StringUtils.isNotBlank(dServizio.getServCod())) {
    			sb.append("AND d.servCod = :servCod ");
    			params.put("servCod", dServizio.getServCod());
    		}
    	}
		
	}
	
}
