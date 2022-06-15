import {ServiceResponse} from './service-response';
import {TipoConsenso} from '../tipo-consenso';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ConsultaTipoBeResponse extends ServiceResponse {
  tipiConsenso: TipoConsenso[];
}
