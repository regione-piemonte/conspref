/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Consenso {

   private String cfCittadino;
   private String cfDelegato;
   private String idAura;
   private String nome;
   private String delegatoNome;
   private String delegatoCognome;
   private String cognome;
   private Date dataAcquisizione;
   private Operatore operatore;
   private Fonte fonte;
   private Codifica tipoStato;
   private Informativa informativa;
   private Codifica valoreConsenso;
   private Codifica tipoAsr;
   private UUID uuid;
   private Boolean updated;

   public void setUpdated(Boolean updated) {
      this.updated = updated;
   }

   public void setCfCittadino(String cfCittadino) {
      this.cfCittadino = cfCittadino;
   }

   public void setCfDelegato(String cfDelegato) {
      this.cfDelegato = cfDelegato;
   }

   public void setIdAura(String idAura) {
      this.idAura = idAura;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public void setCognome(String cognome) {
      this.cognome = cognome;
   }

   public void setDataAcquisizione(Date dataAcquisizione) {
      this.dataAcquisizione = dataAcquisizione;
   }

   public void setOperatore(Operatore operatore) {
      this.operatore = operatore;
   }

   public void setFonte(Fonte fonte) {
      this.fonte = fonte;
   }

   public void setTipoStato(Codifica tipoStato) {
      this.tipoStato = tipoStato;
   }

   public void setInformativa(Informativa informativa) {
      this.informativa = informativa;
   }

   public void setValoreConsenso(Codifica valoreConsenso) {
      this.valoreConsenso = valoreConsenso;
   }

   public void setTipoAsr(Codifica tipoAsr) {
      this.tipoAsr = tipoAsr;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }

   public void setDelegatoNome(String delegatoNome) {
      this.delegatoNome = delegatoNome;
   }

   public void setDelegatoCognome(String delegatoCognome) {
      this.delegatoCognome = delegatoCognome;
   }

   @JsonProperty("cf_cittadino")
   public String getCfCittadino() {
      return cfCittadino;
   }

   @JsonProperty("updated")
   public Boolean getUpdated() {
      return updated;
   }

   @JsonProperty("cf_delegato")
   public String getCfDelegato() {
      return cfDelegato;
   }

   @JsonProperty("id_aura")
   public String getIdAura() {
      return idAura;
   }

   @JsonProperty("nome")
   public String getNome() {
      return nome;
   }

   @JsonProperty("delegato_nome")
   public String getDelegatoNome() {
      return delegatoNome;
   }

   @JsonProperty("delegato_cognome")
   public String getDelegatoCognome() {
      return delegatoCognome;
   }

   @JsonProperty("cognome")
   public String getCognome() {
      return cognome;
   }

   @JsonProperty("data_acquisizione")
   public Date getDataAcquisizione() {
      return dataAcquisizione;
   }

   @JsonProperty("operatore")
   public Operatore getOperatore() {
      return operatore;
   }

   @JsonProperty("fonte")
   public Fonte getFonte() {
      return fonte;
   }

   @JsonProperty("tipo_stato")
   public Codifica getTipoStato() {
      return tipoStato;
   }

   @JsonProperty("informativa")
   public Informativa getInformativa() {
      return informativa;
   }

   @JsonProperty("valore_consenso")
   public Codifica getValoreConsenso() {
      return valoreConsenso;
   }

   @JsonProperty("tipo_asr")
   public Codifica getTipoAsr() {
      return tipoAsr;
   }

   @JsonProperty("uuid")
   public UUID getUuid() {
      return uuid;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Consenso informativa = (Consenso) o;
      return Objects.equals(cfCittadino, informativa.cfCittadino) &&
            Objects.equals(cfDelegato, informativa.cfDelegato) &&
            Objects.equals(idAura, informativa.idAura) &&
            Objects.equals(nome, informativa.nome) &&
            Objects.equals(cognome, informativa.cognome) &&
            Objects.equals(dataAcquisizione, informativa.dataAcquisizione) &&
            Objects.equals(operatore, informativa.operatore) &&
            Objects.equals(informativa, informativa.informativa) &&
            Objects.equals(valoreConsenso, informativa.valoreConsenso) &&
            Objects.equals(tipoAsr, informativa.tipoAsr) &&
            Objects.equals(uuid, informativa.uuid) &&
            Objects.equals(tipoStato, informativa.tipoStato);
   }

   @Override
   public int hashCode() {
      return Objects.hash(cfCittadino, cfDelegato, idAura, nome, cognome, dataAcquisizione, operatore, tipoStato, informativa, valoreConsenso, tipoAsr, uuid);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Informativa {\n");

      sb.append("    cfCittadino: ").append(toIndentedString(cfCittadino)).append("\n");
      sb.append("    cfDelegato: ").append(toIndentedString(cfDelegato)).append("\n");
      sb.append("    idAura: ").append(toIndentedString(idAura)).append("\n");
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
