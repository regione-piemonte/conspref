/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi;

public enum StatoConsensoEnum
{
	ATTIVO("A","ATTIVO"),
	REVOCATO("R","REVOCATO");

	
	private String codice;
	private String descrizione;
	
	
	private StatoConsensoEnum(String codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
}
