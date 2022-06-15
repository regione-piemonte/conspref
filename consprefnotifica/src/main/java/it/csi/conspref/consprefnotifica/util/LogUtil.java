/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefnotifica.util;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import org.apache.logging.log4j.*;
import org.springframework.stereotype.Component;
@Component
public class LogUtil {
	
	public static String APPLICATION_CODE = "consprefnotifica";

	private String pattern = "[%s.%s] - %s";
	
	private Logger logger;
	private String className;
	
	public LogUtil() {
		logger = LogManager.getLogger(APPLICATION_CODE);
		className = this.getClass().getSimpleName();
	}
	
	public LogUtil(Class<?> clazz) {
		logger = LogManager.getLogger(APPLICATION_CODE);
		className = clazz.getSimpleName();
	}
	
	public LogUtil(Class<?> clazz, String layer) {
		logger = LogManager.getLogger(APPLICATION_CODE+ "." + layer);
		className = clazz.getSimpleName();
	}
	
	
	public void debug(String methodName, String message, Object... args) {
		if(logger.isDebugEnabled()) {
			String formattedMessage = String.format(message, toArgs(args));
			logger.debug(String.format(pattern, className,methodName,formattedMessage));
		}
		
	}
	
	

	public void info(String methodName, String message, Object... args) {
		if(logger.isInfoEnabled()) {
			String formattedMessage = String.format(message, toArgs(args));
			logger.info(String.format(pattern, className,methodName,formattedMessage));
		}
	}
	
	public void warn(String methodName, String message, Object... args) {
		if(logger.isWarnEnabled()) {
			String formattedMessage = String.format(message, toArgs(args));
			logger.warn(String.format(pattern, className,methodName,formattedMessage));
		}
	}
	
	public void warn(String methodName, String message, Throwable t, Object... args) {
		if(logger.isWarnEnabled()) {
			String formattedMessage = String.format(message, toArgs(args));
			logger.warn(String.format(pattern, className,methodName,formattedMessage),t);
		}
	}
	
	public void error(String methodName, String message, Object... args) {
		if(logger.isErrorEnabled()) {
			String formattedMessage = String.format(message, toArgs(args));
			logger.error(String.format(pattern, className,methodName,formattedMessage));
		}
	}
	
	public void error(String methodName, String message, Throwable t, Object... args) {
		if(logger.isErrorEnabled()) {
			String formattedMessage = String.format(message, toArgs(args));
			logger.error(String.format(pattern, className,methodName,formattedMessage),t);
		}
	}
	
	public boolean isDebugEnabled(){
		return logger.isDebugEnabled();
	}
	
	public boolean isInfoEnabled(){
		return logger.isInfoEnabled();
	}
	
	public boolean isTraceEnabled(){
		return logger.isTraceEnabled();
	}
	
	public boolean isEnabledFor(Level level){
		return logger.isEnabled(level);
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger log) {
		this.logger = log;
	}
	

	/**
	 * Logga un oggetto complesso con annotazione XmlType.
	 * 
	 * @param obj oggetto con annotazione XmlType da convertire in xml per il logging
	 * @param msg nome del parametro da loggare (per il logging)
	 */
	public void logXmlTypeObject(Object obj, String msg) {
		String methodName = "logXmlTypeObject";
		if (isDebugEnabled()) {			
			String result;
			try {
				result = toXml(obj);			
			} catch (RuntimeException e) {
				result = e.getMessage();
				warn(methodName,"Impossibile loggare "+msg,e);
			}
			
			debug(methodName,msg+": "+ result);
		}
	}

	/**
	 * Trasforma un oggetto con annotazione XmlType in una stringa xml.
	 * 
	 * @param obj
	 * @return
	 * @throws JAXBException
	 * @throws PropertyException
	 */
	private static String toXml(Object obj) {
		if(obj == null){
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JAXB.marshal(obj, baos);
		return baos.toString();

	}
	
	private Object[] toArgs(Object[] args) {
		for (int i = 0; i<args.length; i++) {
			Object arg = args[i];
			if(arg instanceof ToLog) {
				ToLog f = (ToLog) (arg);
				args[i] = f.apply();
			}
		}
		return args;
	}

	@FunctionalInterface
	public interface ToLog {
		public Object apply();
	}


}
