package br.com.rafaelq80.projeto01.controller;

import static br.com.rafaelq80.projeto01.controller.ResponseUtil.resourceUri;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelq80.projeto01.exception.ResourceNotFoundException;
import br.com.rafaelq80.projeto01.model.Participante;
import br.com.rafaelq80.projeto01.model.Turma;
import br.com.rafaelq80.projeto01.repository.ParticipanteRepository;
import br.com.rafaelq80.projeto01.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ParticipanteController {

    private final TurmaRepository turmaRepository;
    private final ParticipanteRepository participanteRepository;

    @GetMapping("/turmas/{turmaId}/participantes")
    public Page<Participante> getAllParticipantesByParticipanteId(@PathVariable(value = "turmaId") Long turmaId,
            Pageable pageable) {
        return participanteRepository.findByTurmaId(turmaId, pageable);
    }

    @PostMapping("/turmas/{turmaId}/participantes")
    public ResponseEntity<Participante> createParticipante(
            @PathVariable(value = "turmaId") @Min(value = 1) Long turmaId,
            @Validated @RequestBody Participante participanteRequest) {
        return turmaRepository.findById(turmaId).map(turma -> {
            participanteRequest.setTurma(turma);
            return participanteRepository.save(participanteRequest);
        }).map(participante -> ResponseEntity.created(resourceUri(participante.getId())).body(participante))
                .orElseThrow(() -> new ResourceNotFoundException(turmaId));
    }

    @PutMapping("/turmas/{turmaId}/participantes/{participanteId}")
    public ResponseEntity<Participante> updateParticipante(@PathVariable(value = "turmaId") Long turmaId,
            @PathVariable(value = "participanteId") Long participanteId,
            @Validated @RequestBody Participante participanteRequest) {

        Turma turma = turmaRepository.findById(turmaId).orElseThrow(() -> new ResourceNotFoundException(turmaId));

        return participanteRepository.findById(participanteId).map(participante -> {
            participante.setNome(participanteRequest.getNome());
            participante.setEmail(participanteRequest.getEmail());
            participante.setObservacoes(participanteRequest.getObservacoes());
            participante.setTurma(turma);
            return participanteRepository.save(participante);
        }).map(Participante -> ResponseEntity.ok().location(resourceUri(Participante.getId())).body(Participante))
                .orElseThrow(() -> new ResourceNotFoundException(turmaId));
    }

    @DeleteMapping("/turmas/{turmaId}/participantes/{participanteId}")
    public ResponseEntity<?> deleteParticipante(@PathVariable(value = "turmaId") Long turmaId,
            @PathVariable(value = "participanteId") Long participanteId) {
        return participanteRepository.findByIdAndTurmaId(participanteId, turmaId).map(participante -> {
            participanteRepository.delete(participante);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(turmaId));
    }

    @GetMapping("/participantes/all")
    public Page<Participante> getAllParticipantes(Pageable pageable) {
        return participanteRepository.findAll(pageable);
    }

}
