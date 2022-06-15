/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.notificaConsensi;

public enum NotificaStatoEnum
{
	AVVIATA("A","AVVIATA"),
	CONCLUSA("C","CONCLUSA"),
	ERRORE("E","ERRORE"),
	NOTIFICATO("N","NOTIFICATO");
	
	private String codice;
	private String descrizione;
	
	
	private NotificaStatoEnum(String codice, String descrizione) {
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
