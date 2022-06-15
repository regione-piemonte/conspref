import {ServiceRequest} from './service-request';
import {Operatore} from '../operatore';
import {Fonte} from '../fonte';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ConsultaConsensoBe extends ServiceRequest{
  cfCittadino: string;
  cfDelegato: string;
  operatore: Operatore;
  fonte: Fonte;
  codiceTipoConsenso: string;
  codiceSottotipoConsenso: string;
  idInformativa: string;
  codAsr: string;
  verificaAura: boolean
}
