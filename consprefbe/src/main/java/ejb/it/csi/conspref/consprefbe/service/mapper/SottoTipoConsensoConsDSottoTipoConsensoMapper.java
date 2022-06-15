/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefbe.service.mapper;

import it.csi.conspref.consprefbe.model.ConsDSottoTipoCon;
import it.csi.conspref.consprefbe.ws.model.SottoTipoConsenso;

public class SottoTipoConsensoConsDSottoTipoConsensoMapper implements Mapper<SottoTipoConsenso, ConsDSottoTipoCon> {

	@Override
	public ConsDSottoTipoCon to(SottoTipoConsenso source) {
		
		ConsDSottoTipoCon dest = new ConsDSottoTipoCon();
		dest.setSottoTipoConsenso(source.getCodice());
		dest.setDescSottoTipoCons(source.getDescrizione());
		return dest;
	}

	@Override
	public SottoTipoConsenso from(ConsDSottoTipoCon dest) {
		SottoTipoConsenso source = new SottoTipoConsenso();
		source.setCodice(dest.getSottoTipoConsenso());
		source.setDescrizione(dest.getDescSottoTipoCons());
		return source;
	}

}
