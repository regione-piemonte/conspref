import {ServiceResponse} from './service-response';
import {TipoASR} from '../tipo-asr';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ConsultaAsrBeResponse extends ServiceResponse {
  asr: TipoASR[];
}
