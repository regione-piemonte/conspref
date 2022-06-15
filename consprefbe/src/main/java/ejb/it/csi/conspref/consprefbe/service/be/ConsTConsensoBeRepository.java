/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.be;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.csi.conspref.consprefbe.model.ConsDInformativa;
import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.PropertiesUtil;

@Stateless
public class ConsTConsensoBeRepository {
	   @Inject
	    private LogUtil log;
	    
	    @Inject
		private ConsDStatoRepository consDStatoRepository;

	    @Inject
	    private EntityManager em;
	    
	    @Inject
		protected PropertiesUtil properties;
	    

	    public List<ConsTConsenso> ricercaConsensiPerIdInformativa(String cf, String idInformativa){
	    	final String METHOD_NAME = "ricercaConsensiPerIdInformativa";
	    	log.info(METHOD_NAME, "BEGIN - cf = %s idInformativa = %s", cf, idInformativa);
	    	
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("SELECT c FROM ConsTConsenso c ");
	    	sb.append(" WHERE c.cfCittadino = :codiceFiscale ");
	    	sb.append(" and c.consDInformativa.dInformativaId = :idInformativa ");
	    	sb.append(" AND c.dataFine is null ");
	    	
			Map<String, Object> params = new HashMap<>();
			params.put("codiceFiscale", cf);
			params.put("idInformativa", Integer.parseInt(idInformativa) );
			
			String jpql = sb.toString();
	    	log.debug(METHOD_NAME, "jpql: %s", jpql);
	    	TypedQuery<ConsTConsenso> query = em.createQuery(jpql, ConsTConsenso.class);
	    	params.entrySet().stream().forEach(e -> {
	    		String key = e.getKey();
	    		Object value = e.getValue();
	    		query.setParameter(key, value);
	    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
	    	});
	    	
	    	List<ConsTConsenso> listaConsensi = query.getResultList();
	    	
	    	return listaConsensi;
	    }
	    
	    

	    public List<ConsDInformativa> ricercaInformativeValide() {
	    	final String METHOD_NAME = "ricercaInformativaValide";
			log.debug(METHOD_NAME, "BEGIN ");
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT tab FROM ConsDInformativa tab ");
			sb.append("WHERE tab.dataScadenza IS NULL or dataScadenza >= now() ");
					
			String jpql = sb.toString();
	    	log.debug(METHOD_NAME, "jpql: %s", jpql);
	    	TypedQuery<ConsDInformativa> query = em.createQuery(jpql, ConsDInformativa.class);
	    	
	    	List<ConsDInformativa> result = query.getResultList();
	    	
	    	return result;
	    }	
	    

}
