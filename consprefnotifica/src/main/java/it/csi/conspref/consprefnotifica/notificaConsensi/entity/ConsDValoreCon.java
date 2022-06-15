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
 * The persistent class for the cons_d_valore_cons database table.
 * 
 */
@Entity
@Table(name="cons_d_valore_cons")
@NamedQuery(name="ConsDValoreCon.findAll", query="SELECT c FROM ConsDValoreCon c")
public class ConsDValoreCon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="CONS_D_VALORE_CONS_VALORECONSENSO_GENERATOR", sequenceName="CONS_D_VALORE_CONS_VALORE_CONSENSO_SEQ")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_VALORE_CONS_VALORECONSENSO_GENERATOR")
	@Column(name="valore_consenso")
	private String valoreConsenso;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="desc_consenso")
	private String descConsenso;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsTConsenso
	@OneToMany(mappedBy="consDValoreCon")
	private List<ConsTConsenso> consTConsensos;

	public ConsDValoreCon() {
	}

	public String getValoreConsenso() {
		return this.valoreConsenso;
	}

	public void setValoreConsenso(String valoreConsenso) {
		this.valoreConsenso = valoreConsenso;
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

	public String getDescConsenso() {
		return this.descConsenso;
	}

	public void setDescConsenso(String descConsenso) {
		this.descConsenso = descConsenso;
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

	public List<ConsTConsenso> getConsTConsensos() {
		return this.consTConsensos;
	}

	public void setConsTConsensos(List<ConsTConsenso> consTConsensos) {
		this.consTConsensos = consTConsensos;
	}

	public ConsTConsenso addConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().add(consTConsenso);
		consTConsenso.setConsDValoreCon(this);

		return consTConsenso;
	}

	public ConsTConsenso removeConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().remove(consTConsenso);
		consTConsenso.setConsDValoreCon(null);

		return consTConsenso;
	}

}