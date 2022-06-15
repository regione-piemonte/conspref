/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cons_d_sotto_tipo_cons database table.
 * 
 */
@Entity
@Table(name="cons_d_sotto_tipo_cons")
@NamedQuery(name="ConsDSottoTipoCon.findAll", query="SELECT c FROM ConsDSottoTipoCon c")
public class ConsDSottoTipoCon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_D_SOTTO_TIPO_CONS_SOTTOTIPOCONSENSO_GENERATOR", sequenceName="CONS_D_SOTTO_TIPO_CONS_SOTTO_TIPO_CONSENSO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_SOTTO_TIPO_CONS_SOTTOTIPOCONSENSO_GENERATOR")
	@Column(name="sotto_tipo_consenso")
	private String sottoTipoConsenso;

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

	@Column(name="desc_sotto_tipo_cons")
	private String descSottoTipoCons;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsDInformativa
	@OneToMany(mappedBy="consDSottoTipoCon")
	private List<ConsDInformativa> consDInformativas;

	public ConsDSottoTipoCon() {
	}

	public String getSottoTipoConsenso() {
		return this.sottoTipoConsenso;
	}

	public void setSottoTipoConsenso(String sottoTipoConsenso) {
		this.sottoTipoConsenso = sottoTipoConsenso;
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

	public String getDescSottoTipoCons() {
		return this.descSottoTipoCons;
	}

	public void setDescSottoTipoCons(String descSottoTipoCons) {
		this.descSottoTipoCons = descSottoTipoCons;
	}

	public String getLoginOperazione() {
		return this.loginOperazione;
	}

	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
	}

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public List<ConsDInformativa> getConsDInformativas() {
		return this.consDInformativas;
	}

	public void setConsDInformativas(List<ConsDInformativa> consDInformativas) {
		this.consDInformativas = consDInformativas;
	}

	public ConsDInformativa addConsDInformativa(ConsDInformativa consDInformativa) {
		getConsDInformativas().add(consDInformativa);
		consDInformativa.setConsDSottoTipoCon(this);

		return consDInformativa;
	}

	public ConsDInformativa removeConsDInformativa(ConsDInformativa consDInformativa) {
		getConsDInformativas().remove(consDInformativa);
		consDInformativa.setConsDSottoTipoCon(null);

		return consDInformativa;
	}

}