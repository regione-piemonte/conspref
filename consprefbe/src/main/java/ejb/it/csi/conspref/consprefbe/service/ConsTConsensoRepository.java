/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import it.csi.conspref.consprefbe.model.ConsDFonte;
import it.csi.conspref.consprefbe.model.ConsDInformativa;
import it.csi.conspref.consprefbe.model.ConsDStato;
import it.csi.conspref.consprefbe.model.ConsTConsenso;
import it.csi.conspref.consprefbe.model.CsiLogAudit;
import it.csi.conspref.consprefbe.service.ConsDStatoRepository.ConsDStatoEnum;
import it.csi.conspref.consprefbe.util.E_ERRORI;
import it.csi.conspref.consprefbe.util.LogUtil;
import it.csi.conspref.consprefbe.util.LogUtil.ToLog;
import it.csi.conspref.consprefbe.util.PropertiesUtil;
import it.csi.conspref.consprefbe.ws.model.Fonte;
import it.csi.conspref.consprefbe.ws.model.Operatore;

@Stateless
public class ConsTConsensoRepository extends BaseRepository {

    @Inject
    private LogUtil log;
    
    @Inject
	private ConsDStatoRepository consDStatoRepository;

    @Inject
    private EntityManager em;
    
    @Inject
	protected PropertiesUtil properties;

    
    public ConsTConsenso inserisciConsenso(ConsTConsenso consenso, CsiLogAudit logaudit, String loginOperazione){
    	final String METHOD_NAME = "inserisciConsenso";
    	log.debug(METHOD_NAME, "inserisci consenso for: servCod =%s", (ToLog) () -> {
   				return consenso != null ? consenso.getCfCittadino() : "null";
		});

    	
    	consenso.setConsId(null);
    	consenso.setUuid(logaudit.getUuid());
    	consenso.setCsiLogAudit(logaudit);
    	setCreazione(consenso, loginOperazione);
    	
    	em.persist(consenso);
    	return consenso;
    }
    
    public ConsTConsenso aggiornaConsenso(ConsTConsenso consenso,ConsDStatoEnum statoEnum, CsiLogAudit logaudit, String loginOperazione){
    	final String METHOD_NAME = "aggiornaConsenso";
    	log.debug(METHOD_NAME, "aggiorna servizio for: uuid =%s", (ToLog) () -> {
    		return consenso != null ? consenso.getUuid() : "null";
		});
    	
    	
    	ConsTConsenso tConsensoBydb=em.find(ConsTConsenso.class, consenso.getConsId());
    	
    	setModifica(tConsensoBydb, loginOperazione);

    	if(tConsensoBydb == null) {
    		String msg = (String)properties.getProp().get(E_ERRORI.NO_RESULT_ID.getErrorCode()) + " " + consenso.getConsId();
			log.error(METHOD_NAME, msg);
    		throw new NoResultException(msg);
    	}    	

    	
    	
    	Timestamp now=new Timestamp(new Date().getTime());
    	tConsensoBydb.setDataFine(now);
    	ConsDStato stato=consDStatoRepository.ricercaStatoByCodice(statoEnum);
    	
    	tConsensoBydb.setConsDStato(stato);
    	
    	
    	em.merge(tConsensoBydb);

    	
    	return tConsensoBydb;
    }
    
    
    public List<ConsTConsenso> ricercaConsensoLocal(ConsTConsenso consenso)  {
    	final String METHOD_NAME = "ricercaConsensoLocal";
		log.debug(METHOD_NAME, "get consenso for: cfCittadino=%s", (ToLog) () -> {
			return consenso != null ? consenso.getCfCittadino() : "null";
		});
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT c FROM ConsTConsenso c ");
    	sb.append("WHERE c.dataCancellazione IS NULL ");
		sb.append("AND c.consDStato.tipoStato = 'A' ");
		sb.append("AND c.cfCittadino = :cfCittadino ");
		sb.append("AND c.consDAsr.codAsr = :tipoASR ");
		sb.append("AND EXISTS (SELECT d FROM ConsDInformativa d WHERE consDTipoCon.tipoConsenso = :tipoConsenso AND consDSottoTipoCon.sottoTipoConsenso = :sottoTipoConsenso AND d.dataCancellazione IS NULL AND (d.dataScadenza IS NULL OR d.dataScadenza >= ' " + new Timestamp(new Date().getTime()) + "' ))"); 	
		
		Map<String, Object> params = new HashMap<>();
		params.put("cfCittadino", consenso.getCfCittadino());
		params.put("tipoASR", consenso.getConsDAsr().getCodAsr());
		params.put("tipoConsenso", consenso.getConsDInformativa().getConsDTipoCon().getTipoConsenso());
		params.put("sottoTipoConsenso", consenso.getConsDInformativa().getConsDSottoTipoCon().getSottoTipoConsenso());
		
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	TypedQuery<ConsTConsenso> query = em.createQuery(jpql, ConsTConsenso.class);
    	params.entrySet().stream().forEach(e -> {
    		String key = e.getKey();
    		Object value = e.getValue();
    		query.setParameter(key, value);
    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
    	});
    	
    	List<ConsTConsenso> result = query.getResultList();

    	return result;
    	
    }
    
