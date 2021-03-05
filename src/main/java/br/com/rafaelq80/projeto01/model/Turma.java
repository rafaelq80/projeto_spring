package br.com.rafaelq80.projeto01.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turmas")

public class Turma extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = true)

    @JoinColumn(name = "participante_id", nullable = false, foreignKey = @ForeignKey(name = "fk_turmas_participantes_id"))
    @OrderColumn()

    private Participante participante;

}
