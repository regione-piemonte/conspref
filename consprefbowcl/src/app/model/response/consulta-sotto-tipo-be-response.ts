import {SottoTipoConsenso} from '../sotto-tipo-consenso';
import {ServiceResponse} from './service-response';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ConsultaSottoTipoBeResponse extends ServiceResponse{
  sottotipiConsenso: SottoTipoConsenso[]
}
