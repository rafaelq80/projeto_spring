package br.com.rafaelq80.projeto01.model;

import java.util.Objects;

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

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "observacoes", nullable = false)
    private String observacoes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false, foreignKey = @ForeignKey(name = "fk_partcipantes_turmas_id"))

    private Turma turma;

    @Override
    public String toString() {
        return "{" + " nome='" + getNome() + "'" + ", email='" + getEmail() + "'" + ", observacoes='" + getObservacoes()
                + "'" + ", turma='" + getTurma() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Participante)) {
            return false;
        }
        Participante participante = (Participante) o;
        return Objects.equals(nome, participante.nome) && Objects.equals(email, participante.email)
                && Objects.equals(observacoes, participante.observacoes) && Objects.equals(turma, participante.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, observacoes, turma);
    }

}
