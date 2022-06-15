import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';
import {faArrowLeft, faCheckCircle, faTimesCircle} from '@fortawesome/free-solid-svg-icons';
import {StateService} from '../../state.service';
import {InformativaService} from '../../informativa.service';
import {InformativaStatus} from '../../model/informativa';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  arrowLeft = faArrowLeft;
  checkCircle = faCheckCircle;
  timesCircle = faTimesCircle;
  activeRouteSnapshot: ActivatedRouteSnapshot;
  fiscalCode;
  today = new Date();
  feedbackInformativa;
  response;

  status;

  getStatusLabel() {
    let result = "attivato";
    if (this.status === InformativaStatus.ACTIVE) {
      result = "attivato";
    } else if (this.status === InformativaStatus.REVOKED) {
      result = "revocato";
    } else if (this.status === InformativaStatus.EXPIRED) {
      result = "scaduto";
    } else if (this.status === InformativaStatus.NOT_ACTIVE) {
      result = "null";
    } else if (this.status === InformativaStatus.NEGATO) {
      result = "negato";
    }
    return result;
  }

  type: string = '';

  errorList= [];

  isOkResponse() {
    let result = true;
    if (this.response && this.response !== null && this.response.length > 0) result = false;
    return result;
  }

  constructor(
    private router: Router,
    private informativaService: InformativaService,
    protected activatedRoute: ActivatedRoute,
    protected stateService: StateService
  ) {
    this.activeRouteSnapshot = this.activatedRoute.snapshot
  }

  ngOnInit() {
    this.fiscalCode = this.activeRouteSnapshot.params['fiscalCode'];
    this.feedbackInformativa = this.stateService.data.feedbackInformativa;
    this.response = this.stateService.data.response;

    if (this.feedbackInformativa === undefined || this.feedbackInformativa === null) {
      this.back()
    }



    this.status = this.informativaService.getStatus(this.feedbackInformativa)
  }

  back() {
    this.router.navigate(['cittadino', this.fiscalCode, 'consensi']);
  }

}
