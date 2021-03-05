package br.com.rafaelq80.projeto01.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "participantes")

public class Participante extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "observacoes", nullable = false)
    private String observacoes;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "participante")
    @Builder.Default

    private List<Turma> turmas = new ArrayList<>();

}
