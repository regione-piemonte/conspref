/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the csi_log_audit database table.
 * 
 */
@Entity
@Table(name="csi_log_audit")
@NamedQuery(name="CsiLogAudit.findAll", query="SELECT c FROM CsiLogAudit c")
public class CsiLogAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSI_LOG_AUDIT_AUDITID_GENERATOR", sequenceName="CSI_LOG_AUDIT_AUDIT_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSI_LOG_AUDIT_AUDITID_GENERATOR")
	@Column(name="audit_id")
	private Integer auditId;

	@Column(name="data_ora")
	private Timestamp dataOra;

	@Column(name="id_app")
	private String idApp;

	private String idrequest;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="key_oper")
	private String keyOper;

	@Column(name="ogg_oper")
	private String oggOper;

	private String operazione;

	private String proprietario;

	private String ruolo;

	private String utente;

	@Column(name = "uuid")
	@org.hibernate.annotations.Type(type="pg-uuid")
	private UUID uuid;

	//bi-directional many-to-one association to ConsTConsenso
	@OneToMany(mappedBy="csiLogAudit")
	private List<ConsTConsenso> consTConsensos;

	public CsiLogAudit() {
	}

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public Timestamp getDataOra() {
		return this.dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getIdApp() {
		return this.idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public String getIdrequest() {
		return this.idrequest;
	}

	public void setIdrequest(String idrequest) {
		this.idrequest = idrequest;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getKeyOper() {
		return this.keyOper;
	}

	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

	public String getOggOper() {
		return this.oggOper;
	}

	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	public String getOperazione() {
		return this.operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public String getProprietario() {
		return this.proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public UUID getUuid() {
		return this.uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public List<ConsTConsenso> getConsTConsensos() {
		return this.consTConsensos;
	}

	public void setConsTConsensos(List<ConsTConsenso> consTConsensos) {
		this.consTConsensos = consTConsensos;
	}

	public ConsTConsenso addConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().add(consTConsenso);
		consTConsenso.setCsiLogAudit(this);

		return consTConsenso;
	}

	public ConsTConsenso removeConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().remove(consTConsenso);
		consTConsenso.setCsiLogAudit(null);

		return consTConsenso;
	}

}