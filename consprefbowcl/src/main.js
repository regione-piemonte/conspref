(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<app-navbar></app-navbar>\n<div class=\"container col-12 col-md-11 col-lg-10 col-xl-9\">\n  <router-outlet></router-outlet>\n</div>\n<app-footbar></app-footbar>\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'app works!';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/http */ "./node_modules/@angular/http/fesm5/http.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _navbar_navbar_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./navbar/navbar.component */ "./src/app/navbar/navbar.component.ts");
/* harmony import */ var _footbar_footbar_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./footbar/footbar.component */ "./src/app/footbar/footbar.component.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _page_not_found_page_not_found_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./page-not-found/page-not-found.component */ "./src/app/page-not-found/page-not-found.component.ts");
/* harmony import */ var _content_citizen_search_citizen_search_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./content/citizen-search/citizen-search.component */ "./src/app/content/citizen-search/citizen-search.component.ts");
/* harmony import */ var _content_citizen_search_found_citizens_modal_found_citizens_modal_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./content/citizen-search/found-citizens-modal/found-citizens-modal.component */ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.ts");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/fesm5/ng-bootstrap.js");
/* harmony import */ var _content_consensi_list_consensi_list_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./content/consensi-list/consensi-list.component */ "./src/app/content/consensi-list/consensi-list.component.ts");
/* harmony import */ var _routes__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./routes */ "./src/app/routes.ts");
/* harmony import */ var _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! @fortawesome/angular-fontawesome */ "./node_modules/@fortawesome/angular-fontawesome/fesm5/angular-fontawesome.js");
/* harmony import */ var _content_consesnsi_detail_consesnsi_detail_component__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./content/consesnsi-detail/consesnsi-detail.component */ "./src/app/content/consesnsi-detail/consesnsi-detail.component.ts");
/* harmony import */ var _content_feedback_feedback_component__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./content/feedback/feedback.component */ "./src/app/content/feedback/feedback.component.ts");
/* harmony import */ var _soap_service__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./soap.service */ "./src/app/soap.service.ts");
/* harmony import */ var _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ./soap-request-factory.service */ "./src/app/soap-request-factory.service.ts");
/* harmony import */ var _date_service__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ./date.service */ "./src/app/date.service.ts");
/* harmony import */ var _state_service__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! ./state.service */ "./src/app/state.service.ts");
/* harmony import */ var _content_citizen_search_nobody_found_error_nobody_found_error_component__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! ./content/citizen-search/nobody-found-error/nobody-found-error.component */ "./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.ts");
/* harmony import */ var _content_citizen_search_citizen_search_service__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! ./content/citizen-search/citizen-search.service */ "./src/app/content/citizen-search/citizen-search.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_23__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _informativa_service__WEBPACK_IMPORTED_MODULE_24__ = __webpack_require__(/*! ./informativa.service */ "./src/app/informativa.service.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_25__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var primeng_primeng__WEBPACK_IMPORTED_MODULE_26__ = __webpack_require__(/*! primeng/primeng */ "./node_modules/primeng/primeng.js");
/* harmony import */ var primeng_primeng__WEBPACK_IMPORTED_MODULE_26___default = /*#__PURE__*/__webpack_require__.n(primeng_primeng__WEBPACK_IMPORTED_MODULE_26__);
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



























var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"],
                _navbar_navbar_component__WEBPACK_IMPORTED_MODULE_5__["NavbarComponent"],
                _footbar_footbar_component__WEBPACK_IMPORTED_MODULE_6__["FootbarComponent"],
                _page_not_found_page_not_found_component__WEBPACK_IMPORTED_MODULE_8__["PageNotFoundComponent"],
                _content_citizen_search_citizen_search_component__WEBPACK_IMPORTED_MODULE_9__["CitizenSearchComponent"],
                _content_citizen_search_found_citizens_modal_found_citizens_modal_component__WEBPACK_IMPORTED_MODULE_10__["FoundCitizensModalComponent"],
                _content_consensi_list_consensi_list_component__WEBPACK_IMPORTED_MODULE_12__["ConsensiListComponent"],
                _content_consesnsi_detail_consesnsi_detail_component__WEBPACK_IMPORTED_MODULE_15__["ConsesnsiDetailComponent"],
                _content_feedback_feedback_component__WEBPACK_IMPORTED_MODULE_16__["FeedbackComponent"],
                _content_citizen_search_nobody_found_error_nobody_found_error_component__WEBPACK_IMPORTED_MODULE_21__["NobodyFoundErrorComponent"],
            ],
            entryComponents: [
                _content_citizen_search_found_citizens_modal_found_citizens_modal_component__WEBPACK_IMPORTED_MODULE_10__["FoundCitizensModalComponent"]
            ],
            imports: [
                primeng_primeng__WEBPACK_IMPORTED_MODULE_26__["ProgressSpinnerModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_25__["HttpClientModule"],
                _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_14__["FontAwesomeModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_11__["NgbModule"].forRoot(),
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                _angular_http__WEBPACK_IMPORTED_MODULE_3__["HttpModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_7__["RouterModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_7__["RouterModule"].forRoot(_routes__WEBPACK_IMPORTED_MODULE_13__["applicationRoute"])
            ],
            providers: [
                _informativa_service__WEBPACK_IMPORTED_MODULE_24__["InformativaService"],
                _soap_service__WEBPACK_IMPORTED_MODULE_17__["SoapService"],
                _state_service__WEBPACK_IMPORTED_MODULE_20__["StateService"],
                _date_service__WEBPACK_IMPORTED_MODULE_19__["DateService"],
                _content_citizen_search_citizen_search_service__WEBPACK_IMPORTED_MODULE_22__["CitizenSearchService"],
                _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_18__["SoapRequestFactoryService"],
                { provide: _angular_common__WEBPACK_IMPORTED_MODULE_23__["LocationStrategy"], useClass: _angular_common__WEBPACK_IMPORTED_MODULE_23__["PathLocationStrategy"] }
            ],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/content/citizen-search/citizen-search.component.css":
/*!*********************************************************************!*\
  !*** ./src/app/content/citizen-search/citizen-search.component.css ***!
  \*********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/content/citizen-search/citizen-search.component.html":
/*!**********************************************************************!*\
  !*** ./src/app/content/citizen-search/citizen-search.component.html ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div style=\"padding: 15px; margin-top: 20px; margin-bottom: 30px;\">\n  <div id=\"benPanel\" class=\"tondo ombragenerale rigabiancafontnero\"\n       style=\"padding: 15px;\">\n    <div class=\"row\" style=\"padding-bottom: 5px\">\n      <strong style=\"font-size: 16px;\">Beneficiario</strong>\n    </div>\n\n    <div class=\"row\" style=\"font-size: 12px\">\n      <span class=\"col-md-12 m-0 p-0\">\n        Se i dati sono uguali al dichiarante clicca sul pulsante sottostante.\n        <label style=\"\" id=\"copiadatidic\"></label>\n      </span>\n      <div class=\"col-md-6\"\n\n           style=\"padding-left: 0; padding-right: 0\">\n        <button type=\"button\" class=\"btn btn-outline-primary btn-block\"\n                style=\"padding: 2px 2px 2px 5px;border: 2px solid #1154a2 !important; height: 30px; display: inline-flex; margin-top: 5px;\" id=\"recuperadati\">\n          <strong style=\"color: #1154a2; font-size: 12px\">Recupera dati dichiarante </strong>\n        </button>\n      </div>\n      <div class=\"col-md-6\"></div>\n    </div>\n    <div class=\"row\" style=\"padding-top: 8px\">\n      <strong>Cerca il dichiarante tramite Codice Fiscale o\n        Cognome e nome dell'assistito.</strong>\n    </div>\n    <div class=\"row\" style=\"margin-top: 15px; font-size: 12px\">\n      <ul class=\"nav nav-tabs nav-Tabs-principales nav-justified\"\n          style=\"width: 100%\" role=\"tablist\" id=\"prodTabsben\">\n        <li class=\"nav-item text-center rigagrigianobordo active\"\n            role=\"presentation\"><a role=\"tab\" class=\"nav-link active\" data-toggle=\"tab\"\n                                   href=\"#codfiscben\" style=\"border-radius: 0;\">\n          <img class=\"img-fluid\" src=\"assets/img/user/iconauomo.png\"> <br>\n          <strong style=\"color: #1154a2;\">Codice Fiscale</strong></a>\n        </li>\n        <li class=\"nav-item text-center rigagrigianobordo\" role=\"presentation\"><a\n          role=\"tab\" class=\"nav-link\" data-toggle=\"tab\"\n          href=\"#cognnomben\" style=\"border-radius: 0;\">\n          <img class=\"img-fluid\" src=\"assets/img/user/iconauomo.png\"><br>\n          <strong style=\"color: #1154a2;\">Cognome e nome</strong></a></li>\n      </ul>\n      <div class=\"row\" style=\"width: 100%\">\n        <div class=\"tab-content\"\n             style=\"width: 100%; background: white; margin-bottom: 15px\">\n\n          <!-- Cognome input form -->\n          <div class=\"tab-pane pt-4 active\" id=\"codfiscben\"\n               role=\"tabpanel\" style=\"width: 100%; background: white\">\n            <label class=\"control-label\" for=\"codFiscaleben\"> Codice fiscale</label>\n            <input class=\"form-control\" type=\"text\" style=\"height: 30px; text-transform: uppercase;\" [(ngModel)]=\"searchOfCitizens.codiceFiscale\"\n                   id=\"codFiscaleben\" name=\"codFiscaleben\">\n          </div>\n\n          <!-- Cognome input form -->\n          <div id=\"cognnomben\" class=\"tab-pane pt-4\" role=\"tabpanel\"\n               style=\"width: 100%; background: white\">\n            <div class=\"row\" style=\"padding-top: 5px\">\n              <label class=\"control-label\" for=\"cognomeben\">Cognome</label>\n              <input type=\"text\" class=\"form-control\" id=\"cognomeben\"\n              style=\"height: 30px;text-transform: uppercase;\" name=\"cognomeben\" [(ngModel)]=\"searchOfCitizens.cognome\">\n            </div>\n\n            <div class=\"row\" style=\"padding-top: 5px\">\n              <label class=\"control-label\" for=\"nome\">Nome</label>\n              <input type=\"text\" class=\"form-control\"\n                     style=\"margin-bottom: 8px; height: 30px;text-transform: uppercase;\" [(ngModel)]=\"searchOfCitizens.nome\"\n                     id=\"nomeben\" name=\"nomeben\">\n            </div>\n\n            <div class=\"row\" style=\"padding-top: 5px\">\n              <label class=\"control-label\" for=\"nome\">Data di Nascita</label>\n            </div>\n            <input type=\"date\" class=\"form-control d-sm-block d-md-none\" style=\"width: 100%; height: 35px\"\n                   [(ngModel)]=\"dataDiNascita\" (ngModelChange)=\"datanasBen.value = $event\">\n            <div class=\"row d-sm-none d-none d-md-block\" style=\"width: 100%\">\n              <div class=\"input-group input-append date\"\n                   data-date-format=\"yyyy-mm-dd\" data-provide=\"datepicker\">\n                <span class=\"input-group-addon add-on\"></span>\n                <input [ngModel]=\"dataDiNascita\"\n                       type=\"date\" class=\"form-control\" name=\"datanasBen\"\n                #datanasBen placeholder=\"gg/mm/aaaa\"\n                style=\"border-right-width: 1px; padding-right: 12px;\n                background: url('assets/img/user/calendario.png') no-repeat right 10px center;\">\n              </div>\n            </div>\n          </div>\n\n        </div>\n      </div>\n      <div class=\"row\"\n           style=\"width: 100%; bottom: 0; padding-bottom: 0;\">\n        <div class=\"col-md-6\"></div>\n        <div class=\"col-md-6\"\n             style=\"padding-left: 0; padding-right: 0; display: flex; flex-direction: row-reverse\">\n          <button [disabled]=\"inSearchInProgress\" style=\"max-width: 150px\" type=\"button\" (click)=\"search()\" class=\"btn btn-primary btn-block\"\n          href=\"#\" data-toggle=\"modal\" data-target=\".beneficiario\">Cerca\n          </button>\n          <p-progressSpinner *ngIf=\"inSearchInProgress\" [style]=\"{width: '35px', height: '35px', marginRight: '15px'}\"></p-progressSpinner>\n        </div>\n      </div>\n    </div>\n\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/content/citizen-search/citizen-search.component.ts":
/*!********************************************************************!*\
  !*** ./src/app/content/citizen-search/citizen-search.component.ts ***!
  \********************************************************************/
/*! exports provided: CitizenSearchComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CitizenSearchComponent", function() { return CitizenSearchComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/fesm5/ng-bootstrap.js");
/* harmony import */ var _found_citizens_modal_found_citizens_modal_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./found-citizens-modal/found-citizens-modal.component */ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.ts");
/* harmony import */ var _model_request_search_of_citizens__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../model/request/search-of-citizens */ "./src/app/model/request/search-of-citizens.ts");
/* harmony import */ var _date_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../date.service */ "./src/app/date.service.ts");
/* harmony import */ var _state_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../state.service */ "./src/app/state.service.ts");
/* harmony import */ var _citizen_search_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./citizen-search.service */ "./src/app/content/citizen-search/citizen-search.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var CitizenSearchComponent = /** @class */ (function () {
    function CitizenSearchComponent(modalService, citizenSearchService, dateService, stateService) {
        this.modalService = modalService;
        this.citizenSearchService = citizenSearchService;
        this.dateService = dateService;
        this.stateService = stateService;
        // true - search by Fiscal Code. false - search by Surname and name
        this._isCodiceFiscale = true;
        this.inSearchInProgress = false;
    }
    CitizenSearchComponent.prototype.ngOnInit = function () {
        this.searchOfCitizens = new _model_request_search_of_citizens__WEBPACK_IMPORTED_MODULE_3__["SearchOfCitizens"]();

    };
    CitizenSearchComponent.prototype.openCercaResultDialog = function () {
        this.ngbModalRef = this.modalService.open(_found_citizens_modal_found_citizens_modal_component__WEBPACK_IMPORTED_MODULE_2__["FoundCitizensModalComponent"], {
            size: 'lg',
        });
    };
    CitizenSearchComponent.prototype.ngOnDestroy = function () {
        this.ngbModalRef = null;
    };
    CitizenSearchComponent.prototype.search = function () {
        var _this = this;
        this.inSearchInProgress = true;
        var dataDiNascita1 = this.dataDiNascita;
        this.searchOfCitizens.dataDiNascita = dataDiNascita1 ? this.dateService.getNormalizedForServerData(dataDiNascita1) : undefined;
        this.citizenSearchService.searchCitizen(this.searchOfCitizens)
            .done(function (res) {
            _this.stateService.data.foundCitizens = _this.citizenSearchService.parseCittadinos(res);
            _this.openCercaResultDialog();
            _this.inSearchInProgress = false;
        })
            .fail(function (err) {

            _this.inSearchInProgress = false;
        });
    };
    Object.defineProperty(CitizenSearchComponent.prototype, "dataDiNascita", {
        /**
         * returns value of pc birthday date field if it's present
         * if it isn't, returns dataDiNascita variable.
         *
         * It's needed for the syncing values of the both PC and mobile fields of birthday date fields.
         * usual [(ngModel)] unused because of bootstrap datepicker that doesn't evolve *change* event
         */
        get: function () {
            var pcFieldValue = this.datanasBenField.nativeElement.value;
            if (pcFieldValue) {
                return pcFieldValue;
            }
            else {
                return this._dataDiNascita;
            }
        },
        set: function (value) {
            this._dataDiNascita = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CitizenSearchComponent.prototype, "isCodiceFiscale", {
        get: function () {
            return this._isCodiceFiscale;
        },
        set: function (value) {
            this._isCodiceFiscale = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CitizenSearchComponent.prototype, "searchOfCitizens", {
        get: function () {
            return this._searchOfCitizens;
        },
        set: function (value) {
            this._searchOfCitizens = value;
        },
        enumerable: true,
        configurable: true
    });
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('datanasBen'),
        __metadata("design:type", Object)
    ], CitizenSearchComponent.prototype, "datanasBenField", void 0);
    CitizenSearchComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-citizen-search',
            template: __webpack_require__(/*! ./citizen-search.component.html */ "./src/app/content/citizen-search/citizen-search.component.html"),
            styles: [__webpack_require__(/*! ./citizen-search.component.css */ "./src/app/content/citizen-search/citizen-search.component.css")]
        }),
        __metadata("design:paramtypes", [_ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_1__["NgbModal"],
            _citizen_search_service__WEBPACK_IMPORTED_MODULE_6__["CitizenSearchService"],
            _date_service__WEBPACK_IMPORTED_MODULE_4__["DateService"],
            _state_service__WEBPACK_IMPORTED_MODULE_5__["StateService"]])
    ], CitizenSearchComponent);
    return CitizenSearchComponent;
}());



