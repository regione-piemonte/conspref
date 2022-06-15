import {Component, OnInit} from '@angular/core';
import {faArrowLeft, faCheckCircle, faTimesCircle} from '@fortawesome/free-solid-svg-icons';
import {faCircle, faClock} from '@fortawesome/free-regular-svg-icons';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';
import {Informativa, InformativaStatus} from '../../model/informativa';
import {SearchOfCitizens} from '../../model/request/search-of-citizens';
import {CitizenSearchService} from '../citizen-search/citizen-search.service';
import {Cittadino} from '../../model/cittadino';
import {StateService} from '../../state.service';
import {InformativaService} from '../../informativa.service';
import {Fonte} from "../../model/fonte";
import {Consenso} from "../../model/consenso";
import {$e} from "codelyzer/angular/styles/chars";

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

@Component({
  selector: 'app-consesnsi-detail',
  templateUrl: './consesnsi-detail.component.html',
  styleUrls: ['./consesnsi-detail.component.css']
})
export class ConsesnsiDetailComponent implements OnInit {

  arrowLeft = faArrowLeft;

  notActiveIcon = faCircle;
  revokedIcon = faTimesCircle;
  activeIcon = faCheckCircle;
  expiredIcon = faClock;

  informativa: Informativa;
  fiscalCode: string;
  id_informativa: string;
  activeRouteSnapshot: ActivatedRouteSnapshot;
  citizen: Cittadino;

  aslList = [];

  constructor(
    private router: Router,
    protected activatedRoute: ActivatedRoute,
    protected citizenSearchService: CitizenSearchService,
    protected stateService: StateService,
    protected informativaService: InformativaService,
  ) {
    this.activeRouteSnapshot = this.activatedRoute.snapshot
  }

  statusOfInformativa: InformativaStatus;

  htmlTemplate = '';

  isActive() {
    return !this.isNotActive() && this.statusOfInformativa === InformativaStatus.ACTIVE;
  }

  isRevoked() {
    return !this.isNotActive() && this.statusOfInformativa === InformativaStatus.REVOKED;
  }

  isExpired() {
    return !this.isNotActive() && this.statusOfInformativa === InformativaStatus.EXPIRED;
  }

  isNotActive() {
    return this.notActive || !this.informativa.consenso_list;
  }

  ngOnInit() {
    this.fiscalCode = this.activeRouteSnapshot.params['fiscalCode'];
    this.id_informativa = this.activeRouteSnapshot.params['idInformativa'];

    this.loadCitizen();
  }

  oldInformativa;

  LoadInformativaList() {
    this.informativaService.findInformativa(this.citizen.codice_fiscale, this.id_informativa)
      .subscribe((res) => {
        let filteredRes;
        if (!res || res.length === 0) {
          this.onNotFound();
        } else {
          filteredRes = res.filter(inf =>
            this.id_informativa + "" === inf.id_informativa + ""
          );
          if (filteredRes.length === 0) {

            this.onNotFound();
          } else {
            this.informativa = filteredRes[0];
            this.oldInformativa = JSON.parse(JSON.stringify(filteredRes[0]));

            this.statusOfInformativa = this.informativaService.getStatus(this.informativa);
            if (this.informativa.consenso_list) {
              this.informativa.consenso_list = this.informativa.consenso_list.sort((a, b) => a.tipo_asr.codice > b.tipo_asr.codice ? 1 : -1);
              this.oldInformativa.consenso_list = this.oldInformativa.consenso_list.sort((a, b) => a.tipo_asr.codice > b.tipo_asr.codice ? 1 : -1);

              if (this.informativa.tipo_consenso.codice === 'A') {
                this.informativa.consenso_list.forEach(cons => {
                  cons.valore_consenso.codice = cons.valore_consenso.codice === "SI";
                });
                this.oldInformativa.consenso_list.forEach(cons => {
                  cons.valore_consenso.codice = cons.valore_consenso.codice === "SI";
                });
              }
            }
            this.loadAlsList();

          }
        }
      }, (error => {

        this.onNotFound()
      }))
  }

  onNotFound() {
    this.router.navigate(['/']);
  }

  notActive = false;

  isInTheAslList(code) {
    let result = false;
    this.informativa.consenso_list.forEach(consenso => {
      if (consenso.tipo_asr && consenso.tipo_asr.codice === code) {
        result = true;
      }
    });

    return result
  }

