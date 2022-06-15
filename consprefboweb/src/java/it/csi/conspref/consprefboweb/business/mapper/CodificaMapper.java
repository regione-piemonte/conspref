/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.mapper;

import it.csi.conspref.consprefboweb.dto.Codifica;

public class CodificaMapper extends BaseMapper<Codifica, it.csi.conspref.consprefbe.ws.model.Codifica> {

	@Override
	public it.csi.conspref.consprefbe.ws.model.Codifica to(Codifica source) {
		if (source == null) return null;
		final it.csi.conspref.consprefbe.ws.model.Codifica result = new it.csi.conspref.consprefbe.ws.model.Codifica();

		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());

		return result;
	}

	@Override
	public Codifica from(it.csi.conspref.consprefbe.ws.model.Codifica dest) {
		if (dest == null) return null;
		final Codifica result = new Codifica();

		result.setCodice(dest.getCodice());
		result.setDescrizione(dest.getDescrizione());

		return result;
	}
}
