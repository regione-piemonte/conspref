import { Injectable } from '@angular/core';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class DateService {

  constructor() { }


  getNormalizedForServerData(date: string) {
    let strings = date.split("-");
    return strings[2]+"/"+strings[1]+"/"+strings[0];
  }

  getFromNormalized(date: string) {
    let strings = date.split("/");
    return strings[2]+"-"+strings[1]+"-"+strings[0]
  }

}
