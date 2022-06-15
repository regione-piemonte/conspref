/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.filter;

import java.util.List;

public interface ModelFilter<T> {
	
	public List<T> doFilter(List<T> list, String filter);
	

}
