/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.conspref.Errore;
import it.csi.conspref.consprefnotifica.notificaConsensi.EsitoEnum;
import it.csi.conspref.consprefnotifica.notificaConsensi.NotificaStatoEnum;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsDErroreTipo;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTConsenso;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTNotifica;
import it.csi.conspref.consprefnotifica.notificaConsensi.entity.ConsTNotificaErroreDett;
import it.csi.conspref.consprefnotifica.util.LogUtil;

@Component
public class ConsTNotificaRepository extends BaseRepository {
	
    @Autowired
    private LogUtil log;
    
    @Autowired
    private EntityManager em;
    
          
    public ConsTNotifica inserisciNotifica(ConsTNotifica notifica, List<ConsTNotificaErroreDett> errori, String esito){
    	
    	final String METHOD_NAME = "inserisciNotifica";
    	log.debug(METHOD_NAME, "inserisci notifica for: consUUID: "+ notifica!=null ? notifica.getConsUuid(): " --> null");


    	
    	if(!esito.equalsIgnoreCase(EsitoEnum.SUCCESSO.getCodice())) {
    		
    		log.info(METHOD_NAME, "Decodifico codice di warning o di EROORE, "+esito!=null?esito:" --> null");
			ConsDErroreTipo consDErroreTipo= (ConsDErroreTipo)em.createNamedQuery("ConsDErroreTipo.findByCodiceErroreTipo").setParameter("errCodeTipo", esito)
			    .getSingleResult();
			
			notifica.setConsDErroreTipo(consDErroreTipo);
    	}

		setCreazione(notifica);
    	
    	em.persist(notifica);
    	

    	
    	for (ConsTNotificaErroreDett err : errori) {
    		err.setConsTNotifica(notifica);
			em.persist(err);
		}
    	
    	
    	return notifica;
    }
    
    public ConsTNotifica aggiornaNotifica(Integer  notificaId, String notificaStato, Integer erroreTipoId){
    	final String METHOD_NAME = "aggiornaNotifica";
    	
    	log.debug(METHOD_NAME, "aggiorna stato notifica for id =" + notificaId);
    	

    	ConsTNotifica tNotficaBydb=em.find(ConsTNotifica.class, notificaId);
    	
    	if(tNotficaBydb == null) {
    		String msg="Nessuna notifica trovata per id:"  + notificaId;
    		log.error(METHOD_NAME, msg);
    		throw new NoResultException(msg);
    	}    
    	
    	setModifica(tNotficaBydb);
    	tNotficaBydb.setNotStato(notificaStato);
    	if(erroreTipoId!=null) {
    		ConsDErroreTipo consDErroreTipo = new ConsDErroreTipo(); 
    		consDErroreTipo.setErrTipoId(erroreTipoId);
    		tNotficaBydb.setConsDErroreTipo(consDErroreTipo);
    	}
    	
    	em.merge(tNotficaBydb);
   	
    	return tNotficaBydb;
    }
    
    public List<ConsTNotifica> ricercaNotificheDaInviare(String startDate)  {
    	
    	final String METHOD_NAME = "ricercaNotificheDaInviare";
    	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct * from cons_t_notifica a where ");
		sb.append(" a.not_stato ='C' and  a.data_modifica>='"+startDate);
		sb.append("' order by cf_cittadino,cod_asr,not_id ASC; ");

		Map<String, Object> params = new HashMap<>();
   	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTNotifica> query = (TypedQuery<ConsTNotifica>) em.createNativeQuery(jpql, ConsTNotifica.class);
    	
    	
    	List<ConsTNotifica> result = query.getResultList();
    	return result;
    }

    public List<Object[]> getStatoNotifiche(String cfCittadino, List<String> uuid)  {
    	final String METHOD_NAME = "getStatoNotifiche";
   	
    	log.info(METHOD_NAME, " Start");
		
    	StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT d.cod_asr,t.id_app as dipartimentale,d.tipo_stato as stato_consenso,n.not_stato as stato_notifica, ");
		sb.append(" (CASE WHEN n.not_stato = 'C' THEN 'CONSEGNATO' WHEN n.not_stato = 'E' THEN 'ERRORE'  ELSE 'IN ATTESA DI INVIO' END) as stato_decodificato ,d.cons_id, d.uuid, d.cf_cittadino ");
		sb.append(" FROM cons_t_notifica n  FULL OUTER JOIN cons_t_consenso d ON d.cons_id = n.cons_id, cons_t_endpoint t ");
		sb.append(" where t.cod_asr=d.cod_asr and d.cf_cittadino='xyz' and t.data_cancellazione is null ");
		
		Map<String, Object> params = new HashMap<>();
   	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<Object> query = (TypedQuery<Object>) em.createNativeQuery(jpql);
    	
    	
    	List<Object> result = query.getResultList();
    	return null;
    }
    
}
