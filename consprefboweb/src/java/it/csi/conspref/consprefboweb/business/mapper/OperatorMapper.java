/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.mapper;

import it.csi.conspref.consprefboweb.dto.Operatore;

public class OperatorMapper extends BaseMapper<Operatore, it.csi.conspref.consprefbe.ws.model.Operatore> {

	@Override
	public it.csi.conspref.consprefbe.ws.model.Operatore to(Operatore source) {
		if (source == null) return null;
		final it.csi.conspref.consprefbe.ws.model.Operatore result = new it.csi.conspref.consprefbe.ws.model.Operatore();

		result.setCodiceOperatore(source.getCodiceOperatore());
		result.setTipoOperatore(source.getTipoOperatore());

		return result;
	}

	@Override
	public Operatore from(it.csi.conspref.consprefbe.ws.model.Operatore dest) {
		if (dest == null) return null;
		final Operatore result = new Operatore();

		result.setCodiceOperatore(dest.getCodiceOperatore());
		result.setTipoOperatore(dest.getTipoOperatore());

		return result;
	}
}