  createConsensus(asl) {
    let consensus = new Consenso();
    consensus.tipo_asr = {
      codice: asl.codice,
      descrizione: asl.descrizione,
    };
    consensus.id_aura = this.citizen.id_aura;
    consensus.cf_cittadino = this.citizen.codice_fiscale;
    consensus.informativa = new Informativa;
    consensus.data_acquisizione = new Date();
    consensus.informativa.id_informativa = this.informativa.id_informativa;

    consensus.valore_consenso = {
      codice: this.informativa.tipo_consenso.codice === 'A' ? null : "NO",
      descrizione: undefined
    };
    consensus.tipo_stato = {
      codice: this.informativa.tipo_consenso.codice === 'A' ? null : "R",
      descrizione: undefined
    }
    consensus.fonte = {
      codice: "WA_CITT",
      descrizione: undefined,
      tipo_fonte: {
        codice: "WA_CITT",
        descrizione: undefined,
      }
    }
    return consensus;
  }

  regionaleConsState = "NO";

  loadAlsList() {
    if (!this.informativa.consenso_list) {
      this.informativa.consenso_list = [];
    }
    if (!this.oldInformativa.consenso_list) {
      this.oldInformativa.consenso_list = [];
    }
    if (this.informativa.tipo_consenso.codice === 'A') {
      this.notActive = true;
      this.informativaService.loadAslList().subscribe(
        (res) => {
          res.forEach(asl => {
            if (!this.isInTheAslList(asl.codice)) {
              this.informativa.consenso_list.push(this.createConsensus(asl))
              this.oldInformativa.consenso_list.push(this.createConsensus(asl))
            } else {
              this.notActive = false;
            }
          });
          this.informativa.consenso_list = this.informativa.consenso_list.sort((a, b) => a.tipo_asr.codice > b.tipo_asr.codice ? 1 : -1);
          this.oldInformativa.consenso_list = this.oldInformativa.consenso_list.sort((a, b) => a.tipo_asr.codice > b.tipo_asr.codice ? 1 : -1);
        });
    } else if (!this.isInTheAslList("999")) {
      this.notActive = true;
      this.informativa.consenso_list.push(this.createConsensus({codice: "999"}));
      this.oldInformativa.consenso_list.push(this.createConsensus({codice: "999"}))
    } else {
      this.notActive = false;
      this.regionaleConsState = this.informativa.consenso_list[0].valore_consenso.codice
    }
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
            this.LoadInformativaList()
          }
        },
        (error) => {

          this.onNotFound()
        }
      )
  }

  agreed: boolean;

  changeTipoStato($event, consensusIndex) {
    let value = $event.value;
    if (value === null && this.oldInformativa.consenso_list[consensusIndex].valore_consenso.codice !== null) {
      value = true
    }
    this.informativa.consenso_list[consensusIndex].valore_consenso.codice = value;


  }

  markUpdatedConsensuses() {
    this.informativa.consenso_list.forEach(newInforAsl => {
      if (this.notActive) {
        newInforAsl.updated = true;
      } else {
        this.oldInformativa.consenso_list.forEach(oldInforAsl => {
          if (newInforAsl.tipo_asr && oldInforAsl.tipo_asr &&
            newInforAsl.tipo_asr.codice === oldInforAsl.tipo_asr.codice &&
            newInforAsl.valore_consenso.codice !== oldInforAsl.valore_consenso.codice) {
            newInforAsl.updated = true;
          }
        })
      }
    })

  }

  back() {
    window.history.back();
  }

  isSaving = false;
  test: any;

  formatConsensuses() {
    this.informativa.consenso_list.forEach(cons => {
      if (cons.valore_consenso.codice) {
        cons.valore_consenso.codice = 'SI';
        cons.tipo_stato.codice = 'A';
      } else if (cons.valore_consenso.codice == false) {
        cons.valore_consenso.codice = 'NO';
        cons.tipo_stato.codice = 'R';
      } else {
        cons.updated = false;
      }
    })
  }

  save() {
    this.isSaving = true;
    if (this.informativa.tipo_consenso.codice === "R") {
      this.informativa.consenso_list[0].valore_consenso.codice = this.regionaleConsState;
      this.informativa.consenso_list[0].tipo_stato.codice = this.regionaleConsState === "SI" ? 'A' : 'R';
    }

    this.markUpdatedConsensuses();
    if (this.informativa.tipo_consenso.codice === "A") {
      this.formatConsensuses();
    }

    this.informativaService.updateInformativa(this.informativa, this.fiscalCode)
      .subscribe((res) => {

        this.isSaving = false;
        this.stateService.data.response = res;
        this.stateService.data.feedbackInformativa = this.informativa;
        this.router.navigate(['cittadino', this.fiscalCode, 'consensi', 'feedback']);
      }, (error => {

        this.stateService.data.response = error;
        this.isSaving = false;
        this.router.navigate(['cittadino', this.fiscalCode, 'consensi', 'feedback']);
      }))
  }

  selectAll(checked) {
    this.informativa.consenso_list.forEach((consensus) => {
      consensus.tipo_stato.codice = checked;
      consensus.valore_consenso.codice = checked;
    })
  }
}