    public ConsTConsenso ricercaConsensoByUUID(UUID uuid) {
		final String METHOD_NAME = "ricercaConsensoByUUID";

		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT tab FROM ConsTConsenso tab ");
		sb.append("WHERE tab.dataCancellazione IS NULL ");
		sb.append("AND tab.uuid = :uuid ");
		sb.append("AND tab.consDStato.tipoStato = 'A' ");
		
		Map<String, Object> params = new HashMap<>();
		params.put("uuid", uuid);
		
		String jpql = sb.toString();
		TypedQuery<ConsTConsenso> query = em.createQuery(jpql, ConsTConsenso.class);
		params.entrySet().stream().forEach(e -> {
			String key = e.getKey();
			Object value = e.getValue();
			query.setParameter(key, value);
			log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
		});

		ConsTConsenso result;
		try {
			result = query.getSingleResult();
		} catch (NonUniqueResultException nure) {
			String msg = (String)properties.getProp().get(E_ERRORI.NOT_UNIQUE_RESULT_CODE.getErrorCode()) + " " + uuid;
			log.error(METHOD_NAME, msg, nure, new Object[] {});
			throw new IllegalArgumentException(msg);
		} catch (NoResultException nre) {
			String msg = (String)properties.getProp().get(E_ERRORI.NO_RESULT_CODE.getErrorCode()) + " " + uuid;
			log.debug(METHOD_NAME, msg, nre, new Object[] {});

			return null;
		}

		return result;
	}
    
    public ConsDFonte getFonte(Fonte fonte) {
    	final String METHOD_NAME = "getFonte";
		log.debug(METHOD_NAME, "get consenso for: codice = %s", (ToLog) () -> {
			return fonte != null ? fonte.getCodice() : "null";
		});
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT tab FROM ConsDFonte tab ");
		sb.append("WHERE tab.dataCancellazione IS NULL ");
		sb.append("AND tab.fonteCod = :fonteCod ");
		sb.append("AND tab.consDTipoFonte.tipofonteCod = :tipoConteCod ");
		
		Map<String, Object> params = new HashMap<>();
		params.put("fonteCod", fonte.getCodice());
		params.put("tipoConteCod", fonte.getTipoFonte().getCodice());
		
		String jpql = sb.toString();
		TypedQuery<ConsDFonte> query = em.createQuery(jpql, ConsDFonte.class);
		params.entrySet().stream().forEach(e -> {
			String key = e.getKey();
			Object value = e.getValue();
			query.setParameter(key, value);
			log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
		});

		ConsDFonte result;
		try {
			result = query.getSingleResult();
		} catch (NonUniqueResultException nure) {
			String msg = "Trovato piu' di un risultato per il codice  " + fonte.getCodice();
			log.error(METHOD_NAME, msg, nure, new Object[] {});
			throw new IllegalArgumentException(msg);
		} catch (NoResultException nre) {
			String msg = "Nessun risultato trovato per il codice " + fonte.getCodice();
			log.debug(METHOD_NAME, msg, nre, new Object[] {});

			return null;
		}

		return result;
		
    }
    
    public ConsDInformativa getInformativa(String tipoConsenso,String sottoTipoConsenso) {
    	final String METHOD_NAME = "getInformativa";
		log.debug(METHOD_NAME, "get informativa for: tipo Consenso/sottoTipoConsenso = %s", (ToLog) () -> {
			return tipoConsenso != null && sottoTipoConsenso != null ? tipoConsenso.concat("/").concat(sottoTipoConsenso) : "null";
		});
    	
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT tab FROM ConsDInformativa tab ");
		sb.append("WHERE tab.dataCancellazione IS NULL ");
		sb.append("AND (tab.dataScadenza IS NULL OR tab.dataScadenza >= '" + new Timestamp(new Date().getTime()) + "')");
		sb.append("AND tab.consDTipoCon.tipoConsenso = :tipoConsenso ");
		sb.append("AND tab.consDSottoTipoCon.sottoTipoConsenso = :sottoTipoConsenso ");
		
		Map<String, Object> params = new HashMap<>();
		params.put("tipoConsenso", tipoConsenso);
		params.put("sottoTipoConsenso", sottoTipoConsenso);
		
		String jpql = sb.toString();
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
		} catch (NonUniqueResultException nure) {
			String msg = "Trovato piu' di un risultato per tipoConsenso/sottoTipoConsenso:  " + tipoConsenso.concat("/").concat(sottoTipoConsenso);
			log.error(METHOD_NAME, msg, nure, new Object[] {});
			throw new IllegalArgumentException(msg);
		} catch (NoResultException nre) {
			String msg = "Nessun risultato trovato per tipoConsenso/sottoTipoConsenso: " + tipoConsenso.concat("/").concat(sottoTipoConsenso);
			log.debug(METHOD_NAME, msg, nre, new Object[] {});

			return null;
		}

