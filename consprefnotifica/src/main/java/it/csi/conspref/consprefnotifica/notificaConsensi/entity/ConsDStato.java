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
 * The persistent class for the cons_d_stato database table.
 * 
 */
@Entity
@Table(name="cons_d_stato")
@NamedQuery(name="ConsDStato.findAll", query="SELECT c FROM ConsDStato c")
public class ConsDStato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="CONS_D_STATO_TIPOSTATO_GENERATOR", sequenceName="CONS_D_STATO_TIPO_STATO_SEQ")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_STATO_TIPOSTATO_GENERATOR")
	@Column(name="tipo_stato")
	private String tipoStato;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="desc_stato")
	private String descStato;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsTConsenso
	@OneToMany(mappedBy="consDStato")
	private List<ConsTConsenso> consTConsensos;

	public ConsDStato() {
	}

	public String getTipoStato() {
		return this.tipoStato;
	}

	public void setTipoStato(String tipoStato) {
		this.tipoStato = tipoStato;
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

	public String getDescStato() {
		return this.descStato;
	}

	public void setDescStato(String descStato) {
		this.descStato = descStato;
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
		consTConsenso.setConsDStato(this);

		return consTConsenso;
	}

	public ConsTConsenso removeConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().remove(consTConsenso);
		consTConsenso.setConsDStato(null);

		return consTConsenso;
	}

}