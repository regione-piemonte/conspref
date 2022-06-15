import {Errore} from '../errore';
import {ResultatoCodice} from '../resultato-codice';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class ServiceResponse {
  elencoErrori: Errore;
  esito: ResultatoCodice;
}
