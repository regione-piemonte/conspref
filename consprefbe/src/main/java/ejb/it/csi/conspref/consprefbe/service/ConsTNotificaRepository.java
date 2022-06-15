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
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.model.DServizio;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.LogUtil.ToLog;
import it.csi.conspref.consprefbe.util.StatoNotificaBean;

import java.nio.charset.Charset;
import java.sql.Types;


@Stateless
public class ConsTNotificaRepository extends BaseRepository {

	@Inject
    private LogUtil log;

    @Inject
    private EntityManager em;
    
    public List<Object[]> getStatoNotifiche(String cfCittadino, List<String> uuid)  {
    	final String METHOD_NAME = "getStatoNotifiche";
    	log.info(METHOD_NAME, " Start");
    	
		log.debug(METHOD_NAME, "getStatoNotifiche for: cfCittadino=%s", (ToLog) () -> {
			return cfCittadino != null ? cfCittadino : "null";
		});
       
        StringBuilder sb = new StringBuilder();
        sb.append("select d.cons_id as cons_id, " + 
        		"CAST (d.uuid AS varchar) n_uuid, " + 
        		"d.cod_asr, " + 
        		"t.id_app as dipartimentale, " + 
        		"d.tipo_stato as stato_consenso, " + 
        		"n.not_stato as stato_notifica, " + 
        		"(CASE WHEN n.not_stato = 'C' THEN 'CONSEGNATO' " + 
        		"WHEN n.not_stato = 'E' THEN 'ERRORE' " + 
        		"WHEN d.tipo_stato = 'S' THEN 'MODIFICATO' " + 
        		"ELSE 'IN ATTESA DI INVIO' " + 
        		"END) as stato_decodificato " + 
        		"from cons_t_consenso d, " + 
        		"cons_t_endpoint t, " + 
        		"cons_t_notifica n " + 
        		"where t.cod_asr = d.cod_asr and " + 
        		"n.not_endp_url = t.endp_url and " + 
        		
        		"d.data_cancellazione is null and " + 
        		"n.data_cancellazione is null and " + 
        		"n.cons_id = d.cons_id and " + 
        		"d.cf_cittadino = '"+cfCittadino+"' and " + 
        		"d.uuid in (");
        populateFilter(sb, uuid);
       
        sb.append("union " + 
        		"select d.cons_id as cons_id, " + //privo di notifiche
        		"CAST (d.uuid AS varchar) n_uuid, " + 
        		"d.cod_asr, " + 
        		"t.id_app as dipartimentale, " + 
        		"d.tipo_stato as stato_consenso, " + 
        		"'A' as stato_notifica, " + 
        		"'IN ATTESA DI INVIO' stato_decodificato " + 
        		"from cons_t_consenso d, " + 
        		"cons_t_endpoint t " + 
        		"where t.cod_asr = d.cod_asr and " + 
        		"t.data_cancellazione is null and " + 
        		"d.data_cancellazione is null and " + 
        		"d.cf_cittadino = '"+cfCittadino+"' and " + 
        		"d.uuid in (");
                populateFilter(sb, uuid);
        sb.append(" and " + 
        		"not exists ( " + 
        		"select 1 " + 
        		"from cons_t_notifica n " + 
        		"where n.cons_id = d.cons_id " + 
        		") ");
        //Map<String, Object> params = new HashMap<>();
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
    	
    	TypedQuery<Object[]> query = (TypedQuery<Object[]>)em.createNativeQuery(jpql);
    	
    	
    	

    	
    	
    	List<Object[]> result = query.getResultList();
    	log.debug(METHOD_NAME, "result size: %s", result.size());
    	return result;
    	
    }
    
    private void populateFilter(StringBuilder sb,List<String> uuid) {
    	int numeroElementi=uuid.toArray().length;
    	int i=1;
    	for (String suuid: uuid) {
    		if(StringUtils.isNotBlank(suuid)){
    			sb.append("'"+suuid+"'");
    		}
    		if(i<numeroElementi) {
    			sb.append(",");
    		}else {
    			
    			sb.append(")");
    		}
    		i++;
    	}
	}
    
}