/***/ }),

/***/ "./src/app/content/citizen-search/citizen-search.service.ts":
/*!******************************************************************!*\
  !*** ./src/app/content/citizen-search/citizen-search.service.ts ***!
  \******************************************************************/
/*! exports provided: CitizenSearchService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CitizenSearchService", function() { return CitizenSearchService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _model_cittadino__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../model/cittadino */ "./src/app/model/cittadino.ts");
/* harmony import */ var _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../soap-request-factory.service */ "./src/app/soap-request-factory.service.ts");
/* harmony import */ var _soap_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../soap.service */ "./src/app/soap.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var CitizenSearchService = /** @class */ (function () {
    function CitizenSearchService(soapRequestFactoryService, soapService) {
        this.soapRequestFactoryService = soapRequestFactoryService;
        this.soapService = soapService;
    }
    CitizenSearchService.prototype.searchCitizen = function (searchOfCitizens) {
        var searchUserRequest = this.soapRequestFactoryService.getSearchUserRequest(searchOfCitizens);
        return this.soapService.sentSoapFindUsers(searchUserRequest);
    };
    CitizenSearchService.prototype.parseCittadinos = function (res) {
        var resultCitizenArray = [];
        var citizensArray = res.getElementsByTagName('citizens')[0].children;
        for (var i = 0; i < citizensArray.length; i++) {
            var xmlCitizen = citizensArray.item(i);
            var resultCitizen = new _model_cittadino__WEBPACK_IMPORTED_MODULE_1__["Cittadino"]();
            var xmlCitizenFields = xmlCitizen.children;
            for (var j = 0; j < xmlCitizenFields.length; j++) {
                var field = xmlCitizenFields.item(j);
                resultCitizen[field.nodeName] = field.innerHTML;
            }
            resultCitizenArray.push(resultCitizen);
        }
        return resultCitizenArray;
    };
    CitizenSearchService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_soap_request_factory_service__WEBPACK_IMPORTED_MODULE_2__["SoapRequestFactoryService"],
            _soap_service__WEBPACK_IMPORTED_MODULE_3__["SoapService"]])
    ], CitizenSearchService);
    return CitizenSearchService;
}());



/***/ }),

/***/ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.css":
/*!************************************************************************************************!*\
  !*** ./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.css ***!
  \************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.html":
/*!*************************************************************************************************!*\
  !*** ./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.html ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"modal beneficiario\" style=\"height: fit-content; display: block\">\n  <div class=\"modal-dialog modal-lg\" tabindex=\"-1\" role=\"document\">\n    <div class=\"modal-content rigabiancafontnero\">\n      <div class=\"modal-header bordo-top\" style=\"border: 0px\">\n        <h4 class=\"modal-title\">Risultato della ricerca</h4>\n        <button class=\"close\" id=\"chiudimodalricerca\" type=\"button\"\n                data-dismiss=\"modal\" aria-label=\"Close\" (click)=\"dismiss()\">\n          <span>&times;</span>\n        </button>\n      </div>\n\n      <div class=\"tabellaricerca\">\n        <div class=\"row\" style=\"width: 100%; padding: 0 15px;\">\n          <div class=\"bs-callout bs-callout-primary\"\n               style=\"width: 100%; padding: 15px; margin-top: 0; font-size: 12px\">\n            <strong>Sono stati trovati <label id=\"numtrovati\"></label>\n              assistiti con le caratteristiche impostate.\n            </strong><br> Seleziona l'utente che intendi inserire nella richiesta\n          </div>\n        </div>\n\n        <div class=\"table-responsive\" style=\"padding: 0 15px 15px;\">\n\n          <table id=\"ricerca\" class=\"table\"\n                 style=\"font-size: 12px; border: 0;\">\n            <thead class=\"rigagrigiafontnero\" style=\"font-weight: bold\">\n            <tr style=\"border: 0px\">\n              <th scope=\"col\" style=\"border: 0px\">Codice Fiscale</th>\n              <th scope=\"col\" style=\"border: 0px\">Cognome</th>\n              <th scope=\"col\" style=\"border: 0px\">Nome</th>\n              <th scope=\"col\" style=\"border: 0px\">Sesso</th>\n              <th scope=\"col\" style=\"border: 0px\">Data di nascita</th>\n              <th scope=\"col\" style=\"border: 0px\">Comune o Stato estero\n                di nascita</th>\n              <th scope=\"col\"></th>\n            </tr>\n            </thead>\n            <tbody>\n            <tr *ngFor=\"let citizen of foundCitizens; let i=index\">\n              <th class=\"celleTab\" scope=\"row\">{{citizen.codFiscale}}</th>\n              <td class=\"celleTab\">{{citizen.cognome}}</td>\n              <td class=\"celleTab\">{{citizen.nome}}</td>\n              <td class=\"celleTab\">{{citizen.codSesso}}</td>\n              <td class=\"celleTab\">{{citizen.dataNascita}}</td>\n              <td class=\"celleTab\">{{citizen.comuneNascita}}</td>\n              <td class=\"celleTab\">\n                <button type=\"button\" class=\"btn btn-outline-primary\"\n                        data-dismiss=\"modal\" (click)=\"chooseCitizen(citizen)\">Scegli</button>\n              </td>\n            </tr>\n            </tbody>\n          </table>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.ts":
/*!***********************************************************************************************!*\
  !*** ./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.ts ***!
  \***********************************************************************************************/
