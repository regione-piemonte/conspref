import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Cittadino} from '../../../model/cittadino';
import * as $ from 'jquery';
import {Router} from '@angular/router';
import {StateService} from '../../../state.service';

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Component({
  selector: 'app-found-citizens-modal',
  templateUrl: './found-citizens-modal.component.html',
  styleUrls: ['./found-citizens-modal.component.css']
})
export class FoundCitizensModalComponent implements OnInit, OnDestroy {

  foundCitizens: Cittadino[] = [];

  constructor(
    public activeModal: NgbActiveModal,
    private router: Router,
    private stateService: StateService
  ) {
  }

  ngOnInit() {
    this.foundCitizens = this.stateService.data.foundCitizens;
  }

  ngOnDestroy(): void {
    $(".modal-backdrop").remove();
    $(".modal").remove();
    this.stateService.data.foundCitizens = [];
  }

  chooseCitizen(citizen: Cittadino) {
    this.ngOnDestroy();
    this.router.navigate(['/cittadino', citizen.codice_fiscale, 'consensi']);
  }

  dismiss() {
    this.activeModal.dismiss()
  }


}
