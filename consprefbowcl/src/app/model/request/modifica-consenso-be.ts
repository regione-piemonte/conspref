import {ServiceRequest} from './service-request';
import {Operatore} from '../operatore';
import {Fonte} from '../fonte';
import {TipoStato} from '../tipo-stato';
import {ValoreConsenso} from '../valore-consenso';
import {TipoASR} from '../tipo-asr';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ModificaConsensoBe extends ServiceRequest{
  cfCittadino: string;
  idAura: string;
  cfDelegato: string;
  operatore: Operatore;
  fonte: Fonte;
  dataAcquisizione: Date;
  idInformativa: number;
  tipoStato: TipoStato;
  valoreConsenso: ValoreConsenso;
  asr: TipoASR;
}