/*! exports provided: FoundCitizensModalComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FoundCitizensModalComponent", function() { return FoundCitizensModalComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/fesm5/ng-bootstrap.js");
/* harmony import */ var jquery__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! jquery */ "./node_modules/jquery/dist/jquery.js");
/* harmony import */ var jquery__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(jquery__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _state_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../../state.service */ "./src/app/state.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var FoundCitizensModalComponent = /** @class */ (function () {
    function FoundCitizensModalComponent(activeModal, router, stateService) {
        this.activeModal = activeModal;
        this.router = router;
        this.stateService = stateService;
        this.foundCitizens = [];
    }
    FoundCitizensModalComponent.prototype.ngOnInit = function () {
        this.foundCitizens = this.stateService.data.foundCitizens;
    };
    FoundCitizensModalComponent.prototype.ngOnDestroy = function () {
        jquery__WEBPACK_IMPORTED_MODULE_2__(".modal-backdrop").remove();
        jquery__WEBPACK_IMPORTED_MODULE_2__(".modal").remove();
        this.stateService.data.foundCitizens = [];
    };
    FoundCitizensModalComponent.prototype.chooseCitizen = function (citizen) {
        this.ngOnDestroy();
        this.router.navigate(['/cittadino', citizen.codFiscale, 'consensi']);
    };
    FoundCitizensModalComponent.prototype.dismiss = function () {
        this.activeModal.dismiss();
    };
    FoundCitizensModalComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-found-citizens-modal',
            template: __webpack_require__(/*! ./found-citizens-modal.component.html */ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.html"),
            styles: [__webpack_require__(/*! ./found-citizens-modal.component.css */ "./src/app/content/citizen-search/found-citizens-modal/found-citizens-modal.component.css")]
        }),
        __metadata("design:paramtypes", [_ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_1__["NgbActiveModal"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            _state_service__WEBPACK_IMPORTED_MODULE_4__["StateService"]])
    ], FoundCitizensModalComponent);
    return FoundCitizensModalComponent;
}());



/***/ }),

/***/ "./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.css":
/*!********************************************************************************************!*\
  !*** ./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.css ***!
  \********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.html":
/*!*********************************************************************************************!*\
  !*** ./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.html ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  nobody-found-error works!\n</p>\n"

/***/ }),

/***/ "./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.ts":
/*!*******************************************************************************************!*\
  !*** ./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.ts ***!
  \*******************************************************************************************/
/*! exports provided: NobodyFoundErrorComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NobodyFoundErrorComponent", function() { return NobodyFoundErrorComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var NobodyFoundErrorComponent = /** @class */ (function () {
    function NobodyFoundErrorComponent() {
    }
    NobodyFoundErrorComponent.prototype.ngOnInit = function () {
    };
    NobodyFoundErrorComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-nobody-found-error',
            template: __webpack_require__(/*! ./nobody-found-error.component.html */ "./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.html"),
            styles: [__webpack_require__(/*! ./nobody-found-error.component.css */ "./src/app/content/citizen-search/nobody-found-error/nobody-found-error.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], NobodyFoundErrorComponent);
    return NobodyFoundErrorComponent;
}());



/***/ }),

/***/ "./src/app/content/consensi-list/consensi-list.component.css":
/*!*******************************************************************!*\
  !*** ./src/app/content/consensi-list/consensi-list.component.css ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".informativa {\r\n  margin-top: 10px; margin-right: 20px\r\n}\r\n\r\n.scaduto-informativa {\r\n  border-left: 10px solid #e93131\r\n}\r\n\r\n.attivo-informativa {\r\n  border-left: 10px solid green\r\n}\r\n\r\n.not-attivo-informativa {\r\n  border-left: 10px solid #666666\r\n}\r\n\r\n"

/***/ }),

/***/ "./src/app/content/consensi-list/consensi-list.component.html":
/*!********************************************************************!*\
  !*** ./src/app/content/consensi-list/consensi-list.component.html ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"!citizen || !informativas\" class=\"row col-12 justify-content-center mt-5 mb-5\">\n  <p-progressSpinner [style]=\"{width: '50px', height: '50px'}\"></p-progressSpinner>\n</div>\n<div class=\"row\" style=\"margin-top: 35px\" *ngIf=\"false\">\n  <p class=\"row col-12 pl-0\">\n    <a (click)=\"isFilterOpened = !isFilterOpened\" class=\"filter-label\">\n      Filtra per\n      <fa-icon style=\"font-size: 18px\" *ngIf=\"isFilterOpened\" [icon]=\"chevronUp\"></fa-icon>\n      <fa-icon style=\"font-size: 18px\" *ngIf=\"!isFilterOpened\" [icon]=\"chevronDown\"></fa-icon>\n    </a>\n  </p>\n  <div [ngClass]=\"{'show': isFilterOpened}\" class=\"collapse\" style=\"margin-bottom: 10px\">\n    <div class=\"row col-12 pl-0\">\n      <div class=\"filter-item col\" (click)=\"dataFilterAsc = !dataFilterAsc\">\n        <span>DATA</span>\n        <span class=\"filter-item-arrows\">\n           <fa-icon *ngIf=\"dataFilterAsc\" [icon]=\"chevronUp\"></fa-icon>\n           <fa-icon *ngIf=\"!dataFilterAsc\" [icon]=\"chevronDown\"></fa-icon>\n        </span>\n      </div>\n      <div class=\"filter-item col\" (click)=\"statoFilterAsc = !statoFilterAsc\">\n        <span>STATO</span>\n        <span class=\"filter-item-arrows\">\n          <fa-icon *ngIf=\"statoFilterAsc\" [icon]=\"chevronUp\"></fa-icon>\n           <fa-icon *ngIf=\"!statoFilterAsc\" [icon]=\"chevronDown\"></fa-icon>\n        </span>\n      </div>\n      <div class=\"filter-item col\" (click)=\"delegatoFilterAsc = !delegatoFilterAsc\">\n        <span>DELEGATO</span>\n        <span class=\"filter-item-arrows\">\n          <fa-icon *ngIf=\"delegatoFilterAsc\" [icon]=\"chevronUp\"></fa-icon>\n           <fa-icon *ngIf=\"!delegatoFilterAsc\" [icon]=\"chevronDown\"></fa-icon>\n        </span>\n      </div>\n      <div class=\"filter-item col\" (click)=\"aslFilterAsc = !aslFilterAsc\">\n        <span>ASL</span>\n        <span class=\"filter-item-arrows\">\n          <fa-icon *ngIf=\"aslFilterAsc\" [icon]=\"chevronUp\"></fa-icon>\n           <fa-icon *ngIf=\"!aslFilterAsc\" [icon]=\"chevronDown\"></fa-icon>\n        </span>\n      </div>\n      <div class=\"filter-item col\" (click)=\"validoPerFilterAsc = !validoPerFilterAsc\">\n        <span>VALIDO PER</span>\n        <span class=\"filter-item-arrows\">\n           <fa-icon *ngIf=\"validoPerFilterAsc\" [icon]=\"chevronUp\"></fa-icon>\n           <fa-icon *ngIf=\"!validoPerFilterAsc\" [icon]=\"chevronDown\"></fa-icon>\n        </span>\n      </div>\n    </div>\n  </div>\n</div>\n<div style=\"margin-top: 35px\"></div>\n<ng-container style=\"margin-top: 35px\" *ngIf=\"citizen && informativas\">\n  <div style=\"width: 100%; padding: 5px; border: 1px #dbdbdb solid; background-color: white; margin-bottom: 20px\" *ngFor=\"let informativa of informativas\">\n    <div class=\"row informativa\"\n         [ngClass]=\"{'attivo-informativa': isActive(informativa),\n          'scaduto-informativa': isExpired(informativa) || isRevoked(informativa),\n          'not-attivo-informativa': isNotActive(informativa)}\">\n      <div style=\"width: calc(100% - 70px); margin-left: 10px;\">\n        <strong>{{informativa.sottoTipoConsenso.descrizione}}</strong>\n        <p>\n          {{informativa.descInformativa}}\n        </p>\n      </div>\n      <div *ngIf=\"isActive(informativa)\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n        <fa-icon style=\"color: #008000!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"activeIcon\"></fa-icon>\n        <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">attivo</span>\n      </div>\n      <div *ngIf=\"isExpired(informativa)\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n        <fa-icon style=\"color: #e93131!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"expiredIcon\"></fa-icon>\n        <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">scaduto</span>\n      </div>\n      <div *ngIf=\"isRevoked(informativa)\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n        <fa-icon style=\"color: #e93131!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"revokedIcon\"></fa-icon>\n        <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">revocato</span>\n      </div>\n      <div *ngIf=\"isNotActive(informativa)\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n        <fa-icon style=\"color: #666666!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"notActiveIcon\"></fa-icon>\n        <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">non attivo</span>\n      </div>\n    </div>\n    <div style=\"display: flex; min-height: 55px\">\n      <div *ngIf=\"isActive(informativa)\" style=\"margin-left: 20px; margin-top: 15px\">\n        <img id=\"u34_img\" class=\"img \" src=\"assets/img/user/u34.png\">\n        <span style=\"color: green\"><strong>Consenso rilasciato dal delegante: {{citizen.nome+\" \"+citizen.cognome}}</strong></span>\n      </div>\n      <div style=\"position: relative; margin-left: auto; margin-right: 20px;\">\n    <span style=\"font-size: 13px; color: #666666; position:relative; top: 22px\">\n      <span *ngIf=\"informativa.dataDecorrenza\">Data rilascio: {{informativa.dataDecorrenza | date:'dd.MM.yyyy'}} </span>\n      <span *ngIf=\"informativa.dataScadenza\">Data scadenza: {{informativa.dataScadenza | date:'dd.MM.yyyy'}} </span>\n    </span>\n      </div>\n    </div>\n    <div style=\"display: flex; margin-top: 20px; margin-left: 20px; justify-content: space-between;\">\n      <div style=\"display:flex;\">\n      <span style=\"font-size: 13px; text-align: end;\">\n        Scarica e stampa<br>\n        consenso:\n       </span>\n        <a href=\"{{informativa.pdfInformativa}}\">\n          <img style=\"width: 40px; margin-left: 10px;\" id=\"u28_img\" class=\"img \" src=\"assets/img/user/u28.png\">\n        </a>\n      </div>\n      <button (click)=\"openDetailPage(informativa.idInformativa)\" class=\"btn btn-primary btn-block\" style=\"width: 180px; margin-right: 20px; height: 35px; position: relative; top: 10px\">\n      <span style=\"position: relative; bottom: 2px\">\n        Gestisci\n      </span>\n      </button>\n    </div>\n  </div>\n</ng-container>\n"

/***/ }),

