/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.mapper;

import it.csi.conspref.consprefbe.ws.model.Codifica;
import it.csi.conspref.consprefbe.ws.model.TipoASR;
import it.csi.conspref.consprefbe.ws.model.TipoStato;
import it.csi.conspref.consprefbe.ws.model.ValoreConsenso;
import it.csi.conspref.consprefboweb.dto.Consenso;

public class ConsensoMapper extends BaseMapper<Consenso, it.csi.conspref.consprefbe.ws.model.Consenso> {

	private CodificaMapper codificaMapper = new CodificaMapper();
	private InformativaMapper informativaMapper = new InformativaMapper();
	private FonteMapper fonteMapper = new FonteMapper();
	private OperatorMapper operatorMapper = new OperatorMapper();

	@Override
	public it.csi.conspref.consprefbe.ws.model.Consenso to(Consenso source) {
		if (source == null) return null;
		final it.csi.conspref.consprefbe.ws.model.Consenso result = new it.csi.conspref.consprefbe.ws.model.Consenso();

		result.setCfCittadino(source.getCfCittadino());
		result.setCfDelegato(source.getCfDelegato());
		result.setCognome(source.getCognome());
		result.setDataAcquisizione(source.getDataAcquisizione());
		result.setFonte(fonteMapper.to(source.getFonte()));
		result.setIdAura(source.getIdAura());
		result.setInformativa(informativaMapper.to(source.getInformativa()));
		result.setNome(source.getNome());
		result.setOperatore(operatorMapper.to(source.getOperatore()));

		Codifica toTipoASR = codificaMapper.to(source.getTipoAsr());
		TipoASR tipoASR = new TipoASR();
		tipoASR.setCodice(toTipoASR.getCodice());
		tipoASR.setDescrizione(toTipoASR.getDescrizione());
		result.setTipoAsr(tipoASR);

		Codifica toTipoStato = codificaMapper.to(source.getTipoStato());
		TipoStato tipoStato = new TipoStato();
		tipoStato.setCodice(toTipoStato.getCodice());
		tipoStato.setDescrizione(toTipoStato.getDescrizione());
		result.setTipoStato(tipoStato);

		result.setUuid(source.getUuid());

		Codifica toValoreConsenso = codificaMapper.to(source.getValoreConsenso());
		ValoreConsenso valoreConsenso = new ValoreConsenso();
		valoreConsenso.setCodice(toValoreConsenso.getCodice());
		valoreConsenso.setDescrizione(toValoreConsenso.getDescrizione());
		result.setValoreConsenso(valoreConsenso);

		return result;
	}

	@Override
	public Consenso from(it.csi.conspref.consprefbe.ws.model.Consenso dest) {
		if (dest == null) return null;
		final Consenso result = new Consenso();

		result.setCfCittadino(dest.getCfCittadino());
		result.setCfDelegato(dest.getCfDelegato());
		result.setCognome(dest.getCognome());
		result.setDataAcquisizione(dest.getDataAcquisizione());
		result.setFonte(fonteMapper.from(dest.getFonte()));
		result.setIdAura(dest.getIdAura());
		result.setInformativa(informativaMapper.from(dest.getInformativa()));
		result.setNome(dest.getNome());
		result.setOperatore(operatorMapper.from(dest.getOperatore()));
		result.setTipoAsr(codificaMapper.from(dest.getTipoAsr()));
		result.setTipoStato( codificaMapper.from(dest.getTipoStato()));
		result.setUuid(dest.getUuid());
		result.setValoreConsenso(codificaMapper.from(dest.getValoreConsenso()));

		return result;
	}
}
