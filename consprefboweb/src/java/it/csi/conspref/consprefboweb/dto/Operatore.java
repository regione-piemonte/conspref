/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Operatore {

   private String tipoOperatore;
   private String codiceOperatore;


   @JsonProperty("codice_operatore")
   public String getCodiceOperatore() {
      return codiceOperatore;
   }

   public void setCodiceOperatore(String codiceOperatore) {
      this.codiceOperatore = codiceOperatore;
   }

   @JsonProperty("tipo_operatore")
   public String getTipoOperatore() {
      return tipoOperatore;
   }

   public void setTipoOperatore(String tipoOperatore) {
      this.tipoOperatore = tipoOperatore;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Operatore informativa = (Operatore) o;
      return Objects.equals(codiceOperatore, informativa.codiceOperatore) &&
            Objects.equals(tipoOperatore, informativa.tipoOperatore);
   }

   @Override
   public int hashCode() {
      return Objects.hash(codiceOperatore, tipoOperatore);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Informativa {\n");

      sb.append("    tipoOperatore: ").append(toIndentedString(tipoOperatore)).append("\n");
      sb.append("    codiceOperatore: ").append(toIndentedString(codiceOperatore)).append("\n");
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