/***/ "./src/app/content/consensi-list/consensi-list.component.ts":
/*!******************************************************************!*\
  !*** ./src/app/content/consensi-list/consensi-list.component.ts ***!
  \******************************************************************/
/*! exports provided: ConsensiListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConsensiListComponent", function() { return ConsensiListComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @fortawesome/free-solid-svg-icons */ "./node_modules/@fortawesome/free-solid-svg-icons/index.es.js");
/* harmony import */ var _fortawesome_free_regular_svg_icons__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @fortawesome/free-regular-svg-icons */ "./node_modules/@fortawesome/free-regular-svg-icons/index.es.js");
/* harmony import */ var _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../soap-request-factory.service */ "./src/app/soap-request-factory.service.ts");
/* harmony import */ var _soap_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../soap.service */ "./src/app/soap.service.ts");
/* harmony import */ var _citizen_search_citizen_search_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../citizen-search/citizen-search.service */ "./src/app/content/citizen-search/citizen-search.service.ts");
/* harmony import */ var _model_request_search_of_citizens__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../model/request/search-of-citizens */ "./src/app/model/request/search-of-citizens.ts");
/* harmony import */ var _model_informativa__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../../model/informativa */ "./src/app/model/informativa.ts");
/* harmony import */ var _informativa_service__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../../informativa.service */ "./src/app/informativa.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










var ConsensiListComponent = /** @class */ (function () {
    function ConsensiListComponent(activatedRoute, router, soapRequestFactoryService, citizenSearchService, soapService, informativaService) {
        this.activatedRoute = activatedRoute;
        this.router = router;
        this.soapRequestFactoryService = soapRequestFactoryService;
        this.citizenSearchService = citizenSearchService;
        this.soapService = soapService;
        this.informativaService = informativaService;
        this.chevronDown = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faChevronDown"];
        this.chevronUp = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faChevronUp"];
        this.notActiveIcon = _fortawesome_free_regular_svg_icons__WEBPACK_IMPORTED_MODULE_3__["faCircle"];
        this.revokedIcon = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faTimesCircle"];
        this.activeIcon = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faCheckCircle"];
        this.expiredIcon = _fortawesome_free_regular_svg_icons__WEBPACK_IMPORTED_MODULE_3__["faClock"];
        this.fiscalCode = '';
        this.informativas = [];
        this.isFilterOpened = false;
        this.dataFilterAsc = true;
        this.statoFilterAsc = true;
        this.delegatoFilterAsc = true;
        this.aslFilterAsc = true;
        this.validoPerFilterAsc = true;
        this.finishedLoadingOfInformativas = false;
        this.finishedLoadingOfCitizen = false;
        this.activeRouteSnapshot = this.activatedRoute.snapshot;
    }
    ConsensiListComponent.prototype.isActive = function (informativa) {
        return this.informativaService.getStatus(informativa) === _model_informativa__WEBPACK_IMPORTED_MODULE_8__["InformativaStatus"].ACTIVE;
    };
    ConsensiListComponent.prototype.isRevoked = function (informativa) {
        return this.informativaService.getStatus(informativa) === _model_informativa__WEBPACK_IMPORTED_MODULE_8__["InformativaStatus"].REVOKED;
    };
    ConsensiListComponent.prototype.isExpired = function (informativa) {
        return this.informativaService.getStatus(informativa) === _model_informativa__WEBPACK_IMPORTED_MODULE_8__["InformativaStatus"].EXPIRED;
    };
    ConsensiListComponent.prototype.isNotActive = function (informativa) {
        return this.informativaService.getStatus(informativa) === _model_informativa__WEBPACK_IMPORTED_MODULE_8__["InformativaStatus"].NOT_ACTIVE;
    };
    ConsensiListComponent.prototype.ngOnInit = function () {
        this.fiscalCode = this.activeRouteSnapshot.params['fiscalCode'];
        this.loadCitizen();
        this.loadInformativasByFiscalCode();
    };
    ConsensiListComponent.prototype.loadCitizen = function () {
        var _this = this;
        var searchOfCitizens = new _model_request_search_of_citizens__WEBPACK_IMPORTED_MODULE_7__["SearchOfCitizens"]();
        searchOfCitizens.codiceFiscale = this.fiscalCode;
        this.citizenSearchService.searchCitizen(searchOfCitizens)
            .done(function (res) {
            var foundCitizens = _this.citizenSearchService.parseCittadinos(res);
            _this.citizen = foundCitizens.length > 0 ? foundCitizens[0] : undefined;
            _this.finishedLoadingOfCitizen = true;
        })
            .fail(function (err) {

            _this.finishedLoadingOfCitizen = true;
        });
    };
    ConsensiListComponent.prototype.loadConsensusesByFiscalCodeAndInformativa = function (informativaId) {
        var consensusesRequest = this.soapRequestFactoryService.getConsensusesRequest(this.fiscalCode, informativaId);
        return this.soapService.sentSoap(consensusesRequest);
    };
    ConsensiListComponent.prototype.parseAndAddInformativa = function (xml) {
        var _this = this;
        var informativa = this.soapService.parseXmlNode(xml);
        this.loadConsensusesByFiscalCodeAndInformativa(informativa.idInformativa)
            .done(function (res) {
            informativa.consensuses = _this.soapService.parseConsensuses(res);
        })
            .fail(function (err) {

            setTimeout(function () {
                _this.parseAndAddInformativa(xml);
            }, 500);
        });
        this.informativas.push(informativa);
    };
    ConsensiListComponent.prototype.openDetailPage = function (idInformativa) {
        this.router.navigate(["./dettaglio", idInformativa], { relativeTo: this.activatedRoute });
    };
    ConsensiListComponent.prototype.loadInformativasByFiscalCode = function () {
        var _this = this;
        this.finishedLoadingOfInformativas = false;
        var informativaRequest = this.soapRequestFactoryService.getInformativaRequest(this.fiscalCode);
        this.soapService.sentSoap(informativaRequest)
            .done(function (res) {
            var informativas = res.getElementsByTagName('informativa');
            for (var i = 0; i < informativas.length; i++) {
                _this.parseAndAddInformativa(informativas[i]);
            }
            // this.informativas[1].dataScadenza = this.informativas[1].dataDecorrenza

            _this.finishedLoadingOfInformativas = true;
        })
            .fail(function (err) {

            _this.finishedLoadingOfInformativas = true;
        });
    };
    ConsensiListComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-consensi-list',
            template: __webpack_require__(/*! ./consensi-list.component.html */ "./src/app/content/consensi-list/consensi-list.component.html"),
            styles: [__webpack_require__(/*! ./consensi-list.component.css */ "./src/app/content/consensi-list/consensi-list.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_4__["SoapRequestFactoryService"],
            _citizen_search_citizen_search_service__WEBPACK_IMPORTED_MODULE_6__["CitizenSearchService"],
            _soap_service__WEBPACK_IMPORTED_MODULE_5__["SoapService"],
            _informativa_service__WEBPACK_IMPORTED_MODULE_9__["InformativaService"]])
    ], ConsensiListComponent);
    return ConsensiListComponent;
}());



/***/ }),

/***/ "./src/app/content/consesnsi-detail/consesnsi-detail.component.css":
/*!*************************************************************************!*\
  !*** ./src/app/content/consesnsi-detail/consesnsi-detail.component.css ***!
  \*************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".informativa {\r\n  margin-top: 10px; margin-right: 20px\r\n}\r\n\r\n.scaduto-informativa {\r\n  border-left: 10px solid #e93131\r\n}\r\n\r\n.attivo-informativa {\r\n  border-left: 10px solid green\r\n}\r\n\r\n"

/***/ }),

