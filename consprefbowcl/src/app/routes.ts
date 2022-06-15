import {Routes} from '@angular/router';
import {CitizenSearchComponent} from './content/citizen-search/citizen-search.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ConsensiListComponent} from './content/consensi-list/consensi-list.component';
import {ConsesnsiDetailComponent} from './content/consesnsi-detail/consesnsi-detail.component';
import {FeedbackComponent} from './content/feedback/feedback.component';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export const applicationRoute: Routes = [
  {
    path: '',
    component: CitizenSearchComponent
  }, {
    path: 'cittadino/:fiscalCode/consensi',
    component: ConsensiListComponent
  }, {
    path: 'cittadino/:fiscalCode/consensi/dettaglio/:idInformativa',
    component: ConsesnsiDetailComponent
  }, {
    path: 'cittadino/:fiscalCode/consensi/feedback',
    component: FeedbackComponent
  }, {
    path: '**',
    component: PageNotFoundComponent
  }
];
