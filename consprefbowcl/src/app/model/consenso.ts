import {Operatore} from './operatore';
import {Fonte} from './fonte';
import {Informativa} from './informativa';
import {TipoStato} from './tipo-stato';
import {ValoreConsenso} from './valore-consenso';
import {TipoASR} from './tipo-asr';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class Consenso {
  cf_cittadino: string;
  cf_delegato: string;
  id_aura: string;
  nome: string;
  delegato_nome: string;
  delegato_cognome: string;
  cognome: string;
  data_acquisizione: Date;
  operatore: Operatore;
  fonte: Fonte;
  tipo_stato: TipoStato;
  informativa: Informativa;
  valore_consenso: ValoreConsenso;
  tipo_asr: TipoASR;
  uuid: string;
  updated: boolean;

}