/***/ "./src/app/content/consesnsi-detail/consesnsi-detail.component.html":
/*!**************************************************************************!*\
  !*** ./src/app/content/consesnsi-detail/consesnsi-detail.component.html ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h3 style=\"margin-left: 15px; margin-top: 20px; margin-bottom: 30px\">\n  <fa-icon (click)=\"back()\" style=\"cursor: pointer; font-size: 20px; position: relative; bottom: 2px;\" [icon]=\"arrowLeft\"></fa-icon>\n  <span style=\"margin-left: 5px\">Dettaglio Consenso</span>\n</h3>\n\n<div *ngIf=\"!citizen || !informativa\" class=\"row col-12 justify-content-center mt-5 mb-5\">\n  <p-progressSpinner [style]=\"{width: '50px', height: '50px'}\"></p-progressSpinner>\n</div>\n\n<div *ngIf=\"informativa && citizen\" style=\"width: 100%; padding: 5px; border: 1px #dbdbdb solid; background-color: white; margin-bottom: 20px\">\n  <div class=\"row informativa\" [ngClass]=\"{'attivo-informativa': isActive(), 'scaduto-informativa': isExpired() || isRevoked()}\">\n    <div style=\"width: calc(100% - 70px); margin-left: 10px;\">\n      <strong>{{informativa.sottoTipoConsenso.descrizione}}</strong>\n      <p>\n        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bidendum laoreet. Proin gravida dolor sit amet lacus accumsan.\n      </p>\n    </div>\n    <div *ngIf=\"isActive()\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n      <fa-icon style=\"color: #008000!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"activeIcon\"></fa-icon>\n      <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">attivo</span>\n    </div>\n    <div *ngIf=\"isExpired()\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n      <fa-icon style=\"color: #e93131!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"expiredIcon\"></fa-icon>\n      <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">scaduto</span>\n    </div>\n    <div *ngIf=\"isRevoked()\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n      <fa-icon style=\"color: #e93131!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"revokedIcon\"></fa-icon>\n      <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">revocato</span>\n    </div>\n    <div *ngIf=\"isNotActive()\" style=\"display: flex;flex-direction: column;align-items: center;position: relative;margin-left: auto;\">\n      <fa-icon style=\"color: #999999!important; font-size: 30px; font-family: 'Arial Normale', 'Arial'; font-weight: 400;\" [icon]=\"notActiveIcon\"></fa-icon>\n      <span style=\"font-size: 13px; color: #666666; text-align: center; position: relative; bottom: 10px;\">non attivo</span>\n    </div>\n  </div>\n  <div style=\"display: flex; min-height: 55px\">\n    <div *ngIf=\"isActive()\" style=\"margin-left: 20px; margin-top: 15px\">\n      <img id=\"u34_img\" class=\"img \" src=\"assets/img/user/u34.png\">\n      <span style=\"color: green\"><strong>Consenso rilasciato dal delegante: {{citizen.nome+\" \"+citizen.cognome}}</strong></span>\n    </div>\n    <div style=\"position: relative; margin-left: auto; margin-right: 20px;\">\n   <span style=\"font-size: 13px; color: #666666; position:relative; top: 22px\">\n      <span *ngIf=\"informativa.dataDecorrenza\">Data rilascio: {{informativa.dataDecorrenza | date:'dd.MM.yyyy'}} </span>\n      <span *ngIf=\"informativa.dataScadenza\">Data scadenza: {{informativa.dataScadenza | date:'dd.MM.yyyy'}} </span>\n    </span>\n    </div>\n  </div>\n  <hr>\n  <div style=\"margin-left: 20px\">\n    <strong>Consenso numero uno</strong><br>\n    <span\n      style=\"font-size: 12px;\">Scegli per quali ASL vale il tuo consenso (almeno una ASL deve essere selezionata)</span>\n    <div style=\"display: flex\">\n      <label style=\"font-size: 13px; width: 180px\">\n        <input type=\"checkbox\" [(ngModel)]=\"allTipoStatoAreActive\" (change)=\"changeAllTipoStato($event)\">\n        <span style=\"position:relative; bottom: 2px\">TUTTE</span>\n      </label>\n\n      <div class=\"row\" style=\"font-size: 13px; width: 100%;\" *ngIf=\"informativa.consensuses\">\n        <label *ngFor=\"let consensus of informativa.consensuses; let i = index\"\n               [ngClass]=\"{'col-6 col-sm-4 col-md-3': informativa.consensuses.length > 3,\n               'mr-5':informativa.consensuses.length <= 3}\">\n          <input type=\"checkbox\" [checked]=\"consensus.tipoStato.codice === 'A'\"\n                 (change)=\"changeTipoStato($event, i)\">\n          <span style=\"position:relative; bottom: 2px\">\n            {{consensus.tipoAsr.codice+\" \"+consensus.tipoAsr.descrizione}}\n          </span>\n        </label>\n      </div>\n    </div>\n    <div style=\"font-size: 13px; margin-top: 10px;\" class=\"row\"></div>\n    <div style=\"font-size: 13px; margin-top: 10px;\" class=\"row\"></div>\n    <div style=\"font-size: 13px; margin-top: 10px;\" class=\"row\">\n    </div>\n  </div>\n  <hr>\n  <div style=\"margin-left: 20px\">\n    <div style=\"margin-bottom: 40px\">\n      <strong *ngIf=\"isActive()\">Testo integrale consenso numero due - Stato: <span style=\"color: green\">ATTIVO <fa-icon\n        [icon]=\"activeIcon\" style=\"margin-left: 10px; font-size: 20px\"></fa-icon> </span>\n      </strong>\n      <strong *ngIf=\"isExpired()\">Testo integrale consenso numero due - Stato: <span style=\"color: #e93131\">SCADUTO <fa-icon\n        [icon]=\"expiredIcon\" style=\"margin-left: 10px; font-size: 20px\"></fa-icon> </span>\n      </strong>\n      <strong *ngIf=\"isRevoked()\">Testo integrale consenso numero due - Stato: <span style=\"color: #e93131\">REVOCATO <fa-icon\n        [icon]=\"revokedIcon\" style=\"margin-left: 10px; font-size: 20px\"></fa-icon> </span>\n      </strong>\n      <strong *ngIf=\"isNotActive()\">Testo integrale consenso numero due - Stato: <span style=\"color:#999999;\">NON ATTIVO <fa-icon\n        [icon]=\"notActiveIcon\" style=\"margin-left: 10px; font-size: 20px\"></fa-icon> </span>\n      </strong>\n      <br><br>\n      <div [innerHTML]=\"htmlTemplate\"></div>\n    </div>\n    <input type=\"checkbox\" id=\"id-name--1\" name=\"set-name\"\n           class=\"switch-input\" [(ngModel)]=\"agreed\">\n    <label style=\"padding-left: 60px; background-color: #F2F2F2\"\n           for=\"id-name--1\" class=\"switch-label\">\n      <span style=\"margin-right: 30px\" class=\"toggle--on\">Dichiaro di avere letto ed accettato l'informativa</span>\n      <span style=\"margin-right: 30px\" class=\"toggle--off\">Dichiaro di avere letto ed accettato l'informativa</span>\n    </label>\n    <p id=\"testoBlocco\"></p>\n\n    <div style=\"width: 100%; display: flex;justify-content: space-between; padding-right: 40px; margin-bottom: 50px;\">\n      <button (click)=\"back()\" style=\"width: 200px; height: 45px\" class=\"btn btn-outline-primary\">indietro</button>\n      <div style=\"display: flex\">\n        <p-progressSpinner *ngIf=\"isSaving\" [style]=\"{width: '40px', height: '40px'}\"></p-progressSpinner>\n        <button [disabled]=\"!agreed || isSaving\" (click)=\"save()\" style=\"width: 200px; height: 45px; margin-left: 15px\" class=\"btn btn-primary\">SALVA</button>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/content/consesnsi-detail/consesnsi-detail.component.ts":
/*!************************************************************************!*\
  !*** ./src/app/content/consesnsi-detail/consesnsi-detail.component.ts ***!
  \************************************************************************/
/*! exports provided: ConsesnsiDetailComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConsesnsiDetailComponent", function() { return ConsesnsiDetailComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @fortawesome/free-solid-svg-icons */ "./node_modules/@fortawesome/free-solid-svg-icons/index.es.js");
/* harmony import */ var _fortawesome_free_regular_svg_icons__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @fortawesome/free-regular-svg-icons */ "./node_modules/@fortawesome/free-regular-svg-icons/index.es.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _model_informativa__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../model/informativa */ "./src/app/model/informativa.ts");
/* harmony import */ var _model_request_search_of_citizens__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../model/request/search-of-citizens */ "./src/app/model/request/search-of-citizens.ts");
/* harmony import */ var _citizen_search_citizen_search_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../citizen-search/citizen-search.service */ "./src/app/content/citizen-search/citizen-search.service.ts");
/* harmony import */ var _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../soap-request-factory.service */ "./src/app/soap-request-factory.service.ts");
/* harmony import */ var _soap_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../../soap.service */ "./src/app/soap.service.ts");
/* harmony import */ var _state_service__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../../state.service */ "./src/app/state.service.ts");
/* harmony import */ var _informativa_service__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../../informativa.service */ "./src/app/informativa.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};











var ConsesnsiDetailComponent = /** @class */ (function () {
    function ConsesnsiDetailComponent(router, activatedRoute, citizenSearchService, soapRequestFactoryService, stateService, soapService, informativaService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.citizenSearchService = citizenSearchService;
        this.soapRequestFactoryService = soapRequestFactoryService;
        this.stateService = stateService;
        this.soapService = soapService;
        this.informativaService = informativaService;
        this.arrowLeft = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_1__["faArrowLeft"];
        this.notActiveIcon = _fortawesome_free_regular_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faCircle"];
        this.revokedIcon = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_1__["faTimesCircle"];
        this.activeIcon = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_1__["faCheckCircle"];
        this.expiredIcon = _fortawesome_free_regular_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faClock"];
        this.htmlTemplate = '';
        this.processedRequests = 0;
        this.successRequests = 0;
        this.isSaving = false;
        this.activeRouteSnapshot = this.activatedRoute.snapshot;
    }
    ConsesnsiDetailComponent.prototype.isActive = function () {
        return this.statusOfInformativa === _model_informativa__WEBPACK_IMPORTED_MODULE_4__["InformativaStatus"].ACTIVE;
    };
    ConsesnsiDetailComponent.prototype.isRevoked = function () {
        return this.statusOfInformativa === _model_informativa__WEBPACK_IMPORTED_MODULE_4__["InformativaStatus"].REVOKED;
    };
    ConsesnsiDetailComponent.prototype.isExpired = function () {
        return this.statusOfInformativa === _model_informativa__WEBPACK_IMPORTED_MODULE_4__["InformativaStatus"].EXPIRED;
    };
    ConsesnsiDetailComponent.prototype.isNotActive = function () {
        return this.statusOfInformativa === _model_informativa__WEBPACK_IMPORTED_MODULE_4__["InformativaStatus"].NOT_ACTIVE;
    };
    ConsesnsiDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.fiscalCode = this.activeRouteSnapshot.params['fiscalCode'];
        this.idInformativa = this.activeRouteSnapshot.params['idInformativa'];

        this.loadCitizen();
        var interval = setInterval(function () {
            if (_this.citizen) {
                _this.loadInformativa();
                clearInterval(interval);
            }
        }, 250);
    };
    ConsesnsiDetailComponent.prototype.loadCitizen = function () {
        var _this = this;
        var searchOfCitizens = new _model_request_search_of_citizens__WEBPACK_IMPORTED_MODULE_5__["SearchOfCitizens"]();
        searchOfCitizens.codiceFiscale = this.fiscalCode;
        this.citizenSearchService.searchCitizen(searchOfCitizens)
            .done(function (res) {
            var foundCitizens = _this.citizenSearchService.parseCittadinos(res);
            _this.citizen = foundCitizens.length > 0 ? foundCitizens[0] : undefined;

        })
            .fail(function (err) {

            setTimeout(function () {
                _this.loadCitizen();
            }, 500);
        });
    };
    ConsesnsiDetailComponent.prototype.updateAllTipoStatoCheckbox = function () {
        var result = true;
        this.informativa.consensuses.forEach(function (consensus) {
            if (consensus.tipoStato.codice === 'R') {
                result = false;
            }
        });
        this.allTipoStatoAreActive = result;
    };
    ConsesnsiDetailComponent.prototype.changeAllTipoStato = function ($event) {
        this.informativa.consensuses.forEach(function (consensus) {
            var checked = $event.target.checked;
            consensus.tipoStato.codice = checked ? 'A' : 'R';
            consensus.valoreConsenso.codice = checked ? 'SI' : 'NO';
        });
    };
    ConsesnsiDetailComponent.prototype.changeTipoStato = function ($event, consensusIndex) {
        var checked = $event.target.checked;
        this.informativa.consensuses[consensusIndex].valoreConsenso.codice = checked ? 'SI' : 'NO';
        this.informativa.consensuses[consensusIndex].tipoStato.codice = checked ? 'A' : 'R';
        this.updateAllTipoStatoCheckbox();

    };
    ConsesnsiDetailComponent.prototype.loadInformativa = function () {
        var _this = this;
        var informativaRequest = this.soapRequestFactoryService.getOneInformativaRequest(this.fiscalCode, this.idInformativa);
        this.soapService.sentSoap(informativaRequest)
            .done(function (res) {
            var informativa = res.getElementsByTagName('informativa')[0];
            _this.parseAndAddInformativa(informativa);

        })
            .fail(function (err) {

        });
    };
    ConsesnsiDetailComponent.prototype.loadConsensusesByFiscalCodeAndInformativa = function (informativaId) {
        var consensusesRequest = this.soapRequestFactoryService.getConsensusesRequest(this.fiscalCode, informativaId);
        return this.soapService.sentSoap(consensusesRequest);
    };
    ConsesnsiDetailComponent.prototype.parseAndAddInformativa = function (xml) {
        var _this = this;
        var informativa = this.soapService.parseXmlNode(xml);

        if (informativa.idInformativa !== this.idInformativa)
            return;
        this.informativa = informativa;
        fetch(this.informativa.htmlInformativa, {
            headers: {
                'Access-Control-Allow-Origin': '*'
            },
            method: 'GET'
        }).then(function (res) { return res.text(); })
            .then(function (res) {
            _this.htmlTemplate = res;
        });
        this.loadConsensusesByFiscalCodeAndInformativa(this.informativa.idInformativa)
            .done(function (res) {
            _this.informativa.consensuses = _this.soapService.parseConsensuses(res).sort(function (a, b) {
                return a.tipoAsr.codice > b.tipoAsr.codice ? 1 : -1;
            });
            _this.updateAllTipoStatoCheckbox();
            _this.statusOfInformativa = _this.informativaService.getStatus(_this.informativa);
        })
            .fail(function (err) {

        });
    };
    ConsesnsiDetailComponent.prototype.back = function () {
        window.history.back();
    };
    ConsesnsiDetailComponent.prototype.save = function () {
        var _this = this;
        this.isSaving = true;
        var totalConsensuses = this.informativa.consensuses.length;
        this.informativa.consensuses.forEach(function (consensus) {
            var modifyConsensoRequest = _this.soapRequestFactoryService.getModifyConsensoRequest(consensus);
            _this.soapService.sentSoap(modifyConsensoRequest)
                .done(function (res) {

                _this.successRequests++;
                _this.processedRequests++;
                if (_this.successRequests === totalConsensuses) {
                    _this.successRequests = 0;
                    _this.processedRequests = 0;
                    _this.stateService.data.feedbackInformativa = _this.informativa;
                    _this.isSaving = false;
                    _this.router.navigate(['cittadino', _this.fiscalCode, 'consensi', 'feedback']);
                }
                else if (_this.successRequests + _this.processedRequests === totalConsensuses) {
                    // todo show error
                    _this.isSaving = false;

                }
            })
                .fail(function (err) {

                _this.processedRequests++;
                if (_this.successRequests + _this.processedRequests === totalConsensuses) {
                    // todo show error
                    _this.isSaving = false;

                }
            });
        });
    };
    ConsesnsiDetailComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-consesnsi-detail',
            template: __webpack_require__(/*! ./consesnsi-detail.component.html */ "./src/app/content/consesnsi-detail/consesnsi-detail.component.html"),
            styles: [__webpack_require__(/*! ./consesnsi-detail.component.css */ "./src/app/content/consesnsi-detail/consesnsi-detail.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"],
            _citizen_search_citizen_search_service__WEBPACK_IMPORTED_MODULE_6__["CitizenSearchService"],
            _soap_request_factory_service__WEBPACK_IMPORTED_MODULE_7__["SoapRequestFactoryService"],
            _state_service__WEBPACK_IMPORTED_MODULE_9__["StateService"],
            _soap_service__WEBPACK_IMPORTED_MODULE_8__["SoapService"],
            _informativa_service__WEBPACK_IMPORTED_MODULE_10__["InformativaService"]])
    ], ConsesnsiDetailComponent);
    return ConsesnsiDetailComponent;
}());



