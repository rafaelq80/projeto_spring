package br.com.rafaelq80.projeto01.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "descricao", "tipo" })
public interface TurmaDto {

    public Long getId();

    public String getDescricao();

    public String getTipo();

    public ParticipanteDto getParticipante();

    @JsonPropertyOrder({ "id", "nome", "email", "observacoes" })
    interface ParticipanteDto {

        public Long getId();

        public String getNome();

        public String getEmail();

        public String getObservacoes();

    }

}