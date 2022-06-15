/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cons_t_consenso database table.
 * 
 */
@Entity
@Table(name="cons_t_consenso")
@NamedQuery(name="ConsTConsenso.findAll", query="SELECT c FROM ConsTConsenso c")
public class ConsTConsenso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_T_CONSENSO_CONSID_GENERATOR", sequenceName="CONS_T_CONSENSO_CONS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_T_CONSENSO_CONSID_GENERATOR")
	@Column(name="cons_id")
	private Integer consId;

	@Column(name="cf_cittadino")
	private String cfCittadino;

	@Column(name="cf_delegato")
	private String cfDelegato;

	private String cognome;

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

	@Column(name="id_aura")
	private String idAura;

	@Column(name="login_operazione")
	private String loginOperazione;

	private String nome;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	@Column(name = "uuid")
	@org.hibernate.annotations.Type(type="pg-uuid")
	private UUID uuid;

	//bi-directional many-to-one association to ConsDAsr
	@ManyToOne
	@JoinColumn(name="cod_asr")
	private ConsDAsr consDAsr;

	//bi-directional many-to-one association to ConsDFonte
	@ManyToOne
	@JoinColumn(name="fonte_id")
	private ConsDFonte consDFonte;

	//bi-directional many-to-one association to ConsDInformativa
	@ManyToOne
	@JoinColumn(name="d_informativa_id")
	private ConsDInformativa consDInformativa;

	//bi-directional many-to-one association to ConsDOperatore
	@ManyToOne
	@JoinColumn(name="operatore_id")
	private ConsDOperatore consDOperatore;

	//bi-directional many-to-one association to ConsDStato
	@ManyToOne
	@JoinColumn(name="tipo_stato")
	private ConsDStato consDStato;

	//bi-directional many-to-one association to ConsDValoreCon
	@ManyToOne
	@JoinColumn(name="valore_consenso")
	private ConsDValoreCon consDValoreCon;

	//bi-directional many-to-one association to CsiLogAudit
	@ManyToOne
	@JoinColumn(name="audit_id")
	private CsiLogAudit csiLogAudit;
	
	//bi-directional many-to-one association to ConsTNotifica
	@OneToMany(mappedBy="consTConsenso")
	private List<ConsTNotifica> consTNotificas;

	public ConsTConsenso() {
	}

	public Integer getConsId() {
		return this.consId;
	}

	public void setConsId(Integer consId) {
		this.consId = consId;
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

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public UUID getUuid() {
		return this.uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public ConsDAsr getConsDAsr() {
		return this.consDAsr;
	}

	public void setConsDAsr(ConsDAsr consDAsr) {
		this.consDAsr = consDAsr;
	}

	public ConsDFonte getConsDFonte() {
		return this.consDFonte;
	}

	public void setConsDFonte(ConsDFonte consDFonte) {
		this.consDFonte = consDFonte;
	}

	public ConsDInformativa getConsDInformativa() {
		return this.consDInformativa;
	}

	public void setConsDInformativa(ConsDInformativa consDInformativa) {
		this.consDInformativa = consDInformativa;
	}

	public ConsDOperatore getConsDOperatore() {
		return this.consDOperatore;
	}

	public void setConsDOperatore(ConsDOperatore consDOperatore) {
		this.consDOperatore = consDOperatore;
	}

	public ConsDStato getConsDStato() {
		return this.consDStato;
	}

	public void setConsDStato(ConsDStato consDStato) {
		this.consDStato = consDStato;
	}

	public ConsDValoreCon getConsDValoreCon() {
		return this.consDValoreCon;
	}

	public void setConsDValoreCon(ConsDValoreCon consDValoreCon) {
		this.consDValoreCon = consDValoreCon;
	}

	public CsiLogAudit getCsiLogAudit() {
		return this.csiLogAudit;
	}

	public void setCsiLogAudit(CsiLogAudit csiLogAudit) {
		this.csiLogAudit = csiLogAudit;
	}
	
	
	public List<ConsTNotifica> getConsTNotificas() {
		return this.consTNotificas;
	}

	public void setConsTNotificas(List<ConsTNotifica> consTNotificas) {
		this.consTNotificas = consTNotificas;
	}


}