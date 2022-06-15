/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cons_d_errore_tipo database table.
 * 
 */
@Entity
@Table(name="cons_d_errore_tipo")
@NamedQuery(name="ConsDErroreTipo.findAll", query="SELECT c FROM ConsDErroreTipo c")
@NamedQuery(name="ConsDErroreTipo.findByCodiceErroreTipo", query="SELECT c FROM ConsDErroreTipo c where c.errTipoCod=:errCodeTipo and c.dataCancellazione is null")
public class ConsDErroreTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_D_ERRORE_TIPO_ERRTIPOID_GENERATOR", sequenceName="CONS_D_ERRORE_TIPO_ERR_TIPO_ID_SEQ" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_ERRORE_TIPO_ERRTIPOID_GENERATOR")
	@Column(name="err_tipo_id")
	private Integer errTipoId;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="err_tipo_cod")
	private String errTipoCod;

	@Column(name="err_tipo_desc")
	private String errTipoDesc;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsTNotifica
	@OneToMany(mappedBy="consDErroreTipo")
	private List<ConsTNotifica> consTNotificas;

	public ConsDErroreTipo() {
	}

	public Integer getErrTipoId() {
		return this.errTipoId;
	}

	public void setErrTipoId(Integer errTipoId) {
		this.errTipoId = errTipoId;
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

	public String getErrTipoCod() {
		return this.errTipoCod;
	}

	public void setErrTipoCod(String errTipoCod) {
		this.errTipoCod = errTipoCod;
	}

	public String getErrTipoDesc() {
		return this.errTipoDesc;
	}

	public void setErrTipoDesc(String errTipoDesc) {
		this.errTipoDesc = errTipoDesc;
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

	public List<ConsTNotifica> getConsTNotificas() {
		return this.consTNotificas;
	}

	public void setConsTNotificas(List<ConsTNotifica> consTNotificas) {
		this.consTNotificas = consTNotificas;
	}

	public ConsTNotifica addConsTNotifica(ConsTNotifica consTNotifica) {
		getConsTNotificas().add(consTNotifica);
		consTNotifica.setConsDErroreTipo(this);

		return consTNotifica;
	}

	public ConsTNotifica removeConsTNotifica(ConsTNotifica consTNotifica) {
		getConsTNotificas().remove(consTNotifica);
		consTNotifica.setConsDErroreTipo(null);

		return consTNotifica;
	}

}