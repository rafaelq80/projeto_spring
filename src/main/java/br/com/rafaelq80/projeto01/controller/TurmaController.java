package br.com.rafaelq80.projeto01.controller;

import static br.com.rafaelq80.projeto01.controller.ResponseUtil.resourceUri;

import java.util.Optional;

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
import br.com.rafaelq80.projeto01.model.Turma;
import br.com.rafaelq80.projeto01.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TurmaController {

    private final TurmaRepository turmaRepository;

    @GetMapping("/turmas")
    public Page<Turma> getAllTurmas(Pageable pageable) {
        return turmaRepository.findAll(pageable);
    }

    @PostMapping("/turmas")
    public ResponseEntity<Turma> saveTurma(@Validated @RequestBody Turma request) {
        return Optional.of(request).map(turmaRepository::save)
                .map(turma -> ResponseEntity.created(resourceUri(turma.getId())).body(turma))
                .orElseThrow(IllegalArgumentException::new);
    }

    @PutMapping("/turmas/{turmaId}")
    public ResponseEntity<Turma> updateTurma(@PathVariable @Min(1) final Long turmaId,
            @Validated @RequestBody Turma request) {
        return turmaRepository.findById(turmaId).map(turma -> {
            turma.setDescricao(request.getDescricao());
            turma.setTipo(request.getTipo());
            return turma;
        }).map(turmaRepository::save).map(turma -> ResponseEntity.ok().location(resourceUri(turmaId)).body(turma))
                .orElseThrow(() -> new ResourceNotFoundException(turmaId));

    }

    @DeleteMapping("/turmas/{turmaId}")
    public ResponseEntity<?> deleteTurma(@PathVariable Long turmaId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            turmaRepository.delete(turma);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(turmaId));
    }

}
