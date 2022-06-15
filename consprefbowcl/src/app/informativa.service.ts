import { Injectable } from '@angular/core';
import {Informativa, InformativaStatus} from './model/informativa';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environments/environment';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable({
  providedIn: 'root'
})
export class InformativaService {

  constructor(
    protected httpClient: HttpClient
  ) { }

  protected basePath = environment.consprefboUrl;

  updateInformativa(informativa, codice_fiscale) {
    let url = `${this.basePath}/informativa/update/${encodeURIComponent(codice_fiscale)}`;

    return this.httpClient.put<any>(
      url,
      informativa,
      {
        observe: 'body',
        reportProgress: false
      }
    );
  }

  findInformativa(codice_fiscale, informativaId) {
    let informativaUrl = "";
    if (informativaId) informativaUrl = "/"+informativaId;

    let url = `${this.basePath}/informativa/find/${codice_fiscale}`+informativaUrl;

    return this.httpClient.get<any>(
      url,
      {
        observe: 'body',
        reportProgress: false
      }
    );
  }

  loadAslList() {
    let url = `${this.basePath}/informativa/get-asl-list`;

    return this.httpClient.get<any>(
      url,
      {
        observe: 'body',
        reportProgress: false
      }
    );
  }

  findInformativas(codice_fiscale) {
   return this.findInformativa(codice_fiscale, undefined);
  }

  public getStatus(informativa: Informativa): InformativaStatus {
    let result: InformativaStatus;

    let allConsensusesAreNegated = true;

    let consensos = informativa.consenso_list;
    if (consensos) {
      consensos.forEach((consenso) => {
        if (consenso.tipo_stato.codice === "A") {
          allConsensusesAreNegated = false;
        }
      })
    }



    let date = new Date();
    if (!informativa.data_decorrenza && !informativa.data_scadenza) {
      result = InformativaStatus.NOT_ACTIVE;

    } else if (new Date(informativa.data_decorrenza) < date && (!informativa.data_scadenza || informativa.data_scadenza > date)) {
      if (allConsensusesAreNegated) {
        result = InformativaStatus.REVOKED;

      } else {
        result = InformativaStatus.ACTIVE;

      }
    } else {
      result = InformativaStatus.EXPIRED;
    }

    return result;
  }

}
