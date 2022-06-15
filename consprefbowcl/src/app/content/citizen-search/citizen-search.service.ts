import { Injectable } from '@angular/core';
import {SearchOfCitizens} from '../../model/request/search-of-citizens';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Injectable()
export class CitizenSearchService {

  constructor(
    protected httpClient: HttpClient
  ) { }

  protected basePath = environment.consprefboUrl;

  searchCiticen(searchOfCitizens: SearchOfCitizens) {
    let url = `${this.basePath}/cittadino/find/`;

    let headers = {};

    let nome;
    if (searchOfCitizens.nome) nome = (searchOfCitizens.nome);

    let cognome;
    if (searchOfCitizens.cognome) cognome = (searchOfCitizens.cognome);

    let fiscalCode;
    if (searchOfCitizens.codiceFiscale) fiscalCode = (searchOfCitizens.codiceFiscale);

    let dataNascita;
    if (searchOfCitizens.dataDiNascita) dataNascita = (searchOfCitizens.dataDiNascita);

    if (fiscalCode && nome && cognome && dataNascita) {
      url += fiscalCode;
      headers = {
        nome: nome,
        cognome: cognome,
        dataNascita: dataNascita,
      }

    } else if (nome && cognome && dataNascita) {
      headers = {
        nome: nome,
        cognome: cognome,
        dataNascita: dataNascita,
      }

    } else {
      url += fiscalCode;

    }

    return this.httpClient.get<any>(
      url,
      {
        observe: 'body',
        reportProgress: false,
        headers: headers
      }
    );
  }
}