/***/ }),

/***/ "./src/app/content/feedback/feedback.component.css":
/*!*********************************************************!*\
  !*** ./src/app/content/feedback/feedback.component.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/content/feedback/feedback.component.html":
/*!**********************************************************!*\
  !*** ./src/app/content/feedback/feedback.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div style=\"width: 100%; display: flex; justify-content: center;\">\n  <div style=\"max-width: 900px; width: 90%; display: flex; flex-direction: column\">\n    <h3 style=\"margin-left: 15px; margin-top: 20px; margin-bottom: 30px\">\n      <fa-icon (click)=\"back()\" style=\"cursor: pointer; font-size: 20px; position: relative; bottom: 2px;\"\n               [icon]=\"arrowLeft\"></fa-icon>\n      <span style=\"margin-left: 5px\">Dettaglio Consenso</span>\n    </h3>\n\n    <div style=\"display: flex; color: white; width: 100%; margin-bottom: 35px\" *ngIf=\"feedbackInformativa\">\n      <div style=\"min-height: 200px; background-color: rgba(30, 167, 62, 1); padding: 40px 20px 20px;\">\n        <fa-icon style=\"font-size: 60px\" [icon]=\"checkCircle\"></fa-icon>\n      </div>\n      <div style=\"min-height: 200px; font-size: 20px; font-weight: bold; background-color: rgba(33, 186, 69, 1); padding: 20px; width: 100%\">\n        <p>\n          Il consenso nominato: {{feedbackInformativa.sottoTipoConsenso.descrizione}} è stato ATTIVATO correttamente.\n\n          Azione effettuata in data: 11 dic 2019 per Consenso Permanente ROL\n        </p>\n      </div>\n    </div>\n    <button style=\"margin-bottom: 100px; max-width: 260px\" class=\"btn btn-outline-primary\" (click)=\"back()\">\n      Torna alla home\n    </button>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/content/feedback/feedback.component.ts":
/*!********************************************************!*\
  !*** ./src/app/content/feedback/feedback.component.ts ***!
  \********************************************************/
/*! exports provided: FeedbackComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FeedbackComponent", function() { return FeedbackComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @fortawesome/free-solid-svg-icons */ "./node_modules/@fortawesome/free-solid-svg-icons/index.es.js");
/* harmony import */ var _state_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../state.service */ "./src/app/state.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var FeedbackComponent = /** @class */ (function () {
    function FeedbackComponent(router, activatedRoute, stateService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.stateService = stateService;
        this.arrowLeft = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faArrowLeft"];
        this.checkCircle = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_2__["faCheckCircle"];
        this.type = '';
        this.activeRouteSnapshot = this.activatedRoute.snapshot;
    }
    FeedbackComponent.prototype.ngOnInit = function () {
        this.fiscalCode = this.activeRouteSnapshot.params['fiscalCode'];
        this.feedbackInformativa = this.stateService.data.feedbackInformativa;
        if (!this.feedbackInformativa) {
            this.back();
        }
    };
    FeedbackComponent.prototype.back = function () {
        this.router.navigate(['cittadino', this.fiscalCode, 'consensi']);
    };
    FeedbackComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-feedback',
            template: __webpack_require__(/*! ./feedback.component.html */ "./src/app/content/feedback/feedback.component.html"),
            styles: [__webpack_require__(/*! ./feedback.component.css */ "./src/app/content/feedback/feedback.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["ActivatedRoute"],
            _state_service__WEBPACK_IMPORTED_MODULE_3__["StateService"]])
    ], FeedbackComponent);
    return FeedbackComponent;
}());



/***/ }),

/***/ "./src/app/date.service.ts":
/*!*********************************!*\
  !*** ./src/app/date.service.ts ***!
  \*********************************/
/*! exports provided: DateService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DateService", function() { return DateService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var DateService = /** @class */ (function () {
    function DateService() {
    }
    // convert dd-mm-yyyy to the yyyy/mm/dd
    DateService.prototype.getNormalizedForServerData = function (date) {
        var strings = date.split("-");
        return strings[2] + "/" + strings[1] + "/" + strings[0];
    };
    // convert yyyy/mm/dd to the dd-mm-yyyy
    DateService.prototype.getFromNormalized = function (date) {
        var strings = date.split("/");
        return strings[2] + "-" + strings[1] + "-" + strings[0];
    };
    DateService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [])
    ], DateService);
    return DateService;
}());



/***/ }),

/***/ "./src/app/footbar/footbar.component.css":
/*!***********************************************!*\
  !*** ./src/app/footbar/footbar.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/footbar/footbar.component.html":
/*!************************************************!*\
  !*** ./src/app/footbar/footbar.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"row assistenza\"\n     style=\"color: white; padding-top: 15px; padding-bottom: 15px;\">\n  <div class=\"col-md-1\"></div>\n  <div class=\"col-md-10 larghezza-max\">\n    <div class=\"row\">\n      <div class=\"col-md-4\">\n        <img class=\"img-fluid\"\n             src=\"assets/img/user/logo_regionepiemonte.png\">\n      </div>\n      <div class=\"col-md-8\" style=\"text-align: right;\">\n        <strong><label class=\"font-17\">ASSISTENZA</label></strong><br>\n        <label class=\"font-14\"><strong>Hai bisogno di\n          aiuto?</strong><br>Scrivi a <strong id=\"mailassistenza\"></strong>\n          specificando:<br> <strong>il tuo recapito\n            telefonico - il codice fiscale - il problema che hai rilevato</strong></label>\n      </div>\n    </div>\n\n  </div>\n  <div class=\"col-md-1\"></div>\n</div>\n\n<div class=\"row sistpiemonte\"\n     style=\"padding-top: 15px; padding-bottom: 15px; color: white\">\n  <div class=\"col-md-1\"></div>\n  <div class=\"col-md-10 larghezza-max\">\n    <div class=\"row\" style=\"justify-content: space-between\">\n      <div class=\"col-md-4\" style=\"padding-left: 0;\">\n        <strong><label class=\"font-17\">Sistemapiemonte</label></strong><br>\n        <label class=\"font-9\">Un servizio a cura della Regione\n          Piemonte<br>P.Iva-CF 80087670016\n        </label>\n      </div>\n      <div class=\"col-md-4\">\n        <img style=\"float: right;\" class=\"img-fluid\"\n             src=\"assets/img/user/logo_csi.png\">\n      </div>\n\n    </div>\n  </div>\n  <div class=\"col-md-1\"></div>\n</div>\n"

/***/ }),

/***/ "./src/app/footbar/footbar.component.ts":
/*!**********************************************!*\
  !*** ./src/app/footbar/footbar.component.ts ***!
  \**********************************************/
/*! exports provided: FootbarComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FootbarComponent", function() { return FootbarComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FootbarComponent = /** @class */ (function () {
    function FootbarComponent() {
    }
    FootbarComponent.prototype.ngOnInit = function () {
    };
    FootbarComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-footbar',
            template: __webpack_require__(/*! ./footbar.component.html */ "./src/app/footbar/footbar.component.html"),
            styles: [__webpack_require__(/*! ./footbar.component.css */ "./src/app/footbar/footbar.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], FootbarComponent);
    return FootbarComponent;
}());



/***/ }),

