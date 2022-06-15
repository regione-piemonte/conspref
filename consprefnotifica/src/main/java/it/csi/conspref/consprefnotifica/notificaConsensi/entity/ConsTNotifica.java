/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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
 * The persistent class for the cons_t_notifica database table.
 * 
 */
@Entity
@Table(name="cons_t_notifica")
@NamedQuery(name="ConsTNotifica.findAll", query="SELECT c FROM ConsTNotifica c")
public class ConsTNotifica implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="CONS_T_NOTIFICA_NOTID_GENERATOR", allocationSize=1, sequenceName="CONS_T_NOTIFICA_NOT_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_T_NOTIFICA_NOTID_GENERATOR")
	@Column(name="not_id")
	private Integer notId;

	@Column(name="cf_cittadino")
	private String cfCittadino;

	@Column(name="cf_delegato")
	private String cfDelegato;

	@Column(name="cod_asr")
	private String codAsr;

//	@Column(name = "cons_uuid")
//	@org.hibernate.annotations.Type(type="pg-uuid")
//	private UUID consUuid;
	
	@Column(name = "request_id")
	private String consUuid;

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

	@Column(name="not_avvio")
	private Timestamp notAvvio;

	@Column(name="not_endp_url")
	private String notEndpUrl;

	@Column(name="not_fine")
	private Timestamp notFine;

	@Column(name="not_stato")
	private String notStato;

	@Column(name="operatore_id")
	private Integer operatoreId;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	@Column(name="tipo_stato")
	private String tipoStato;

	@Column(name="valore_consenso")
	private String valoreConsenso;

	//bi-directional many-to-one association to ConsDErroreTipo
	@ManyToOne
	@JoinColumn(name="err_tipo_id")
	private ConsDErroreTipo consDErroreTipo;

	//bi-directional many-to-one association to ConsTConsenso
	@ManyToOne
	@JoinColumn(name="cons_id")
	private ConsTConsenso consTConsenso;

	//bi-directional many-to-one association to ConsTNotificaErroreDett
	@OneToMany(mappedBy="consTNotifica")
	private List<ConsTNotificaErroreDett> consTNotificaErroreDetts;
	
	public ConsTNotifica() {
	}

	public Integer getNotId() {
		return this.notId;
	}

	public void setNotId(Integer notId) {
		this.notId = notId;
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

//	public Integer getConsId() {
//		return this.consId;
//	}
//
//	public void setConsId(Integer consId) {
//		this.consId = consId;
//	}

//	public UUID getConsUuid() {
//		return this.consUuid;
//	}
//
//	public void setConsUuid(UUID consUuid) {
//		this.consUuid = consUuid;
//	}
	
	

	public ConsTConsenso getConsTConsenso() {
		return consTConsenso;
	}

	public String getConsUuid() {
		return consUuid;
	}

	public void setConsUuid(String consUuid) {
		this.consUuid = consUuid;
	}

	public List<ConsTNotificaErroreDett> getConsTNotificaErroreDetts() {
		return consTNotificaErroreDetts;
	}

	public void setConsTNotificaErroreDetts(List<ConsTNotificaErroreDett> consTNotificaErroreDetts) {
		this.consTNotificaErroreDetts = consTNotificaErroreDetts;
	}

	public void setConsTConsenso(ConsTConsenso consTConsenso) {
		this.consTConsenso = consTConsenso;
	}

	public Integer getdInformativaId() {
		return dInformativaId;
	}

	public void setdInformativaId(Integer dInformativaId) {
		this.dInformativaId = dInformativaId;
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

	public Timestamp getNotAvvio() {
		return this.notAvvio;
	}

	public void setNotAvvio(Timestamp notAvvio) {
		this.notAvvio = notAvvio;
	}

	public String getNotEndpUrl() {
		return this.notEndpUrl;
	}

	public void setNotEndpUrl(String notEndpUrl) {
		this.notEndpUrl = notEndpUrl;
	}

	public Timestamp getNotFine() {
		return this.notFine;
	}

	public void setNotFine(Timestamp notFine) {
		this.notFine = notFine;
	}

	public String getNotStato() {
		return this.notStato;
	}

	public void setNotStato(String notStato) {
		this.notStato = notStato;
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

	public String getValoreConsenso() {
		return this.valoreConsenso;
	}

	public void setValoreConsenso(String valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
	}

	public ConsDErroreTipo getConsDErroreTipo() {
		return this.consDErroreTipo;
	}

	public void setConsDErroreTipo(ConsDErroreTipo consDErroreTipo) {
		this.consDErroreTipo = consDErroreTipo;
	}

}