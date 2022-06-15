/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

import java.io.Serializable;
import java.util.Date;
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
 * The persistent class for the dele_d_ruolo_op database table.
 * 
 */
@Entity
@Table(name="dele_d_ruolo_op")
@NamedQuery(name="DeleDRuoloOp.findAll", query="SELECT d FROM DeleDRuoloOp d")
public class DeleDRuoloOp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DELE_D_RUOLO_OP_RUOLOOPID_GENERATOR", sequenceName="DELE_D_RUOLO_OP_RUOLOOP_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DELE_D_RUOLO_OP_RUOLOOPID_GENERATOR")
	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	@Column(name="data_cancellazione")
	private Date dataCancellazione;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_aslall")
	private Boolean ruoloopAslall;

	@Column(name="ruoloop_cod")
	private String ruoloopCod;

	@Column(name="ruoloop_desc")
	private String ruoloopDesc;

	//bi-directional many-to-one association to DServizio
	@OneToMany(mappedBy="deleDRuoloOp")
	private List<DServizio> DServizios;

	

	public DeleDRuoloOp() {
	}

	public Integer getRuoloopId() {
		return this.ruoloopId;
	}

	public void setRuoloopId(Integer ruoloopId) {
		this.ruoloopId = ruoloopId;
	}

	public Date getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getLoginOperazione() {
		return this.loginOperazione;
	}

	public void setLoginOperazione(String loginOperazione) {
		this.loginOperazione = loginOperazione;
	}

	public Boolean getRuoloopAslall() {
		return this.ruoloopAslall;
	}

	public void setRuoloopAslall(Boolean ruoloopAslall) {
		this.ruoloopAslall = ruoloopAslall;
	}

	public String getRuoloopCod() {
		return this.ruoloopCod;
	}

	public void setRuoloopCod(String ruoloopCod) {
		this.ruoloopCod = ruoloopCod;
	}

	public String getRuoloopDesc() {
		return this.ruoloopDesc;
	}

	public void setRuoloopDesc(String ruoloopDesc) {
		this.ruoloopDesc = ruoloopDesc;
	}

	public List<DServizio> getDServizios() {
		return this.DServizios;
	}

	public void setDServizios(List<DServizio> DServizios) {
		this.DServizios = DServizios;
	}

	public DServizio addDServizio(DServizio DServizio) {
		getDServizios().add(DServizio);
		DServizio.setDeleDRuoloOp(this);

		return DServizio;
	}

	public DServizio removeDServizio(DServizio DServizio) {
		getDServizios().remove(DServizio);
		DServizio.setDeleDRuoloOp(null);

		return DServizio;
	}

}