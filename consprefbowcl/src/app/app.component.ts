import {Component, OnInit} from '@angular/core';
import {StateService} from "./state.service";
import {faTimesCircle} from "@fortawesome/free-solid-svg-icons";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  constructor(protected state: StateService){
  }

  isLoading = true;
  notOperator = false;
  timesCircle = faTimesCircle;

  ngOnInit(): void {

    this.stateinit()
  }
  async stateinit () {

    if('false'===window.localStorage.getItem('useToken')){

      this.state.login().subscribe(res => {
        this.isLoading = false;

        if (res.status !== 200) {
          this.notOperator = true;

        }
      }, error => {
        this.isLoading = false;
        this.notOperator = true;

      })

    }
    else {
    while (!this.state.token) {

      await new Promise( resolve => setTimeout(resolve, 250) )
    }


    this.state.tokenlcce().subscribe(res => {
      this.isLoading = false;
      if (res.status !== 200) {
        this.notOperator = true;

      }

      if(res.status === 200) {

        window.localStorage.setItem('useToken', 'false');


        this.state.login().subscribe(res => {
          this.isLoading = false;

          if (res.status !== 200) {
            this.notOperator = true;

          }
        }, error => {
          this.isLoading = false;
          this.notOperator = true;

        })
      }

    }, error => {
      this.isLoading = false;
      this.notOperator = true;

    })
  }
  }
}
