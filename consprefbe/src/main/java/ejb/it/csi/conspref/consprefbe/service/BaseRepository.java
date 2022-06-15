/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class BaseRepository {

	protected void setCreazione(Object o, String loginOperazione) {
		Timestamp now=new Timestamp(new Date().getTime());

		try {
			invokeSingleParamMethod(o, "setDataCreazione", Timestamp.class, now);
			invokeSingleParamMethod(o, "setDataModifica", Timestamp.class, now);
			invokeSingleParamMethod(o, "setDataCancellazione", Timestamp.class, null);
			invokeSingleParamMethod(o, "setLoginOperazione", String.class, loginOperazione);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Impossibile impostare parametri data creazione", e);
		}
	}

	protected void setModifica(Object o, String loginOperazione) {
		Timestamp now=new Timestamp(new Date().getTime());

		try {
			invokeSingleParamMethod(o, "setDataModifica", Timestamp.class, now);
			invokeSingleParamMethod(o, "setLoginOperazione", String.class, loginOperazione);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Impossibile impostare parametri data modifica", e);
		}
	}

	protected void setCancellazione(Object o, String loginOperazione) {
		Timestamp now=new Timestamp(new Date().getTime());

		try {
			invokeSingleParamMethod(o, "setDataCancellazione", Timestamp.class, now);
			invokeSingleParamMethod(o, "setLoginOperazione", String.class, loginOperazione);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Impossibile impostare parametri data cancellazione", e);
		}
	}

	private void invokeSingleParamMethod(Object o, String setterMethodName, Class valueClass, Object value)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> c = o.getClass();
		Method method = c.getMethod(setterMethodName, valueClass);
		method.invoke(o, value);
	}
	
	protected Date formatDate(String durata,Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		if(date != null) {
			cal.setTime(date);
		}
		

		if(durata == null) {
			durata="365";
		}
		cal.add(Calendar.DATE, Integer.parseInt(durata)-1);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
	

	protected boolean isMaggiorenne(Date dataNascita){
		boolean isMagg=false;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dataNascita);
		
		int anno   = cal.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int mese   = cal.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		int giorno = cal.get(Calendar.DAY_OF_MONTH) - cal1.get(Calendar.DAY_OF_MONTH);
		
		isMagg = anno>18 || (anno==18 && (mese>0 || (mese==0 && giorno>=0)));
		
		return isMagg;
	}
}
