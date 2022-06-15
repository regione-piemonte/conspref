import {TipoConsenso} from './tipo-consenso';
import {SottoTipoConsenso} from './sotto-tipo-consenso';
import {Consenso} from './consenso';
import {el} from '@angular/platform-browser/testing/src/browser_util';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export enum InformativaStatus {
  ACTIVE = "ACTIVE",
  EXPIRED = "EXPIRED",
  NOT_ACTIVE = "NOT_ACTIVE",
  NEGATO = "NEGATO",
  REVOKED = "REVOKED"
}

export class Informativa {
  id_informativa: number;
  desc_informativa: string;
  html_informativa: string;
  tipo_consenso: TipoConsenso;
  sotto_tipo_consenso: SottoTipoConsenso;
  pdf_informativa: string;
  data_decorrenza: Date;
  data_scadenza: Date;
  consenso_list: Consenso[];
}
