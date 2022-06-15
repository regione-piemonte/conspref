/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cons_t_notifica_errore_dett database table.
 * 
 */
@Entity
@Table(name="cons_t_notifica_errore_dett")
@NamedQuery(name="ConsTNotificaErroreDett.findAll", query="SELECT c FROM ConsTNotificaErroreDett c")
public class ConsTNotificaErroreDett implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="CONS_T_NOTIFICA_ERRORE_DETT_ERRDETTID_GENERATOR", allocationSize=1, sequenceName="cons_t_notifica_errore_dett_errdett_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_T_NOTIFICA_ERRORE_DETT_ERRDETTID_GENERATOR")
	@Column(name="errdett_id")
	private Integer errdettId;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="errdett_cod")
	private String errdettCod;

	@Column(name="errdett_desc")
	private String errdettDesc;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsTNotifica
	@ManyToOne
	@JoinColumn(name="not_id")
	private ConsTNotifica consTNotifica;

	public ConsTNotificaErroreDett() {
	}

	public Integer getErrdettId() {
		return this.errdettId;
	}

	public void setErrdettId(Integer errdettId) {
		this.errdettId = errdettId;
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

	public String getErrdettCod() {
		return this.errdettCod;
	}

	public void setErrdettCod(String errdettCod) {
		this.errdettCod = errdettCod;
	}

	public String getErrdettDesc() {
		return this.errdettDesc;
	}

	public void setErrdettDesc(String errdettDesc) {
		this.errdettDesc = errdettDesc;
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

	public ConsTNotifica getConsTNotifica() {
		return this.consTNotifica;
	}

	public void setConsTNotifica(ConsTNotifica consTNotifica) {
		this.consTNotifica = consTNotifica;
	}

}