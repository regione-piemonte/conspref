/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cons_t_endpoint database table.
 * 
 */
@Entity
@Table(name="cons_t_endpoint")
@NamedQuery(name="ConsTEndpoint.findAll", query="SELECT c FROM ConsTEndpoint c")
public class ConsTEndpoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_T_ENDPOINT_ENDPID_GENERATOR", sequenceName="CONS_T_ENDPOINT_ENDP_ID_SEQ" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_T_ENDPOINT_ENDPID_GENERATOR")
	@Column(name="endp_id")
	private Integer endpId;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="endp_url")
	private String endpUrl;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsDAsr
	@ManyToOne
	@JoinColumn(name="cod_asr")
	private ConsDAsr consDAsr;

	public ConsTEndpoint() {
	}

	public Integer getEndpId() {
		return this.endpId;
	}

	public void setEndpId(Integer endpId) {
		this.endpId = endpId;
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

	public String getEndpUrl() {
		return this.endpUrl;
	}

	public void setEndpUrl(String endpUrl) {
		this.endpUrl = endpUrl;
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

	public ConsDAsr getConsDAsr() {
		return this.consDAsr;
	}

	public void setConsDAsr(ConsDAsr consDAsr) {
		this.consDAsr = consDAsr;
	}

}