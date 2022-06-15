/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

import java.io.Serializable;
import java.sql.Timestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * The persistent class for the cons_t_operatorebo database table.
 * 
 */
@Entity
@Table(name="cons_t_operatorebo")
@NamedQuery(name="ConsTOperatorebo.findAll", query="SELECT c FROM ConsTOperatorebo c")
public class ConsTOperatorebo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_T_OPERATOREBO_OPERATOREBOID_GENERATOR", sequenceName="CONS_T_OPERATOREBO_OPERATOREBO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_T_OPERATOREBO_OPERATOREBOID_GENERATOR")
	@Column(name="operatorebo_id")
	private Integer operatoreboId;

	@Column(name="cf_operatore")
	private String cfOperatore;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="operatore_id")
	private Integer operatoreId;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional one-to-one association to ConsDOperatore
	@OneToOne
	@JoinColumn(name="operatorebo_id")
	private ConsDOperatore consDOperatore;

	public ConsTOperatorebo() {
	}

	public Integer getOperatoreboId() {
		return this.operatoreboId;
	}

	public void setOperatoreboId(Integer operatoreboId) {
		this.operatoreboId = operatoreboId;
	}

	public String getCfOperatore() {
		return this.cfOperatore;
	}

	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
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

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getLoginOperazione() {
		return this.loginOperazione;
	}

	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
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

	public ConsDOperatore getConsDOperatore() {
		return this.consDOperatore;
	}

	public void setConsDOperatore(ConsDOperatore consDOperatore) {
		this.consDOperatore = consDOperatore;
	}
}
