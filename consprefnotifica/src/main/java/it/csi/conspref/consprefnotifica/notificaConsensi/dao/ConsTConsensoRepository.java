/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTConsenso;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTEndpoint;
import it.csi.conspref.consprefnotifica.util.LogUtil;


@Component
public class ConsTConsensoRepository extends BaseRepository {

    @Autowired
    private LogUtil log;
    
    @Autowired
    private EntityManager em;
    
    public List<ConsTConsenso> findAll()  {
    	
    	final String METHOD_NAME = "findAll";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT c FROM ConsTConsenso c");
    	
    	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTConsenso> query = (TypedQuery<ConsTConsenso>) em.createNativeQuery(jpql, it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTConsenso.class);
    	
    	List<ConsTConsenso> result = new ArrayList<ConsTConsenso>();
    	try {
		
    		result = query.getResultList();
        	
    		
		} catch (Exception e) {

		}
    	
    	return result;

    }
    
    public List<ConsTConsenso> ricercaConsensiDaNotificare(String codiceStatoConsenso)  {
    	
    	final String METHOD_NAME = "ricercaConsensiDaNotificare";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
		
		sb.append("select * from cons_t_consenso a where ");
		sb.append(" a.tipo_stato in (:codiceStatoConsenso) ");
		sb.append(" and  ( ");
		sb.append(" not exists (select 1 from cons_t_notifica c where c.cons_id=a.cons_id) "); 
		sb.append(" or exists (select 1 from cons_t_notifica d where d.cons_id=d.cons_id ");
		sb.append(" and d.not_stato='ERRORE')); ");

		Map<String, Object> params = new HashMap<>();
		params.put("codiceStatoConsenso", codiceStatoConsenso);
    	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTConsenso> query = (TypedQuery<ConsTConsenso>) em.createNativeQuery(jpql, ConsTConsenso.class);
    	
    	params.entrySet().stream().forEach(e -> {
    		String key = e.getKey();
    		Object value = e.getValue();
    		query.setParameter(key, value);
    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
    	});
    	
    	List<ConsTConsenso> result = query.getResultList();
    	return result;
    }
    
    
    public List<ConsTConsenso> ricercaConsensiDaNotificare()  {
    	
    	final String METHOD_NAME = "ricercaConsensiDaNotificare";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
		
		sb.append("select * from cons_t_consenso a where ");
		sb.append(" a.tipo_stato in ('A','R') ");
		sb.append(" and  ( ");
		sb.append(" not exists (select 1 from cons_t_notifica c where c.cons_id=a.cons_id) "); 
		sb.append(" or exists (select 1 from cons_t_notifica d where d.cons_id=d.cons_id ");
		sb.append(" and d.not_stato='ERRORE')) order by a.cons_id ASC; ");

		Map<String, Object> params = new HashMap<>();

    	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTConsenso> query = (TypedQuery<ConsTConsenso>) em.createNativeQuery(jpql, ConsTConsenso.class);
    	
    	
    	List<ConsTConsenso> result = query.getResultList();
    	return result;
    }
    
public List<ConsTConsenso> ricercaConsensiDaNotificarePerAzienda(String filtroPerAzienda)  {
    	
    	final String METHOD_NAME = "ricercaConsensiDaNotificarePerAzienda";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();

		sb.append("select * from cons_t_consenso a where ");
		sb.append(" a.tipo_stato in ('A','R') ");
		if (filtroPerAzienda != null && filtroPerAzienda.length()>1) {
			sb.append(" and  a.cod_asr= '"+(filtroPerAzienda+"'"));
		}
		sb.append(" and  ( ");
		sb.append(" not exists (select 1 from cons_t_notifica c where c.cons_id=a.cons_id) "); 
		sb.append(" or exists (select 1 from cons_t_notifica d where d.cons_id=d.cons_id ");
		sb.append(" and d.not_stato='TODO')) order by a.cons_id ASC; ");

		Map<String, Object> params = new HashMap<>();

    	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTConsenso> query = (TypedQuery<ConsTConsenso>) em.createNativeQuery(jpql, ConsTConsenso.class);
    	
    	
    	List<ConsTConsenso> result = query.getResultList();
    	return result;
    }
    
    
    public List<String> ricercaEndpointurlByCodAsr(String codAsr){
    	
    	final String METHOD_NAME = "ricercaEndpointurlByCodAsr";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT * FROM cons_t_endpoint a , cons_d_asr b ");
    	sb.append("WHERE a.data_cancellazione IS NULL ");
		sb.append("AND a.cod_asr =:codAsr and a.cod_asr = b.cod_asr ");
		
		Map<String, Object> params = new HashMap<>();
		params.put("codAsr", codAsr);
  	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTEndpoint> query = (TypedQuery<ConsTEndpoint>) em.createNativeQuery(jpql, ConsTEndpoint.class);
    	
    	params.entrySet().stream().forEach(e -> {
    		String key = e.getKey();
    		Object value = e.getValue();
    		query.setParameter(key, value);
    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
    	});
    	
    	List<ConsTEndpoint> result = query.getResultList();
    	
    	List<String> endpoints= new ArrayList<>();
    	if(result!=null && !result.isEmpty()) {
    		for (ConsTEndpoint tconsendpoint : result) {
				endpoints.add(tconsendpoint.getEndpUrl());
    		}
    	}
    	
    	return endpoints;
   	
    }
    
    
    
    public List<ConsTEndpoint> ricercaAllEndpointurl(){
    	
    	final String METHOD_NAME = "ricercaAllEndpointurl";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT * FROM cons_t_endpoint a , cons_d_asr b ");
    	sb.append("WHERE a.data_cancellazione IS NULL ");

		
		  	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTEndpoint> query = (TypedQuery<ConsTEndpoint>) em.createNativeQuery(jpql, ConsTEndpoint.class);
    	
    	List<ConsTEndpoint> result = query.getResultList();
    	
    	return result;
   	
    }
    
public List<ConsTEndpoint> ricercaEndpointPerAsr(String codAsr){
    	
    	final String METHOD_NAME = "ricercaAEndpointurl";
    	
    	log.info(METHOD_NAME, " Start");
    	log.info("codAsr=",codAsr);
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT * FROM cons_t_endpoint a  ");
    	sb.append("WHERE a.data_cancellazione IS NULL AND a.cod_asr ='"+codAsr+"'");

		
    	String jpql = sb.toString();
    	log.info(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTEndpoint> query = (TypedQuery<ConsTEndpoint>) em.createNativeQuery(jpql, ConsTEndpoint.class);
    	
    	List<ConsTEndpoint> result = query.getResultList();
    	
    	return result;
   	
    }
    

}
