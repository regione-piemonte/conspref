import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Route, Router} from '@angular/router';
import {faCheckCircle, faChevronDown, faChevronUp, faTimesCircle} from '@fortawesome/free-solid-svg-icons';
import {faCircle, faClock} from '@fortawesome/free-regular-svg-icons';
import {CitizenSearchService} from '../citizen-search/citizen-search.service';
import {SearchOfCitizens} from '../../model/request/search-of-citizens';
import {Cittadino} from '../../model/cittadino';
import {Informativa, InformativaStatus} from '../../model/informativa';
import {InformativaService} from '../../informativa.service';
import {TipoASR} from '../../model/tipo-asr';

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Component({
  selector: 'app-consensi-list',
  templateUrl: './consensi-list.component.html',
  styleUrls: ['./consensi-list.component.css']
})
export class ConsensiListComponent implements OnInit {

  chevronDown = faChevronDown;
  chevronUp = faChevronUp;

  notActiveIcon = faCircle;
  revokedIcon = faTimesCircle;
  activeIcon = faCheckCircle;
  expiredIcon = faClock;

  activeRouteSnapshot: ActivatedRouteSnapshot;

  fiscalCode: string = '';
  informativas: Informativa[] = [];
  notFilteredInformativas: Informativa[] = [];
  citizen: Cittadino;

  isFilterOpened: boolean = true;

  aslList: TipoASR[] = [];
  deleganteList = [];

  selectedDateFilter = 'Tutte';
  selectedStatoFilter = 'Tutte';
  selectedAslFilter = 'Tutte';
  selectedDelegatoFilter = 'Tutte';
  selectedDeleganteFilter = 'Tutte';

  finishedLoadingOfCitizen: boolean = false;

  constructor(
    protected activatedRoute: ActivatedRoute,
    private router: Router,
    protected citizenSearchService: CitizenSearchService,
    protected informativaService: InformativaService
  ) {
    this.activeRouteSnapshot = this.activatedRoute.snapshot
  }

  filterByDate(informativa: Informativa) {
    let pass = true;

    let informativaDate = new Date(informativa.data_decorrenza);
    let minDate;

    if (this.selectedDateFilter === 'ultimoMese') { // last month
      minDate = new Date(new Date().getTime() - 2592000000);
    } else if (this.selectedDateFilter === 'ultimi6Mesi') { // last 6 months
      minDate = new Date(new Date().getTime() - 15552000000);
    } else if (this.selectedDateFilter === 'ultimi5Anni') { // last 5 years
      minDate = new Date(new Date().getTime() - 157680000000);
    }

    if (this.selectedDateFilter !== 'Tutte') {


      pass = informativaDate >= minDate;
    }
    return pass;
  }

  filterByStatus(informativa: Informativa) {
    let pass = true;

    if (this.selectedStatoFilter === 'Attivi') {
      if (!this.isActive(informativa)) {
        pass = false;
      }

    } else if (this.selectedStatoFilter === 'NonAttivo') {
      if (!this.isNotActive(informativa)) {
        pass = false;
      }

    } else if (this.selectedStatoFilter === 'Scaduti') {
      if (!this.isExpired(informativa)) {
        pass = false;
      }

    } else if (this.selectedStatoFilter === 'Revocato') {
      if (!this.isRevoked(informativa)) {
        pass = false;
      }

    } else if (this.selectedStatoFilter === 'Negato') {
      if (!this.isNegato(informativa)) {
        pass = false;
      }
    }

    return pass;
  }

  filterByDelegato(informativa: Informativa) {
    let pass = false;

    if (this.selectedDelegatoFilter === 'Tutte') {
      pass = true;
    } else {
      informativa.consenso_list.forEach(consenso => {
        if (consenso.cf_delegato === this.selectedDelegatoFilter) {
          pass = true;
        }
      })
    }

    return pass;
  }

