/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.mapper;

import it.csi.conspref.consprefbe.ws.model.SottoTipoConsenso;
import it.csi.conspref.consprefbe.ws.model.TipoConsenso;
import it.csi.conspref.consprefboweb.dto.Informativa;

public class InformativaMapper extends BaseMapper<Informativa, it.csi.conspref.consprefbe.ws.model.Informativa> {

	private CodificaMapper codificaMapper = new CodificaMapper();

	@Override
	public it.csi.conspref.consprefbe.ws.model.Informativa to(Informativa source) {
		if (source == null) return null;
		final it.csi.conspref.consprefbe.ws.model.Informativa result = new it.csi.conspref.consprefbe.ws.model.Informativa();

		result.setIdInformativa(source.getIdInformativa());
		result.setDescInformativa(source.getDescInformativa());
		result.setHtmlInformativa(source.getHtmlInformativa());
		result.setDataDecorrenza(source.getDataDecorrenza());
		result.setDataScadenza(source.getDataScadenza());
		result.setPdfInformativa(source.getPdfInformativa());
		result.setSottoTipoConsenso((SottoTipoConsenso) codificaMapper.to(source.getSottoTipoConsenso()));
		result.setTipoConsenso((TipoConsenso) codificaMapper.to(source.getTipoConsenso()));

		return result;
	}

	@Override
	public Informativa from(it.csi.conspref.consprefbe.ws.model.Informativa dest) {
		if (dest == null) return null;
		final Informativa result = new Informativa();

		result.setIdInformativa(dest.getIdInformativa());
		result.setDescInformativa(dest.getDescInformativa());
		result.setHtmlInformativa(dest.getHtmlInformativa());
		result.setDataDecorrenza(dest.getDataDecorrenza());
		result.setDataScadenza(dest.getDataScadenza());
		result.setPdfInformativa(dest.getPdfInformativa());
		result.setSottoTipoConsenso(codificaMapper.from(dest.getSottoTipoConsenso()));
		result.setTipoConsenso(codificaMapper.from(dest.getTipoConsenso()));

		return result;
	}
}
