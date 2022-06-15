/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi;

public enum EsitoEnum
{
	SUCCESSO("0000","Nessun errore o avviso"),
	WARNING("0001","Avviso (non bloccante)"),
	BLOCCANTE("9999","Errore (bloccante)");
	
	private String codice;
	private String descrizione;
	
	
	private EsitoEnum(String codice, String descrizione) {
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
