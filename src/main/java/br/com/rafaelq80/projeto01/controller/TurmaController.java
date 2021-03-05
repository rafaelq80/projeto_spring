package br.com.rafaelq80.projeto01.controller;

import static br.com.rafaelq80.projeto01.controller.ResponseUtil.resourceUri;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelq80.projeto01.dto.TurmaDto;
import br.com.rafaelq80.projeto01.exception.ResourceNotFoundException;
import br.com.rafaelq80.projeto01.model.Participante;
import br.com.rafaelq80.projeto01.model.Turma;
import br.com.rafaelq80.projeto01.repository.ParticipanteRepository;
import br.com.rafaelq80.projeto01.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TurmaController {

    private final TurmaRepository turmaRepository;
    private final ParticipanteRepository participanteRepository;

    @GetMapping("/participantes/{participanteId}/turmas")
    public Page<Turma> getAllTurmasByParticipanteId(@PathVariable(value = "participanteId") Long participanteId,
            Pageable pageable) {
        return turmaRepository.findByParticipanteId(participanteId, pageable);
    }

    @PostMapping("/participantes/{participanteId}/turmas")
    public ResponseEntity<Turma> createTurma(
            @PathVariable(value = "participanteId") @Min(value = 1) Long participanteId,
            @Validated @RequestBody Turma turmaRequest) {
        return participanteRepository.findById(participanteId).map(participante -> {
            turmaRequest.setParticipante(participante);
            return turmaRepository.save(turmaRequest);
        }).map(turma -> ResponseEntity.created(resourceUri(turma.getId())).body(turma))
                .orElseThrow(() -> new ResourceNotFoundException(participanteId));
    }

    @PutMapping("/participantes/{participanteId}/turmas/{turmaId}")
    public ResponseEntity<Turma> updateTurma(@PathVariable(value = "participanteId") Long participanteId,
            @PathVariable(value = "participanteId") Long turmaId, @Validated @RequestBody Turma turmaRequest) {

        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new ResourceNotFoundException(participanteId));

        return turmaRepository.findById(turmaId).map(turma -> {
            turma.setDescricao(turmaRequest.getDescricao());
            turma.setTipo(turmaRequest.getTipo());
            turma.setParticipante(participante);
            return turmaRepository.save(turma);
        }).map(turma -> ResponseEntity.ok().location(resourceUri(turma.getId())).body(turma))
                .orElseThrow(() -> new ResourceNotFoundException(turmaId));
    }

    @DeleteMapping("/participantes/{participanteId}/turmas/{turmaId}")
    public ResponseEntity<?> deleteTurma(@PathVariable(value = "participanteId") Long participanteId,
            @PathVariable(value = "turmaId") Long turmaId) {
        return turmaRepository.findByIdAndParticipanteId(turmaId, participanteId).map(turma -> {
            turmaRepository.delete(turma);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(turmaId));
    }

    @GetMapping("/turmas/all")
    public Page<Turma> getAllTurmas(Pageable pageable) {
        return turmaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @GetMapping("/turmas/participantes/all")
    public Page<TurmaDto> getAllTurmas2(Pageable pageable) {
        return turmaRepository.findBy(pageable);
    }

    @GetMapping("/turmas/id/{turmaId}")
    public Page<Turma> findByTurmaId(@PathVariable(value = "turmaId") Long turmaId, Pageable pageable) {
        return turmaRepository.findById(turmaId, pageable);
    }

    @GetMapping("/turmas/descricao/{turmaDescricao}")
    public Page<Turma> findByTurmaDescricao(@PathVariable(value = "turmaDescricao") String turmaDescricao,
            Pageable pageable) {
        return turmaRepository.findByDescricao(turmaDescricao, pageable);
    }

}