/***/ "./src/app/informativa.service.ts":
/*!****************************************!*\
  !*** ./src/app/informativa.service.ts ***!
  \****************************************/
/*! exports provided: InformativaService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "InformativaService", function() { return InformativaService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _model_informativa__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./model/informativa */ "./src/app/model/informativa.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var InformativaService = /** @class */ (function () {
    function InformativaService() {
    }
    InformativaService.prototype.getStatus = function (informativa) {
        var result;
        var allConsensusesAreNegated = true;
        var consensos = informativa.consensuses;
        if (consensos) {
            consensos.forEach(function (consenso) {
                if (consenso.tipoStato.codice === "A") {
                    allConsensusesAreNegated = false;
                }
            });
        }
        // informativa.dataScadenza = new Date("1980/1/1");
        // informativa.dataScadenza = undefined;
        // informativa.dataDecorrenza = undefined;
        var date = new Date();
        if (!informativa.dataDecorrenza && !informativa.dataScadenza) {
            result = _model_informativa__WEBPACK_IMPORTED_MODULE_1__["InformativaStatus"].NOT_ACTIVE;
        }
        else if (new Date(informativa.dataDecorrenza) < date && (!informativa.dataScadenza || informativa.dataScadenza > date)) {
            if (allConsensusesAreNegated) {
                result = _model_informativa__WEBPACK_IMPORTED_MODULE_1__["InformativaStatus"].REVOKED;
            }
            else {
                result = _model_informativa__WEBPACK_IMPORTED_MODULE_1__["InformativaStatus"].ACTIVE;
            }
        }
        else {
            result = _model_informativa__WEBPACK_IMPORTED_MODULE_1__["InformativaStatus"].EXPIRED;
        }
        return result;
    };
    InformativaService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [])
    ], InformativaService);
    return InformativaService;
}());



/***/ }),

/***/ "./src/app/model/cittadino.ts":
/*!************************************!*\
  !*** ./src/app/model/cittadino.ts ***!
  \************************************/
/*! exports provided: Cittadino */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Cittadino", function() { return Cittadino; });
var Cittadino = /** @class */ (function () {
    function Cittadino() {
    }
    return Cittadino;
}());



/***/ }),

/***/ "./src/app/model/informativa.ts":
/*!**************************************!*\
  !*** ./src/app/model/informativa.ts ***!
  \**************************************/
/*! exports provided: InformativaStatus, Informativa */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "InformativaStatus", function() { return InformativaStatus; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Informativa", function() { return Informativa; });
var InformativaStatus;
(function (InformativaStatus) {
    InformativaStatus["ACTIVE"] = "ACTIVE";
    InformativaStatus["EXPIRED"] = "EXPIRED";
    InformativaStatus["NOT_ACTIVE"] = "NOT_ACTIVE";
    InformativaStatus["REVOKED"] = "REVOKED";
})(InformativaStatus || (InformativaStatus = {}));
var Informativa = /** @class */ (function () {
    function Informativa() {
    }
    return Informativa;
}());



/***/ }),

/***/ "./src/app/model/request/search-of-citizens.ts":
/*!*****************************************************!*\
  !*** ./src/app/model/request/search-of-citizens.ts ***!
  \*****************************************************/
/*! exports provided: SearchOfCitizens */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SearchOfCitizens", function() { return SearchOfCitizens; });
/* harmony import */ var _service_request__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./service-request */ "./src/app/model/request/service-request.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var SearchOfCitizens = /** @class */ (function (_super) {
    __extends(SearchOfCitizens, _super);
    function SearchOfCitizens() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    Object.defineProperty(SearchOfCitizens.prototype, "cognome", {
        get: function () {
            return this._cognome;
        },
        set: function (value) {
            this._cognome = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SearchOfCitizens.prototype, "codiceFiscale", {
        get: function () {
            return this._codiceFiscale;
        },
        set: function (value) {
            this._codiceFiscale = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SearchOfCitizens.prototype, "nome", {
        get: function () {
            return this._nome;
        },
        set: function (value) {
            this._nome = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SearchOfCitizens.prototype, "dataDiNascita", {
        get: function () {
            return this._dataDiNascita;
        },
        set: function (value) {
            this._dataDiNascita = value;
        },
        enumerable: true,
        configurable: true
    });
    return SearchOfCitizens;
}(_service_request__WEBPACK_IMPORTED_MODULE_0__["ServiceRequest"]));



/***/ }),

/***/ "./src/app/model/request/service-request.ts":
/*!**************************************************!*\
  !*** ./src/app/model/request/service-request.ts ***!
  \**************************************************/
/*! exports provided: ServiceRequest */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ServiceRequest", function() { return ServiceRequest; });
var ServiceRequest = /** @class */ (function () {
    function ServiceRequest() {
    }
    return ServiceRequest;
}());



/***/ }),

/***/ "./src/app/navbar/navbar.component.css":
/*!*********************************************!*\
  !*** ./src/app/navbar/navbar.component.css ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/navbar/navbar.component.html":
/*!**********************************************!*\
  !*** ./src/app/navbar/navbar.component.html ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div id=\"barraTop1\" style=\"background-color: #006cb4; padding-left: 15px; padding-right: 15px\"\n     class=\"row justify-content-center\">\n  <div class=\"col-12 col-md-11 col-lg-10 col-xl-9 nomePazienteBarra\">\n    <div class=\"row\" style=\"padding-top: 2px; color: #ffffff\">\n      <div class=\"col\" style=\"padding-left: 0px; padding-top: 5px;\">\n        <a class=\"noLinkColor\" target=\"_top\" id=\"salute1\">\n          <img style=\"width: 16px; bottom: 2px; position: relative; margin-right: 12px\" class=\"img-fluid\"\n               src=\"assets/img/user/hamburger.png\">\n          la mia <strong>Salute</strong>\n        </a>\n      </div>\n      <div class=\"col-auto row\" id=\"menuservizi\"\n           style=\"padding-top: 5px; padding-left: 0px; padding-right: 0px; text-align: right;display: none;\">\n        <div id=\"tuttiServizio\" style=\"padding-right:5px\">Tutti i servizi</div>\n        <div>\n          <i style=\"cursor: pointer; padding-right: 5px\"\n             class=\"fa fa-navicon fa-lg\" onclick=\"ApriServizi()\"></i>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<div *ngIf=\"showHomeStripe()\" id=\"barraTop2\" style=\"background-color: #0381d6; padding-left: 15px; padding-right: 15px\"\n     class=\"row justify-content-center\">\n  <div class=\"col-12 col-md-11 col-lg-10 col-xl-9 nomePazienteBarra\">\n    <div class=\"row\" style=\"padding-top: 2px; color: #ffffff\">\n      <div class=\"col\" style=\"padding-left: 0px; padding-top: 5px;\">\n        <a [routerLink]=\"['/']\" class=\"noLinkColor\" target=\"_top\" id=\"salute2\">\n          <strong>Gestione consensi</strong>\n        </a>\n      </div>\n      <div class=\"col-auto row\" id=\"menuservizi2\"\n           style=\"padding-top: 5px; padding-left: 0px; padding-right: 0px; text-align: right;display: none;\">\n        <div id=\"tuttiServizio2\" style=\"padding-right:5px\">Tutti i servizi</div>\n        <div>\n          <i style=\"cursor: pointer; padding-right: 5px\"\n             class=\"fa fa-navicon fa-lg\" onclick=\"ApriServizi()\"></i>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/navbar/navbar.component.ts":
/*!********************************************!*\
  !*** ./src/app/navbar/navbar.component.ts ***!
  \********************************************/
/*! exports provided: NavbarComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NavbarComponent", function() { return NavbarComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var NavbarComponent = /** @class */ (function () {
    function NavbarComponent(router) {
        this.router = router;
    }
    NavbarComponent.prototype.showHomeStripe = function () {
        return this.router.url !== '/';
    };
    NavbarComponent.prototype.ngOnInit = function () {
    };
    NavbarComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-navbar',
            template: __webpack_require__(/*! ./navbar.component.html */ "./src/app/navbar/navbar.component.html"),
            styles: [__webpack_require__(/*! ./navbar.component.css */ "./src/app/navbar/navbar.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], NavbarComponent);
    return NavbarComponent;
}());



/***/ }),

/***/ "./src/app/page-not-found/page-not-found.component.css":
/*!*************************************************************!*\
  !*** ./src/app/page-not-found/page-not-found.component.css ***!
  \*************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/page-not-found/page-not-found.component.html":
/*!**************************************************************!*\
  !*** ./src/app/page-not-found/page-not-found.component.html ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  page-not-found works!\n</p>\n"

/***/ }),

/***/ "./src/app/page-not-found/page-not-found.component.ts":
/*!************************************************************!*\
  !*** ./src/app/page-not-found/page-not-found.component.ts ***!
  \************************************************************/
/*! exports provided: PageNotFoundComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PageNotFoundComponent", function() { return PageNotFoundComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var PageNotFoundComponent = /** @class */ (function () {
    function PageNotFoundComponent() {
    }
    PageNotFoundComponent.prototype.ngOnInit = function () {
    };
    PageNotFoundComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-page-not-found',
            template: __webpack_require__(/*! ./page-not-found.component.html */ "./src/app/page-not-found/page-not-found.component.html"),
            styles: [__webpack_require__(/*! ./page-not-found.component.css */ "./src/app/page-not-found/page-not-found.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], PageNotFoundComponent);
    return PageNotFoundComponent;
}());



/***/ }),

/***/ "./src/app/routes.ts":
/*!***************************!*\
  !*** ./src/app/routes.ts ***!
  \***************************/
/*! exports provided: applicationRoute */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "applicationRoute", function() { return applicationRoute; });
/* harmony import */ var _content_citizen_search_citizen_search_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./content/citizen-search/citizen-search.component */ "./src/app/content/citizen-search/citizen-search.component.ts");
/* harmony import */ var _page_not_found_page_not_found_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./page-not-found/page-not-found.component */ "./src/app/page-not-found/page-not-found.component.ts");
/* harmony import */ var _content_consensi_list_consensi_list_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./content/consensi-list/consensi-list.component */ "./src/app/content/consensi-list/consensi-list.component.ts");
/* harmony import */ var _content_consesnsi_detail_consesnsi_detail_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./content/consesnsi-detail/consesnsi-detail.component */ "./src/app/content/consesnsi-detail/consesnsi-detail.component.ts");
/* harmony import */ var _content_feedback_feedback_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./content/feedback/feedback.component */ "./src/app/content/feedback/feedback.component.ts");





