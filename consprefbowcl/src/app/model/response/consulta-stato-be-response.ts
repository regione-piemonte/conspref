import {ServiceResponse} from './service-response';
import {TipoStato} from '../tipo-stato';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ConsultaStatoBeResponse extends ServiceResponse {
  stati: TipoStato[];
}
