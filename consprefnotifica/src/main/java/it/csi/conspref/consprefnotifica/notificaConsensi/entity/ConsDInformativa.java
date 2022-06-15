/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cons_d_informativa database table.
 * 
 */
@Entity
@Table(name="cons_d_informativa")
@NamedQuery(name="ConsDInformativa.findAll", query="SELECT c FROM ConsDInformativa c")
public class ConsDInformativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_D_INFORMATIVA_DINFORMATIVAID_GENERATOR", sequenceName="CONS_D_INFORMATIVA_INFORMATIVA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_INFORMATIVA_DINFORMATIVAID_GENERATOR")
	@Column(name="d_informativa_id")
	private Integer dInformativaId;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_decorrenza")
	private Timestamp dataDecorrenza;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="data_scadenza")
	private Timestamp dataScadenza;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="pdf_informativa")
	private String pdfInformativa;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsDSottoTipoCon
	@ManyToOne
	@JoinColumn(name="sotto_tipo_consenso")
	private ConsDSottoTipoCon consDSottoTipoCon;

	//bi-directional many-to-one association to ConsDTipoCon
	@ManyToOne
	@JoinColumn(name="tipo_consenso")
	private ConsDTipoCon consDTipoCon;

	//bi-directional many-to-one association to ConsTConsenso
	@OneToMany(mappedBy="consDInformativa")
	private List<ConsTConsenso> consTConsensos;

	public ConsDInformativa() {
	}

	public Integer getDInformativaId() {
		return this.dInformativaId;
	}

	public void setDInformativaId(Integer dInformativaId) {
		this.dInformativaId = dInformativaId;
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

	public Timestamp getDataDecorrenza() {
		return this.dataDecorrenza;
	}

	public void setDataDecorrenza(Timestamp dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Timestamp getDataScadenza() {
		return this.dataScadenza;
	}

	public void setDataScadenza(Timestamp dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getLoginOperazione() {
		return this.loginOperazione;
	}

	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
	}

	public String getPdfInformativa() {
		return this.pdfInformativa;
	}

	public void setPdfInformativa(String pdfInformativa) {
		this.pdfInformativa = pdfInformativa;
	}

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public ConsDSottoTipoCon getConsDSottoTipoCon() {
		return this.consDSottoTipoCon;
	}

	public void setConsDSottoTipoCon(ConsDSottoTipoCon consDSottoTipoCon) {
		this.consDSottoTipoCon = consDSottoTipoCon;
	}

	public ConsDTipoCon getConsDTipoCon() {
		return this.consDTipoCon;
	}

	public void setConsDTipoCon(ConsDTipoCon consDTipoCon) {
		this.consDTipoCon = consDTipoCon;
	}

	public List<ConsTConsenso> getConsTConsensos() {
		return this.consTConsensos;
	}

	public void setConsTConsensos(List<ConsTConsenso> consTConsensos) {
		this.consTConsensos = consTConsensos;
	}

	public ConsTConsenso addConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().add(consTConsenso);
		consTConsenso.setConsDInformativa(this);

		return consTConsenso;
	}

	public ConsTConsenso removeConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().remove(consTConsenso);
		consTConsenso.setConsDInformativa(null);

		return consTConsenso;
	}

}