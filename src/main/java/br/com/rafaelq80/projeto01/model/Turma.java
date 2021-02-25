package br.com.rafaelq80.projeto01.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "turmas")
public class Turma extends BaseEntity {

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    // @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "turma")
    @Builder.Default
    private List<Participante> participantes = new ArrayList<>();

    @Override
    public String toString() {
        return "{" + " descricao='" + getDescricao() + "'" + ", tipo='" + getTipo() + "'" + ", participantes='"
                + getParticipantes() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Turma)) {
            return false;
        }
        Turma turma = (Turma) o;
        return Objects.equals(descricao, turma.descricao) && Objects.equals(tipo, turma.tipo)
                && Objects.equals(participantes, turma.participantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, tipo, participantes);
    }

}
