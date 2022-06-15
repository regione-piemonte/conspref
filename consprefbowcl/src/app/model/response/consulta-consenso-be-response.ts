import {ServiceResponse} from './service-response';
import {Consenso} from '../consenso';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ConsultaConsensoBeResponse extends ServiceResponse{
  consensi: Consenso[];
}
