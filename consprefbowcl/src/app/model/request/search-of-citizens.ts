import {ServiceRequest} from './service-request';
import {Operatore} from '../operatore';
import {Fonte} from '../fonte';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class SearchOfCitizens extends ServiceRequest{
  private _codiceFiscale: string;
  private _cognome: string;
  private _nome: string;
  private _dataDiNascita: string;

  get cognome(): string {
    return this._cognome;
  }
  set cognome(value: string) {
    this._cognome = value;
  }

  get codiceFiscale(): string {
    return this._codiceFiscale;
  }
  set codiceFiscale(value: string) {
    this._codiceFiscale = value;
  }

  get nome(): string {
    return this._nome;
  }
  set nome(value: string) {
    this._nome = value;
  }

  get dataDiNascita(): string {
    return this._dataDiNascita;
  }
  set dataDiNascita(value: string) {
    this._dataDiNascita = value;
  }
}
