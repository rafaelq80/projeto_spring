package br.com.rafaelq80.projeto01.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafaelq80.projeto01.dto.TurmaDto;
import br.com.rafaelq80.projeto01.model.Turma;

@Repository
@Transactional(readOnly = true)
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    Page<Turma> findByParticipanteId(Long participanteId, Pageable pageable);

    Page<Turma> findById(Long turmaId, Pageable pageable);

    Page<Turma> findByDescricao(String descricao, Pageable pageable);

    Optional<Turma> findByIdAndParticipanteId(Long id, Long participanteId);

    @Query("SELECT t.id AS id, t.descricao AS descricao, t.tipo AS tipo, p AS participante "
            + "FROM Turma t LEFT JOIN t.participante p")
    Page<TurmaDto> findBy(Pageable pageable);

}
