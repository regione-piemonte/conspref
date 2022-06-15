/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.mapper;

import it.csi.conspref.consprefbe.ws.model.Codifica;
import it.csi.conspref.consprefbe.ws.model.TipoFonte;
import it.csi.conspref.consprefboweb.dto.Fonte;

public class FonteMapper extends BaseMapper<Fonte, it.csi.conspref.consprefbe.ws.model.Fonte> {

	private CodificaMapper codificaMapper = new CodificaMapper();

	@Override
	public it.csi.conspref.consprefbe.ws.model.Fonte to(Fonte source) {
		if (source == null) return null;
		final it.csi.conspref.consprefbe.ws.model.Fonte result = new it.csi.conspref.consprefbe.ws.model.Fonte();

		Codifica to = codificaMapper.to(source.getTipoFonte());
		TipoFonte tipoFonte = new TipoFonte();
		tipoFonte.setCodice(to.getCodice());
		tipoFonte.setDescrizione(to.getDescrizione());
		result.setTipoFonte(tipoFonte);

		result.setCodice(source.getCodice());
		result.setDescrizione(source.getDescrizione());

		return result;
	}

	@Override
	public Fonte from(it.csi.conspref.consprefbe.ws.model.Fonte dest) {
		if (dest == null) return null;
		final Fonte result = new Fonte();

		result.setTipoFonte(codificaMapper.from(dest.getTipoFonte()));
		result.setCodice(dest.getCodice());
		result.setDescrizione(dest.getDescrizione());

		return result;
	}
}
