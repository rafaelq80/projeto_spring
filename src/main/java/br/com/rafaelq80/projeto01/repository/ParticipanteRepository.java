package br.com.rafaelq80.projeto01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelq80.projeto01.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    Page<Participante> findById(Long participanteId, Pageable pageable);

    Page<Participante> findByNome(String participanteNome, Pageable pageable);

}