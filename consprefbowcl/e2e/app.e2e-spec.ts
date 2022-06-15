import { CsipiemontFePage } from './app.po';

/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

describe('csipiemont-fe App', function() {
  let page: CsipiemontFePage;

  beforeEach(() => {
    page = new CsipiemontFePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
