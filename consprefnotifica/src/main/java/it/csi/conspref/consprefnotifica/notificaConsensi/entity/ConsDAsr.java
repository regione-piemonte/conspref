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
 * The persistent class for the cons_d_asr database table.
 * 
 */
@Entity
@Table(name="cons_d_asr")
@NamedQuery(name="ConsDAsr.findAll", query="SELECT c FROM ConsDAsr c")
public class ConsDAsr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id

	@Column(name="cod_asr")
	private String codAsr;
	
	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="desc_asr")
	private String descAsr;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsTEndpoint
	@OneToMany(mappedBy="consDAsr")
	private List<ConsTEndpoint> consTEndpoints;

	public ConsDAsr() {
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

	public String getDescAsr() {
		return this.descAsr;
	}

	public void setDescAsr(String descAsr) {
		this.descAsr = descAsr;
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

	public List<ConsTEndpoint> getConsTEndpoints() {
		return this.consTEndpoints;
	}

	public void setConsTEndpoints(List<ConsTEndpoint> consTEndpoints) {
		this.consTEndpoints = consTEndpoints;
	}



}