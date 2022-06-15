import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {FootbarComponent} from './footbar/footbar.component';
import {RouterModule} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {CitizenSearchComponent} from './content/citizen-search/citizen-search.component';
import {FoundCitizensModalComponent} from './content/citizen-search/found-citizens-modal/found-citizens-modal.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ConsensiListComponent} from './content/consensi-list/consensi-list.component';
import {applicationRoute} from './routes';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {ConsesnsiDetailComponent} from './content/consesnsi-detail/consesnsi-detail.component';
import {FeedbackComponent} from './content/feedback/feedback.component';
import {DateService} from './date.service';
import {StateService} from './state.service';
import { CitizenSearchService } from './content/citizen-search/citizen-search.service';
import { PathLocationStrategy, LocationStrategy } from '@angular/common';
import {InformativaService} from './informativa.service';
import { HttpClientModule } from '@angular/common/http';
import {ProgressSpinnerModule} from 'primeng/primeng';
import {TriStateCheckboxModule} from 'primeng/tristatecheckbox';
import {CheckboxModule} from 'primeng/checkbox';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FootbarComponent,
    PageNotFoundComponent,
    CitizenSearchComponent,
    FoundCitizensModalComponent,
    ConsensiListComponent,
    ConsesnsiDetailComponent,
    FeedbackComponent
  ],
  entryComponents: [
    FoundCitizensModalComponent
  ],
  imports: [
    ProgressSpinnerModule,
    HttpClientModule,
    FontAwesomeModule,
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    HttpModule,
    RouterModule,
    RouterModule.forRoot(
      applicationRoute
    ),
    TriStateCheckboxModule,
    CheckboxModule
  ],
  providers: [
    InformativaService,
    StateService,
    DateService,
    CitizenSearchService,
    {provide: LocationStrategy, useClass: PathLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
