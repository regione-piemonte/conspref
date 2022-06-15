/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VerificaSatiValoriConsenso {
	
	
	public static boolean verificaConsensi(String tipoStatoOld, String valoreOld, String tipoStatoNew, String valoreNew) {
		boolean ok = false;
		
		String combinazione = tipoStatoOld + valoreOld + tipoStatoNew + valoreNew;
		List<String> valoriConsentiti = new ArrayList<String>();
		valoriConsentiti.add("ASIRNO");
		valoriConsentiti.add("RNOASI");
		valoriConsentiti.add("ANOASI");
		
		ok = valoriConsentiti.stream().anyMatch(c -> c.equalsIgnoreCase(combinazione)); 
		
		
		return ok;
	}
	
	
	public static void main(String[] args) {
		
		String tipoOld1Corretto = "A";
		String valoreOld1Corretto = "SI";
		String tipoNewCorretto = "R";
		String valoreNewCorretto = "NO";
		
		
		String tipoOld1Corretto2 = "R";
		String valoreOld1Corretto2 = "NO";
		String tipoNewCorretto2 = "A";
		String valoreNewCorretto2 = "SI";
		
		
		String tipoOldErrato = "A";
		String valoreOldErrato = "NO";
		String tipoNewErrato = "R";
		String valoreNewErrato = "NO";
		
		System.out.println( "verificaConsensi Old1Corretto: " +  verificaConsensi(tipoOld1Corretto, valoreOld1Corretto, tipoNewCorretto, valoreNewCorretto)
				+ "verificaConsensi Old1Corretto2: " +  verificaConsensi(tipoOld1Corretto2, valoreOld1Corretto2, tipoNewCorretto2,  valoreNewCorretto2) 
				+ "verificaConsensi sbagliato: " + verificaConsensi(tipoOldErrato, valoreOldErrato, tipoNewErrato, valoreNewErrato));
		
		 
	}
}