var applicationRoute = [
    {
        path: '',
        component: _content_citizen_search_citizen_search_component__WEBPACK_IMPORTED_MODULE_0__["CitizenSearchComponent"]
    }, {
        path: 'cittadino/:fiscalCode/consensi',
        component: _content_consensi_list_consensi_list_component__WEBPACK_IMPORTED_MODULE_2__["ConsensiListComponent"]
    }, {
        path: 'cittadino/:fiscalCode/consensi/dettaglio/:idInformativa',
        component: _content_consesnsi_detail_consesnsi_detail_component__WEBPACK_IMPORTED_MODULE_3__["ConsesnsiDetailComponent"]
    }, {
        path: 'cittadino/:fiscalCode/consensi/feedback',
        component: _content_feedback_feedback_component__WEBPACK_IMPORTED_MODULE_4__["FeedbackComponent"]
    }, {
        path: '**',
        component: _page_not_found_page_not_found_component__WEBPACK_IMPORTED_MODULE_1__["PageNotFoundComponent"]
    }
];


/***/ }),

/***/ "./src/app/soap-request-factory.service.ts":
/*!*************************************************!*\
  !*** ./src/app/soap-request-factory.service.ts ***!
  \*************************************************/
/*! exports provided: SoapRequestFactoryService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SoapRequestFactoryService", function() { return SoapRequestFactoryService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var SoapRequestFactoryService = /** @class */ (function () {
    function SoapRequestFactoryService() {
    }
    SoapRequestFactoryService.prototype.getSearchUserRequest = function (citizen) {

        return [
            '<searchOfCitizens xmlns="http://consprefbe.csi.it/">',
            '<richiedente xmlns="">',
            '<servizio>',
            '<codice>CONSPREFBO</codice>',
            '<idRequest>0</idRequest>',
            '</servizio>',
            '<codiceFiscale></codiceFiscale>',
            '</richiedente>',
            citizen.codiceFiscale ? '<codiceFiscale xmlns="">' + citizen.codiceFiscale + '</codiceFiscale>' : '',
            citizen.cognome ? '<cognome xmlns="">' + citizen.cognome + '</cognome>' : '',
            citizen.nome ? '<nome xmlns="">' + citizen.nome + '</nome>' : '',
            citizen.dataDiNascita ? '<dataDiNascita xmlns="">' + citizen.dataDiNascita + '</dataDiNascita>' : '',
            '</searchOfCitizens>'
        ];
    };
    SoapRequestFactoryService.prototype.modifyConsensus = function () {
        //todo
    };
    SoapRequestFactoryService.prototype.getConsensusesRequest = function (citizenFiscalCode, informativaId) {
        return [
            '<consultaConsensoBe xmlns="http://consprefbe.csi.it/">',
            '<richiedente xmlns="">',
            '<servizio>',
            '<codice>CONSPREFBO</codice>',
            '<idRequest>1</idRequest>',
            '</servizio>',
            '<codiceFiscale>' + citizenFiscalCode + '</codiceFiscale>',
            '</richiedente>',
            '<cfCittadino xmlns="">' + citizenFiscalCode + '</cfCittadino>',
            '<fonte xmlns="">',
            '<dataCreazione>2018-12-12</dataCreazione>',
            '<dataModifica>2018-12-12</dataModifica>',
            '<dataCancellazione>2018-12-12</dataCancellazione>',
            '<loginOperazione>[string?]</loginOperazione>',
            '<ruoloOperazione>',
            '<dataCreazione>2018-12-12</dataCreazione>',
            '<dataModifica>2018-12-12</dataModifica>',
            '<dataCancellazione>2018-12-12</dataCancellazione>',
            '<loginOperazione>[string?]</loginOperazione>',
            '<codice>[string?]</codice>',
            '<descrizione>[string?]</descrizione>',
            '</ruoloOperazione>',
            '<codice>[string?]</codice>',
            '<descrizione>[string?]</descrizione>',
            '<tipoFonte>',
            '<dataCreazione>2018-12-12</dataCreazione>',
            '<dataModifica>2018-12-12</dataModifica>',
            '<dataCancellazione>2018-12-12</dataCancellazione>',
            '<loginOperazione>[string?]</loginOperazione>',
            '<codice>[string?]</codice>',
            '<descrizione>[string?]</descrizione>',
            '</tipoFonte>',
            '</fonte>',
            '<idInformativa xmlns="">' + informativaId + '</idInformativa>',
            '<codAsr xmlns=""></codAsr>',
            '<verificaAura xmlns="">false</verificaAura>',
            '</consultaConsensoBe>'
        ];
    };
    SoapRequestFactoryService.prototype.getModifyConsensoRequest = function (consensus) {
        return [
            '<ns2:modificaConsensoBeRequest xmlns:ns2="http://consprefbe.csi.it/">',
            '<richiedente xmlns="">',
            '<servizio>',
            '<codice>CONSPREFBO</codice>',
            '<idRequest>2</idRequest>',
            '</servizio>',
            '<codiceFiscale>' + consensus.cfCittadino + '</codiceFiscale>',
            '</richiedente>',
            '<cfCittadino xmlns="">' + consensus.cfCittadino + '</cfCittadino>',
            '<idAura xmlns="">' + consensus.idAura + '</idAura>',
            '<fonte xmlns="">',
            '<codice>' + consensus.fonte.codice + '</codice>',
            '<tipoFonte>',
            '<codice>' + consensus.fonte.tipoFonte.codice + '</codice>',
            '</tipoFonte>',
            '</fonte>',
            '<dataAcquisizione xmlns="">' + new Date().toISOString() + '</dataAcquisizione>',
            '<idInformativa xmlns="">' + consensus.informativa.idInformativa + '</idInformativa>',
            '<tipoStato xmlns="">',
            '<codice>' + consensus.tipoStato.codice + '</codice>',
            '</tipoStato>',
            '<valoreConsenso xmlns="">',
            '<codice>' + consensus.valoreConsenso.codice + '</codice>',
            '</valoreConsenso>',
            '<asr xmlns="">',
            '<codice>' + consensus.tipoAsr.codice + '</codice>',
            '<descrizione>' + consensus.tipoAsr.descrizione + '</descrizione>',
            '</asr>',
            '</ns2:modificaConsensoBeRequest>'
        ];
    };
    SoapRequestFactoryService.prototype.getOneInformativaRequest = function (citizenFiscalCode, idInformativa) {
        return [
            '<consultaInformativa xmlns="http://consprefbe.csi.it/">',
            '<richiedente xmlns="">',
            '<servizio>',
            '<codice>CONSPREFBO</codice>',
            '<idRequest>3</idRequest>',
            '</servizio>',
            '<codiceFiscale>' + citizenFiscalCode + '</codiceFiscale>',
            '</richiedente>',
            '</consultaInformativa>'
        ];
    };
    SoapRequestFactoryService.prototype.getInformativaRequest = function (citizenFiscalCode) {
        return [
            '<consultaInformativa xmlns="http://consprefbe.csi.it/">',
            '<richiedente xmlns="">',
            '<servizio>',
            '<codice>CONSPREFBO</codice>',
            '<idRequest>4</idRequest>',
            '</servizio>',
            '<codiceFiscale>' + citizenFiscalCode + '</codiceFiscale>',
            '</richiedente>',
            '</consultaInformativa>'
        ];
    };
    SoapRequestFactoryService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [])
    ], SoapRequestFactoryService);
    return SoapRequestFactoryService;
}());



/***/ }),

/***/ "./src/app/soap.service.ts":
/*!*********************************!*\
  !*** ./src/app/soap.service.ts ***!
  \*********************************/
/*! exports provided: SoapService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SoapService", function() { return SoapService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../environments/environment */ "./src/environments/environment.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var SoapService = /** @class */ (function () {
    function SoapService() {
    }
    SoapService.prototype.sentSoapFindUsers = function (xml) {
        return $.soap({
            url: _environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].consprefboUrl,
            namespaceURL: 'http://consprefbe.csi.it/',
            appendMethodToURL: false,
            soap12: false,
            timeout: 15000,
            data: xml.join('')
        });
    };
    SoapService.prototype.sentSoap = function (xml) {
        return $.soap({
            url: _environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].serviceConsensBeUrl,
            namespaceURL: 'http://consprefbe.csi.it/',
            appendMethodToURL: false,
            soap12: false,
            timeout: 15000,
            data: xml.join(''),
            HTTPHeaders: {
                Authorization: 'Basic ' + btoa(_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].userConsensoBe + ':' + _environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].passwordConsensoBe)
            }
        });
    };
    SoapService.prototype.parseXmlNode = function (xml) {
        var _this = this;
        var root = {};
        var childNodes1 = xml.childNodes;
        if ((childNodes1.length === 1 && childNodes1[0].nodeName === '#text')) {
            root = childNodes1[0].nodeValue;
        }
        else if (xml.nodeName === '#text') {
            root = xml.nodeValue;
        }
        else {
            childNodes1.forEach(function (node) {
                root[node.nodeName] = _this.parseXmlNode(node);
            });
        }
        return root === {} ? undefined : root;
    };
    SoapService.prototype.parseConsensuses = function (xml) {
        var consensoList = xml.getElementsByTagName('consenso');
        var resultList = [];
        for (var i = 0; i < consensoList.length; i++) {
            resultList.push(this.parseXmlNode(consensoList.item(i)));
        }
        return resultList;
    };
    SoapService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [])
    ], SoapService);
    return SoapService;
}());



/***/ }),

/***/ "./src/app/state.service.ts":
/*!**********************************!*\
  !*** ./src/app/state.service.ts ***!
  \**********************************/
/*! exports provided: StateService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StateService", function() { return StateService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var StateService = /** @class */ (function () {
    function StateService() {
        this._data = {};
    }
    Object.defineProperty(StateService.prototype, "data", {
        get: function () {
            return this._data;
        },
        set: function (value) {
            this._data = value;
        },
        enumerable: true,
        configurable: true
    });
    StateService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [])
    ], StateService);
    return StateService;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false,
    fakeTokenProd: false,
    tokeName: 'Shib-Iride-IdentitaDigitale',
    token: 'xyz/xyz/xyz/xyz/xyz/2/',
    // Servizi Deleghe
    consprefboUrl: 'http://xyz.csi.it/consprefboweb/ConsensoBeService',
    auraURL: 'https://xyz/xyz/AURA.WS.AnagrafeFind.cls',
    auraUser: 'CONSPREF',
    auraPassword: 'test',
    serviceConsensBeUrl: 'http://xyz.csi.it/consprefbe-web/ConsensoBeService',
    userConsensoBe: 'user_consprefbo',
    passwordConsensoBe: 'mypass',
    auraAllowed: 'auraAllowed',
    isDebugMode: true,
    XCodiceServizi: 'deleghebo',
    consprefboLogout: 'https://xyz.isan.csi.it/xyz/Shibboleth.sso/Logout',
};


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\User\Documents\CSIPIEMONT\consprefbowcl\trunk\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map
