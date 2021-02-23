package br.com.rafaelq80.projeto01.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelq80.projeto01.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Page<Participante> findByTurmaId(Long turmaId, Pageable pageable);

    Optional<Participante> findByIdAndTurmaId(Long id, Long turmaId);
}