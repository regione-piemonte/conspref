/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.conspref.consprefbe.service.base.exception.ServiceExceptionCittadino;
import it.csi.conspref.consprefbe.ws.model.RisultatoCodice;
import it.csi.conspref.consprefbe.ws.msg.ServiceResponse;

public class CheckCittadino {

	
	public static void checkNotNull(Object obj, String codice, String descrizione) {
		check(()-> obj!=null, codice, descrizione);
	}
	
	public static void checkListNotNull(List<? extends Object> obj, String codice, String descrizione) {
		obj.forEach(object -> {
			check(()-> object!=null, codice, descrizione);
		});
	}
	
	public static void checkNotBlank(String str, String codice, String descrizione) {
		check(() -> StringUtils.isNotBlank(str), codice, descrizione);
	}
	
	public static boolean checkCf(String cf) {
	    int i, s, c;
	    String cf2;
	    
	    int setdisp[] = {1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20,
	           11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };
	    if( cf.length() == 0 )  return false;
	    if( cf.length() != 16 )
	    	return false;

	    cf2 = cf.toUpperCase();
	    for( i=0; i<16; i++ ){
	           c = cf2.charAt(i);
	           if( ! ( c>='0' && c<='9' || c>='A' && c<='Z' ) )
	        	   return false;

	    }
	    s = 0;
	    for( i=1; i<=13; i+=2 ){
	           c = cf2.charAt(i);
	           if( c>='0' && c<='9' )
	                   s = s + c - '0';
	           else
	                   s = s + c - 'A';
	    }
	    for( i=0; i<=14; i+=2 ){
	           c = cf2.charAt(i);
	           if( c>='0' && c<='9' ) c = c - '0' + 'A';
	           s = s + setdisp[c - 'A'];
	    }
	    if( s%26 + 'A' != cf2.charAt(15) )
	    	return false;

		return true;

	}
	
	public static void check(Condition condition, String codice, String descrizione) {
		if(!condition.isSatisfied()) {
			throw new ServiceExceptionCittadino(codice,descrizione);
		}
	}
	
	public static void checkCondition(boolean mustBeTrue, String codice, String descrizione) {
		if(!mustBeTrue) {
			throw new ServiceExceptionCittadino(codice,descrizione);
		}
	}
	
	public static void checkEsitoSuccesso(ServiceResponse serviceResponse, String codice, String descrizione) {
		checkNotNull(serviceResponse, codice, descrizione);
		checkCondition(RisultatoCodice.SUCCESSO == serviceResponse.getEsito(), codice, descrizione);
		
	}
	
	@FunctionalInterface
	public interface Condition {
		public boolean isSatisfied();
	}
	
}
