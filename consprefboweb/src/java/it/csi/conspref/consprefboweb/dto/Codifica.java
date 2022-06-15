/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Codifica {

   protected String codice;
   protected String descrizione;

   @JsonProperty("codice")
   public String getCodice() {
      return codice;
   }
   public void setCodice(String codice) {
      this.codice = codice;
   }

   @JsonProperty("descrizione")
   public String getDescrizione() {
      return descrizione;
   }
   public void setDescrizione(String descrizione) {
      this.descrizione = descrizione;
   }

}
