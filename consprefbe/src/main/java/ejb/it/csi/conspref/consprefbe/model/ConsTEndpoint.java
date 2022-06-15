/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

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
	@Column(name="endp_id")
	private Integer endpId;

	@Column(name="cod_asr")
	private String codAsr;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="endp_url")
	private String endpUrl;

	@Column(name="id_app")
	private String idApp;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	public ConsTEndpoint() {
	}

	public Integer getEndpId() {
		return this.endpId;
	}

	public void setEndpId(Integer endpId) {
		this.endpId = endpId;
	}

	public String getCodAsr() {
		return this.codAsr;
	}

	public void setCodAsr(String codAsr) {
		this.codAsr = codAsr;
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

	public String getIdApp() {
		return this.idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
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

}
