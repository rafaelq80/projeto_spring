package br.com.rafaelq80.projeto01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participantes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participante extends BaseEntity {

    public Participante(String nome2, String email2, String observacoes2, int i) {
    }

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "observacoes", nullable = false)
    private String observacoes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turma_id", nullable = false, foreignKey = @ForeignKey(name = "fk_partcipantes_turmas_id"))

    private Turma turma;
}
