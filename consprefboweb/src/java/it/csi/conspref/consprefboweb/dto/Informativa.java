/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Informativa {

   private Integer idInformativa;
   private String descInformativa;
   private String htmlInformativa;
   private Codifica tipoConsenso;
   private Codifica sottoTipoConsenso;
   private String pdfInformativa;
   private Date dataDecorrenza;
   private Date dataScadenza;
   private List<Consenso> consensoList;

   @JsonProperty("consenso_list")
   public List<Consenso> getConsensoList() {
      return consensoList;
   }
   public void setConsensoList(List<Consenso> consensoList) {
      this.consensoList = consensoList;
   }

   @JsonProperty("html_informativa")
   public String getHtmlInformativa() {
      return htmlInformativa;
   }
   public void setHtmlInformativa(String htmlInformativa) {
      this.htmlInformativa = htmlInformativa;
   }

   @JsonProperty("desc_informativa")
   public String getDescInformativa() {
      return descInformativa;
   }
   public void setDescInformativa(String descInformativa) {
      this.descInformativa = descInformativa;
   }

   @JsonProperty("id_informativa")
   public Integer getIdInformativa() {
      return idInformativa;
   }
   public void setIdInformativa(Integer idInformativa) {
      this.idInformativa = idInformativa;
   }

   @JsonProperty("tipo_consenso")
   public Codifica getTipoConsenso() {
      return tipoConsenso;
   }
   public void setTipoConsenso(Codifica tipoConsenso) {
      this.tipoConsenso = tipoConsenso;
   }

   @JsonProperty("sotto_tipo_consenso")
   public Codifica getSottoTipoConsenso() {
      return sottoTipoConsenso;
   }
   public void setSottoTipoConsenso(Codifica sottoTipoConsenso) {
      this.sottoTipoConsenso = sottoTipoConsenso;
   }

   @JsonProperty("pdf_informativa")
   public String getPdfInformativa() {
      return pdfInformativa;
   }
   public void setPdfInformativa(String pdfInformativa) {
      this.pdfInformativa = pdfInformativa;
   }

   @JsonProperty("data_decorrenza")
   public Date getDataDecorrenza() {
      return dataDecorrenza;
   }
   public void setDataDecorrenza(Date dataDecorrenza) {
      this.dataDecorrenza = dataDecorrenza;
   }

   @JsonProperty("data_scadenza")
   public Date getDataScadenza() {
      return dataScadenza;
   }
   public void setDataScadenza(Date dataScadenza) {
      this.dataScadenza = dataScadenza;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Informativa informativa = (Informativa) o;
      return Objects.equals(tipoConsenso, informativa.tipoConsenso) &&
            Objects.equals(idInformativa, informativa.idInformativa) &&
            Objects.equals(sottoTipoConsenso, informativa.sottoTipoConsenso) &&
            Objects.equals(descInformativa, informativa.descInformativa) &&
            Objects.equals(htmlInformativa, informativa.htmlInformativa) &&
            Objects.equals(pdfInformativa, informativa.pdfInformativa) &&
            Objects.equals(dataDecorrenza, informativa.dataDecorrenza) &&
            Objects.equals(dataScadenza, informativa.dataScadenza);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idInformativa, descInformativa, htmlInformativa, tipoConsenso, sottoTipoConsenso, pdfInformativa, dataDecorrenza, dataScadenza);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Informativa {\n");

      sb.append("    tipoConsenso: ").append(toIndentedString(tipoConsenso)).append("\n");
      sb.append("    htmlInformativa: ").append(toIndentedString(htmlInformativa)).append("\n");
      sb.append("    descInformativa: ").append(toIndentedString(descInformativa)).append("\n");
      sb.append("    idInformativa: ").append(toIndentedString(idInformativa)).append("\n");
      sb.append("    sottoTipoConsenso: ").append(toIndentedString(sottoTipoConsenso)).append("\n");
      sb.append("    pdfInformativa: ").append(toIndentedString(pdfInformativa)).append("\n");
      sb.append("    dataDecorrenza: ").append(toIndentedString(dataDecorrenza)).append("\n");
      sb.append("    dataScadenza: ").append(toIndentedString(dataScadenza)).append("\n");
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
