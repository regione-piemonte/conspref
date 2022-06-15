import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { environment } from '../../environments/environment';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  public logoutSistemaPiemonte: string = environment.consprefboLogout;
  public Pua: string = environment.urlPua;
  constructor(
    protected router: Router
  ) { }

  showHomeStripe() {
    return this.router.url !== '/'
  }

  ngOnInit() {
  }

  Logout():string{
    return this.logoutSistemaPiemonte;
  }
  GetURLPua():string{
    return this.Pua;
  }
}