		return result;
    }
    
    public List<ConsTConsenso> ricercaConsenso(String cfCittadino,String cfDelegato,Operatore operatore,Integer fonteId,Integer informativaId, Date dataAcquisizione,String codiceTipoConsenso,String codiceSottotipoConsenso, List<String> statiConsenso)  {
    	
    	final String METHOD_NAME = "ricercaConsenso";
		log.debug(METHOD_NAME, "get consenso for: cfCittadino=%s", (ToLog) () -> {
			return cfCittadino != null ? cfCittadino : "null";
		});
		
		

		
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT * FROM cons_t_consenso ");
    	sb.append(" WHERE data_cancellazione IS NULL ");
    	sb.append(cfCittadino!=null?" AND cf_cittadino = '" + cfCittadino + "'":" ");
    	sb.append(cfDelegato!=null?" AND cf_delegato  = '" + cfDelegato + "'":" ");
    	sb.append(operatore!=null && operatore.getCodiceOperatore()!=null && operatore.getTipoOperatore()!=null?" AND operatore_id = (SELECT operatore_id FROM cons_d_operatore WHERE tipo_operatore = '"+ operatore.getTipoOperatore() +"' AND cod_operatore = '" + operatore.getCodiceOperatore() + "' AND data_cancellazione is null)":"");
     	sb.append(" AND cons_id IN (SELECT cons_id FROM cons_t_consenso WHERE fonte_id = " + fonteId + " AND  tipo_stato = 'A'");
    	sb.append(informativaId!=null?" AND d_informativa_id =" + informativaId : " ");
    	sb.append(" )OR cons_id IN (SELECT cons_id FROM cons_t_consenso WHERE fonte_id = " + fonteId);
    	sb.append(informativaId!=null?" AND d_informativa_id =" + informativaId : " ");
    	sb.append(" AND tipo_stato = 'S' ORDER BY data_acquisizione DESC LIMIT 1) ");
    	sb.append(" OR cons_id IN (SELECT cons_id FROM cons_t_consenso WHERE fonte_id = " + fonteId);
    	sb.append(informativaId!=null?" AND d_informativa_id =" + informativaId : " ");
    	sb.append(" AND tipo_stato = 'R' ORDER BY data_acquisizione DESC LIMIT 1) ");

    	
    	
    	String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	@SuppressWarnings("unchecked")
		TypedQuery<ConsTConsenso> query = (TypedQuery<ConsTConsenso>) em.createNativeQuery(jpql, ConsTConsenso.class);
    	
    	List<ConsTConsenso> result = query.getResultList();
    	return result;
    }
    
    
    public List<ConsDInformativa> ricercaInformativa(String codiceTipoConsenso,String codiceSottotipoConsenso,Date dataRiferimento)  {
    	
    	final String METHOD_NAME = "ricercaInformativa";
		log.debug(METHOD_NAME, "get ricercaInformativa for: codice = %s", (ToLog) () -> {
			return codiceTipoConsenso != null ? codiceTipoConsenso : "null";
		});
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT tab FROM ConsDInformativa tab ");
		sb.append("WHERE tab.dataCancellazione IS NULL ");
		sb.append("AND tab.consDTipoCon.tipoConsenso = :codiceTipoConsenso ");
		sb.append("AND tab.consDSottoTipoCon.sottoTipoConsenso = :sottoTipoConsenso ");
		sb.append("AND (tab.dataScadenza IS NULL OR tab.dataScadenza >= ' " + new Timestamp(dataRiferimento.getTime()) + "')");
		
		Map<String, Object> params = new HashMap<>();
		params.put("codiceTipoConsenso", codiceTipoConsenso);
		params.put("sottoTipoConsenso", codiceSottotipoConsenso);
		
		String jpql = sb.toString();
    	log.debug(METHOD_NAME, "jpql: %s", jpql);
    	TypedQuery<ConsDInformativa> query = em.createQuery(jpql, ConsDInformativa.class);
    	params.entrySet().stream().forEach(e -> {
    		String key = e.getKey();
    		Object value = e.getValue();
    		query.setParameter(key, value);
    		log.debug(METHOD_NAME, "Param: key: %s, value: %s", key, value);
    	});
    	
    	List<ConsDInformativa> result = query.getResultList();
    	
    	return result;
    }
}
