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
 * The persistent class for the cons_d_operatore database table.
 * 
 */
@Entity
@Table(name="cons_d_operatore")
@NamedQuery(name="ConsDOperatore.findAll", query="SELECT c FROM ConsDOperatore c")
public class ConsDOperatore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_D_OPERATORE_OPERATOREID_GENERATOR", sequenceName="CONS_D_OPERATORE_OPERATORE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_OPERATORE_OPERATOREID_GENERATOR")
	@Column(name="operatore_id")
	private Integer operatoreId;

	@Column(name="cod_operatore")
	private String codOperatore;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	@Column(name="tipo_operatore")
	private String tipoOperatore;

	//bi-directional many-to-one association to ConsTConsenso
	@OneToMany(mappedBy="consDOperatore")
	private List<ConsTConsenso> consTConsensos;

	public ConsDOperatore() {
	}

	public Integer getOperatoreId() {
		return this.operatoreId;
	}

	public void setOperatoreId(Integer operatoreId) {
		this.operatoreId = operatoreId;
	}

	public String getCodOperatore() {
		return this.codOperatore;
	}

	public void setCodOperatore(String codOperatore) {
		this.codOperatore = codOperatore;
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

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public String getTipoOperatore() {
		return this.tipoOperatore;
	}

	public void setTipoOperatore(String tipoOperatore) {
		this.tipoOperatore = tipoOperatore;
	}

	public List<ConsTConsenso> getConsTConsensos() {
		return this.consTConsensos;
	}

	public void setConsTConsensos(List<ConsTConsenso> consTConsensos) {
		this.consTConsensos = consTConsensos;
	}

	public ConsTConsenso addConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().add(consTConsenso);
		consTConsenso.setConsDOperatore(this);

		return consTConsenso;
	}

	public ConsTConsenso removeConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().remove(consTConsenso);
		consTConsenso.setConsDOperatore(null);

		return consTConsenso;
	}

}