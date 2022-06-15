/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cons_d_fonte database table.
 * 
 */
@Entity
@Table(name="cons_d_fonte")
@NamedQuery(name="ConsDFonte.findAll", query="SELECT c FROM ConsDFonte c")
public class ConsDFonte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONS_D_FONTE_FONTEID_GENERATOR", sequenceName="CONS_D_FONTE_FONTE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONS_D_FONTE_FONTEID_GENERATOR")
	@Column(name="fonte_id")
	private Integer fonteId;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_decorrenza")
	private Timestamp dataDecorrenza;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="data_scadenza")
	private Timestamp dataScadenza;

	@Column(name="fonte_cod")
	private String fonteCod;

	@Column(name="fonte_desc")
	private String fonteDesc;

	@Column(name="login_operazione")
	private String loginOperazione;

	@Column(name="ruoloop_id")
	private Integer ruoloopId;

	//bi-directional many-to-one association to ConsDTipoFonte
	@ManyToOne
	@JoinColumn(name="tipo_fonte_id")
	private ConsDTipoFonte consDTipoFonte;

	//bi-directional many-to-one association to ConsTConsenso
	@OneToMany(mappedBy="consDFonte")
	private List<ConsTConsenso> consTConsensos;

	public ConsDFonte() {
	}

	public Integer getFonteId() {
		return this.fonteId;
	}

	public void setFonteId(Integer fonteId) {
		this.fonteId = fonteId;
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

	public Timestamp getDataDecorrenza() {
		return this.dataDecorrenza;
	}

	public void setDataDecorrenza(Timestamp dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Timestamp getDataScadenza() {
		return this.dataScadenza;
	}

	public void setDataScadenza(Timestamp dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getFonteCod() {
		return this.fonteCod;
	}

	public void setFonteCod(String fonteCod) {
		this.fonteCod = fonteCod;
	}

	public String getFonteDesc() {
		return this.fonteDesc;
	}

	public void setFonteDesc(String fonteDesc) {
		this.fonteDesc = fonteDesc;
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

	public ConsDTipoFonte getConsDTipoFonte() {
		return this.consDTipoFonte;
	}

	public void setConsDTipoFonte(ConsDTipoFonte consDTipoFonte) {
		this.consDTipoFonte = consDTipoFonte;
	}

	public List<ConsTConsenso> getConsTConsensos() {
		return this.consTConsensos;
	}

	public void setConsTConsensos(List<ConsTConsenso> consTConsensos) {
		this.consTConsensos = consTConsensos;
	}

	public ConsTConsenso addConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().add(consTConsenso);
		consTConsenso.setConsDFonte(this);

		return consTConsenso;
	}

	public ConsTConsenso removeConsTConsenso(ConsTConsenso consTConsenso) {
		getConsTConsensos().remove(consTConsenso);
		consTConsenso.setConsDFonte(null);

		return consTConsenso;
	}

}