/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


/**
 * The persistent class for the cons_s_consenso database table.
 * 
 */
@Entity
@Table(name="cons_s_consenso")
@NamedQuery(name="ConsSConsenso.findAll", query="SELECT c FROM ConsSConsenso c")
public class ConsSConsenso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_S_CONSENSO_CONSSID_GENERATOR", sequenceName="CONS_S_CONSENSO_CONSS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_S_CONSENSO_CONSSID_GENERATOR")
	@Column(name="conss_id")
	private Integer conssId;

	@Column(name="audit_id")
	private Integer auditId;

	@Column(name="cf_cittadino")
	private String cfCittadino;

	@Column(name="cf_delegato")
	private String cfDelegato;

	@Column(name="cod_asr")
	private String codAsr;

	private String cognome;

	@Column(name="cons_id")
	private Integer consId;

	@Column(name="d_informativa_id")
	private Integer dInformativaId;

	@Column(name="data_acquisizione")
	private Timestamp dataAcquisizione;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_fine")
	private Timestamp dataFine;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="fonte_id")
	private Integer fonteId;

	@Column(name="id_aura")
	private String idAura;

	@Column(name="login_operazione")
	private String loginOperazione;

	private String nome;

	@Column(name="operatore_id")
	private Integer operatoreId;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	@Column(name="tipo_stato")
	private String tipoStato;

	@Column(name = "uuid")
	@org.hibernate.annotations.Type(type="pg-uuid")
	private UUID uuid;

	@Column(name="valore_consenso")
	private String valoreConsenso;

	public ConsSConsenso() {
	}

	public Integer getConssId() {
		return this.conssId;
	}

	public void setConssId(Integer conssId) {
		this.conssId = conssId;
	}

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getCfCittadino() {
		return this.cfCittadino;
	}

	public void setCfCittadino(String cfCittadino) {
		this.cfCittadino = cfCittadino;
	}

	public String getCfDelegato() {
		return this.cfDelegato;
	}

	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}

	public String getCodAsr() {
		return this.codAsr;
	}

	public void setCodAsr(String codAsr) {
		this.codAsr = codAsr;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getConsId() {
		return this.consId;
	}

	public void setConsId(Integer consId) {
		this.consId = consId;
	}

	public Integer getDInformativaId() {
		return this.dInformativaId;
	}

	public void setDInformativaId(Integer dInformativaId) {
		this.dInformativaId = dInformativaId;
	}

	public Timestamp getDataAcquisizione() {
		return this.dataAcquisizione;
	}

	public void setDataAcquisizione(Timestamp dataAcquisizione) {
		this.dataAcquisizione = dataAcquisizione;
	}

	public Timestamp getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Timestamp dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Timestamp getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Integer getFonteId() {
		return this.fonteId;
	}

	public void setFonteId(Integer fonteId) {
		this.fonteId = fonteId;
	}

	public String getIdAura() {
		return this.idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getLoginOperazione() {
		return this.loginOperazione;
	}

	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getOperatoreId() {
		return this.operatoreId;
	}

	public void setOperatoreId(Integer operatoreId) {
		this.operatoreId = operatoreId;
	}

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public String getTipoStato() {
		return this.tipoStato;
	}

	public void setTipoStato(String tipoStato) {
		this.tipoStato = tipoStato;
	}

	public UUID getUuid() {
		return this.uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getValoreConsenso() {
		return this.valoreConsenso;
	}

	public void setValoreConsenso(String valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
	}

}