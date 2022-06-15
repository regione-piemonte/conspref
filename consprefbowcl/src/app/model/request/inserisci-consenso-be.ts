import {ServiceRequest} from './service-request';
import {Consenso} from '../consenso';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class InserisciConsensoBe extends ServiceRequest{
  operatore: string;
  consenso: Consenso;
}