  filterByAsl(informativa: Informativa) {
    let pass = false;

    if (this.selectedAslFilter === 'Tutte') {
      pass = true;
    } else {
      informativa.consenso_list.forEach(consenso => {
        if (consenso.tipo_asr.codice === this.selectedAslFilter) {
          pass = true;
        }
      })
    }

    return pass;
  }

  filter() {
    let informativas = [];

    this.notFilteredInformativas.forEach(informativa => {
      let addToInformativas = true;

      if (!this.filterByDate(informativa)) {
        addToInformativas = false;
      }
      if (!this.filterByStatus(informativa)) {
        addToInformativas = false;
      }
      if (!this.filterByAsl(informativa)) {
        addToInformativas = false;
      }
      if (!this.filterByDelegato(informativa)) {
        addToInformativas = false;
      }

      if (addToInformativas) {
        informativas.push(informativa)
      }
    });

    this.informativas = informativas;
  }

  isActive(informativa: Informativa) {
    return !this.isNotActive(informativa) && this.informativaService.getStatus(informativa) === InformativaStatus.ACTIVE;
  }

  isRevoked(informativa: Informativa) {
    return !this.isNotActive(informativa) && this.informativaService.getStatus(informativa) === InformativaStatus.REVOKED;
  }

  isExpired(informativa: Informativa) {
    return !this.isNotActive(informativa) && this.informativaService.getStatus(informativa) === InformativaStatus.EXPIRED;
  }

  isNotActive(informativa: Informativa) {
    return !informativa.consenso_list;
  }

  isNegato(informativa: Informativa) {
    return !this.isNotActive(informativa) && this.informativaService.getStatus(informativa) === InformativaStatus.NEGATO;
  }

  ngOnInit() {
    this.fiscalCode = this.activeRouteSnapshot.params['fiscalCode'];

    this.loadCitizen();
  }

  containFiscalCodeInDelegatoList(cf) {
    let result = false;
    this.deleganteList.forEach(delegante => {
      if (delegante.fiscalCode === cf) result = true;
    })

    return result;
  }

  fillFilters() {
    this.informativas.forEach(informativa => {
      if (informativa.consenso_list) {
        informativa.consenso_list.forEach(consenso => {
          this.aslList.push(consenso.tipo_asr);

          if (consenso.delegato_nome && consenso.delegato_nome !== '' &&
            consenso.delegato_cognome && consenso.delegato_cognome &&
            !this.containFiscalCodeInDelegatoList(consenso.cf_delegato)) {
            this.deleganteList.push({
              fiscalCode: consenso.cf_delegato,
              nome: consenso.delegato_nome,
              cognome: consenso.delegato_cognome
            })
          }
        })
      }
    });
    this.aslList = this.aslList.sort((a, b) => a.codice > b.codice ? 1 : -1)
  }

  loadInformativas() {
    this.informativaService.findInformativas(this.citizen.codice_fiscale)
      .subscribe((res) => {
        this.informativas = res;
        this.notFilteredInformativas = this.informativas;
        this.finishedLoadingOfCitizen = true;
        this.fillFilters();
      }, (error => {
        this.finishedLoadingOfCitizen = true;
      }))
  }

  onNotFound() {
    this.finishedLoadingOfCitizen = true;
    this.router.navigate(['/']);
  }

  openDetailPage(idInformativa) {
    this.router.navigate(['./dettaglio', idInformativa], {relativeTo: this.activatedRoute});
  }

  loadCitizen() {
    let searchOfCitizens = new SearchOfCitizens();
    searchOfCitizens.codiceFiscale = this.fiscalCode;
    this.citizenSearchService.searchCiticen(searchOfCitizens)
      .subscribe(
        (res) => {

          this.citizen = res.length > 0 ? res[0] : undefined;
          if (!this.citizen) {
            this.onNotFound()
          } else {
            this.loadInformativas()
          }
        },
        (error) => {
          this.onNotFound()
        }
      )
  }
}
