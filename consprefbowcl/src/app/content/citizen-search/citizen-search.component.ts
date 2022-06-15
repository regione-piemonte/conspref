import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {FoundCitizensModalComponent} from './found-citizens-modal/found-citizens-modal.component';
import {SearchOfCitizens} from '../../model/request/search-of-citizens';
import {DateService} from '../../date.service';
import {StateService} from '../../state.service';
import {CitizenSearchService} from './citizen-search.service';

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Component({
  selector: 'app-citizen-search',
  templateUrl: './citizen-search.component.html',
  styleUrls: ['./citizen-search.component.css']
})
export class CitizenSearchComponent implements OnInit, OnDestroy {


  @ViewChild('datanasBen') datanasBenField;


  private _isCodiceFiscale = true;


  private _searchOfCitizens: SearchOfCitizens;

  private _dataDiNascita: string;


  protected ngbModalRef: NgbModalRef;

  constructor(
    protected modalService: NgbModal,
    protected citizenSearchService: CitizenSearchService,
    protected dateService: DateService,
    protected stateService: StateService,
  ) {
  }

  interval

  ngOnInit() {
    this.searchOfCitizens = new SearchOfCitizens();
    this.interval = setInterval(() => {
      this.checkCanProceed();
    }, 500)

  }

  openCercaResultDialog() {
    this.ngbModalRef = this.modalService.open(FoundCitizensModalComponent as Component, {
      size: 'lg',
      // backdrop: 'static'
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
    clearInterval(this.interval)
  }

  inSearchInProgress = false;

  search() {
    this.inSearchInProgress = true;
    let dataDiNascita1 = this.dataDiNascita;
    this.searchOfCitizens.dataDiNascita = dataDiNascita1 ? this.dateService.getNormalizedForServerData(dataDiNascita1) : undefined;

    this.citizenSearchService.searchCiticen(this.searchOfCitizens).subscribe(
      (res) => {
        this.stateService.data.foundCitizens = res;
        this.openCercaResultDialog();
        this.inSearchInProgress = false;
      },
      (err) => {
        this.stateService.data.foundCitizens = [];
        this.openCercaResultDialog();
        this.inSearchInProgress = false;
      }
    )


  }


  get dataDiNascita() {
    let pcFieldValue = this.datanasBenField.nativeElement.value;
    if (pcFieldValue) {
      return pcFieldValue;
    } else {
      return this._dataDiNascita;
    }
  }

  set dataDiNascita(value: string) {
    this._dataDiNascita = value;
  }

  get isCodiceFiscale(): boolean {
    return this._isCodiceFiscale;
  }

  set isCodiceFiscale(value: boolean) {
    this._isCodiceFiscale = value;
  }

  get searchOfCitizens(): SearchOfCitizens {
    return this._searchOfCitizens;
  }

  set searchOfCitizens(value: SearchOfCitizens) {
    this._searchOfCitizens = value;
  }

  canProceed = false

  checkCanProceed() {
    let result = true;

    if (this.isCodiceFiscale) {
      if (!this.searchOfCitizens.codiceFiscale || this.searchOfCitizens.codiceFiscale === "") {
        result = false;
      }
    } else {
      let dataDiNascita1 = this.dataDiNascita;
      this.searchOfCitizens.dataDiNascita = dataDiNascita1 ? this.dateService.getNormalizedForServerData(dataDiNascita1) : undefined;

      if (!this.searchOfCitizens.dataDiNascita || this.searchOfCitizens.dataDiNascita === "") {
        result = false;
      } else if (!this.searchOfCitizens.cognome || this.searchOfCitizens.cognome === "") {
        result = false;
      } else if (!this.searchOfCitizens.nome || this.searchOfCitizens.nome === "") {
        result = false;
      }
    }

    this.canProceed = result;
  }

  clearData(isCodice) {
    this.isCodiceFiscale = isCodice;
    this.searchOfCitizens = new SearchOfCitizens();
  }
}
