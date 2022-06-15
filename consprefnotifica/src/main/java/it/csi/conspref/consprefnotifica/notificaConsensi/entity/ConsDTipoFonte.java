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
 * The persistent class for the cons_d_tipo_fonte database table.
 * 
 */
@Entity
@Table(name="cons_d_tipo_fonte")
@NamedQuery(name="ConsDTipoFonte.findAll", query="SELECT c FROM ConsDTipoFonte c")
public class ConsDTipoFonte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_D_TIPO_FONTE_TIPOFONTEID_GENERATOR", sequenceName="CONS_D_TIPO_FONTE_TIPO_FONTE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_TIPO_FONTE_TIPOFONTEID_GENERATOR")
	@Column(name="tipo_fonte_id")
	private Integer tipoFonteId;

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

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	@Column(name="tipofonte_cod")
	private String tipofonteCod;

	@Column(name="tipofonte_desc")
	private String tipofonteDesc;

	//bi-directional many-to-one association to ConsDFonte
	@OneToMany(mappedBy="consDTipoFonte")
	private List<ConsDFonte> consDFontes;

	public ConsDTipoFonte() {
	}

	public Integer getTipoFonteId() {
		return this.tipoFonteId;
	}

	public void setTipoFonteId(Integer tipoFonteId) {
		this.tipoFonteId = tipoFonteId;
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

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public String getTipofonteCod() {
		return this.tipofonteCod;
	}

	public void setTipofonteCod(String tipofonteCod) {
		this.tipofonteCod = tipofonteCod;
	}

	public String getTipofonteDesc() {
		return this.tipofonteDesc;
	}

	public void setTipofonteDesc(String tipofonteDesc) {
		this.tipofonteDesc = tipofonteDesc;
	}

	public List<ConsDFonte> getConsDFontes() {
		return this.consDFontes;
	}

	public void setConsDFontes(List<ConsDFonte> consDFontes) {
		this.consDFontes = consDFontes;
	}

	public ConsDFonte addConsDFonte(ConsDFonte consDFonte) {
		getConsDFontes().add(consDFonte);
		consDFonte.setConsDTipoFonte(this);

		return consDFonte;
	}

	public ConsDFonte removeConsDFonte(ConsDFonte consDFonte) {
		getConsDFontes().remove(consDFonte);
		consDFonte.setConsDTipoFonte(null);

		return consDFonte;
	}

}