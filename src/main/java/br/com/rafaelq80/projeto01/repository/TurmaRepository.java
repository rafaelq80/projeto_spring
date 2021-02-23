package br.com.rafaelq80.projeto01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelq80.projeto01.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
