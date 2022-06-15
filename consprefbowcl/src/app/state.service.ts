import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import { ActivatedRoute } from '@angular/router';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class StateService {

  public token: string;



  constructor(private route: ActivatedRoute,

    protected httpClient: HttpClient) {

      //RECUPERO DEL PARAMETRO TOKEN PASSATO DALLA URL PRINCIPALE

      this.route.queryParams.subscribe(params => {

        this.token = params['token'];



      });



    }

  protected basePath = environment.consprefboUrl;

  login() {

    let url = `${this.basePath}/cittadino/login/`;

    return this.httpClient.get<any>(
      url,
      {
        observe: 'response',
        reportProgress: false
      }
    );
  }

  tokenlcce() {

    let esito = `${this.basePath}/cittadino/token/${encodeURIComponent(this.token)}`;

    return this.httpClient.get<any>(
      esito,
      {
        observe: 'response',
        reportProgress: false
      }
    );
  }

  get data() {
    return this._data;
  }

  set data(value) {
    this._data = value;
  }

  private _data: any = {};

}
