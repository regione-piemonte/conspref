/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Fonte extends Codifica{

   private Codifica tipoFonte;

   @JsonProperty("tipo_fonte")
   public Codifica getTipoFonte() {
      return tipoFonte;
   }
   public void setTipoFonte(Codifica tipoFonte) {
      this.tipoFonte = tipoFonte;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Fonte fonte = (Fonte) o;
      return Objects.equals(tipoFonte, fonte.tipoFonte) &&
       Objects.equals(codice, fonte.codice) &&
       Objects.equals(descrizione, fonte.descrizione);
   }

   @Override
   public int hashCode() {
      return Objects.hash(tipoFonte, codice, descrizione);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Cittadino {\n");

      sb.append("    tipoFonte: ").append(toIndentedString(tipoFonte)).append("\n");
      sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
      sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
      sb.append("}");
      return sb.toString();
   }

   /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
   private String toIndentedString(Object o) {
      if (o == null) {
         return "null";
      }
      return o.toString().replace("\n", "\n    ");
   }

}
